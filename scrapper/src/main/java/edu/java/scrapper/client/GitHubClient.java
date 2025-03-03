package edu.java.scrapper.client;

import edu.java.scrapper.dto.GithubRepoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange(url = "${github.base-url:https://api.github.com}")
@Component
public interface GitHubClient {
    Mono<GithubRepoResponse> fetchRepository(String owner, String repo);
}
