package edu.java.scrapper.appclients;

import edu.java.scrapper.dto.request.AddLinkRequest;
import edu.java.scrapper.dto.request.RemoveLinkRequest;
import edu.java.scrapper.dto.response.ApiErrorResponse;
import edu.java.scrapper.dto.response.LinkResponse;
import edu.java.scrapper.dto.response.ListLinksResponse;
import edu.java.scrapper.exception.ChatNotFoundException;
import edu.java.scrapper.exception.DuplicateChatException;
import edu.java.scrapper.exception.LinkAlreadyTrackedException;
import edu.java.scrapper.exception.LinkNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ScrapperWebClient implements ScrapperClient {
    private final WebClient webClient;

    public ScrapperWebClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Override
    public void registerChat(long id) {
        webClient.post()
            .uri("/tg-chat/{id}", id)
            .retrieve()
            .onStatus(
                HttpStatus.CONFLICT::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .map(error -> new DuplicateChatException(error.description()))
            )
            .toBodilessEntity()
            .block();
    }

    @Override
    public void deleteChat(long id) {
        webClient.delete()
            .uri("/tg-chat/{id}", id)
            .retrieve()
            .onStatus(
                HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .map(error -> new ChatNotFoundException(error.description()))
            )
            .toBodilessEntity()
            .block();
    }

    @Override
    public ListLinksResponse getLinks(long tgChatId) {
        return webClient.get()
            .uri("/links")
            .header("Tg-Chat-Id", String.valueOf(tgChatId))
            .retrieve()
            .onStatus(
                HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .map(error -> new ChatNotFoundException(error.description()))
            )
            .bodyToMono(ListLinksResponse.class)
            .block();
    }

    @Override
    public LinkResponse addLink(long tgChatId, AddLinkRequest request) {
        return webClient.post()
            .uri("/links")
            .header("Tg-Chat-Id", String.valueOf(tgChatId))
            .retrieve()
            .onStatus(
                HttpStatus.CONFLICT::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .map(error -> new LinkAlreadyTrackedException(error.description()))
            )
            .onStatus(
                HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .map(error -> new ChatNotFoundException(error.description()))
            )
            .bodyToMono(LinkResponse.class)
            .block();
    }

    @Override
    public LinkResponse removeLink(long tgChatId, RemoveLinkRequest request) {
        return webClient.delete()
            .uri("/links")
            .header("Tg-Chat-Id", String.valueOf(tgChatId))
            .retrieve()
            .onStatus(
                HttpStatus.NOT_FOUND::equals,
                response -> response.bodyToMono(ApiErrorResponse.class)
                    .flatMap(error -> {
                        if (error.description().contains("link not found")) {
                            return Mono.error(new LinkNotFoundException(error.description()));
                        }
                        return Mono.error(new ChatNotFoundException(error.description()));
                    })
            )
            .bodyToMono(LinkResponse.class)
            .block();
    }
}
