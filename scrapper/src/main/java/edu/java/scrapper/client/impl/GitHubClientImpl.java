package edu.java.scrapper.client.impl;

import edu.java.scrapper.client.GitHubClient;
import edu.java.scrapper.dto.GitHubRepoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
public class GitHubClientImpl implements GitHubClient {
    private final WebClient webClient;

    @Override
    public GitHubRepoResponse fetchRepo(String owner, String repo) {
        return webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(GitHubRepoResponse.class)
            .block();
    }
}
