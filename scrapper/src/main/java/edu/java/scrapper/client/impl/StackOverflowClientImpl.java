package edu.java.scrapper.client.impl;

import edu.java.scrapper.client.StackOverflowClient;
import edu.java.scrapper.dto.StackOverflowQuestionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class StackOverflowClientImpl implements StackOverflowClient {
    private final WebClient webClient;

    @Override
    public StackOverflowQuestionResponse fetchQuestion(long questionId) {
        return webClient.get()
            .uri("/questions/{id}?site=stackoverflow", questionId)
            .retrieve()
            .bodyToMono(StackOverflowQuestionResponse.class)
            .block();
    }
}
