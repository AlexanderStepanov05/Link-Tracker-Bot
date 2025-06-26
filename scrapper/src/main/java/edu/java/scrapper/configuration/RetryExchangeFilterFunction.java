package edu.java.scrapper.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Component
public class RetryExchangeFilterFunction implements ExchangeFilterFunction {
    private final HttpClientRetryProperties properties;

    public RetryExchangeFilterFunction(HttpClientRetryProperties properties) {
        this.properties = properties;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return next.exchange(request)
            .flatMap(response -> handleRetry(response, request, next, 1));
    }

    private Mono<ClientResponse> handleRetry(ClientResponse response, ClientRequest request, ExchangeFunction next, int attempt) {
        List<Integer> retryCodes = properties.getRetryCodes();
        if (retryCodes.contains(response.statusCode().value()) && attempt < properties.getMaxAttempts()) {
            long delay = getBackoffDelay(attempt);
            return Mono.delay(Duration.ofMillis(delay))
                .flatMap(ignored -> next.exchange(request))
                .flatMap(r -> handleRetry(r, request, next, attempt + 1));
        }
        return Mono.just(response);
    }

    private long getBackoffDelay(int attempt) {
        return switch (properties.getBackoffType()) {
            case "linear" -> properties.getBackoffDelay() * attempt;
            case "exponential" -> (long) (properties.getBackoffDelay() * Math.pow(2, attempt - 1));
            default -> properties.getBackoffDelay();
        };
    }
} 