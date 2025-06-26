package edu.java.scrapper.appclients;

import edu.java.scrapper.dto.request.LinkUpdateRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class BotWebClient implements BotClient {
    private final WebClient webClient;

    public BotWebClient(String baseUrl) {
        this.webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Override
    public void sendUpdate(LinkUpdateRequest request) {
        webClient.post()
            .uri("/updates")
            .bodyValue(request) // Сериализуется в JSON
            .retrieve()
            .toBodilessEntity()
            .block();
    }
}
