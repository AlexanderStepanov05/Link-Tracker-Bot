package edu.java.scrapper.configuration;

import edu.java.scrapper.client.GitHubClient;
import edu.java.scrapper.client.StackOverflowClient;
import edu.java.scrapper.dto.GithubRepoResponse;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@Configuration
@EnableScheduling
public class ClientConfiguration {

    @Bean(name = "scheduler")
    public Scheduler scheduler(@Value("${app.scheduler.interval}") Duration interval) {
        return new Scheduler(interval);
    }

    @Bean
    public GitHubClient gitHubClient(@Value("${github.base-url:https://api.github.com}") String baseUrl) {
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.v3+json")
            .build();

        return new GitHubClient() {
            @Override
            public Mono<GithubRepoResponse> fetchRepository(String owner, String repo) {
                return webClient.get()
                    .uri("/repos/{owner}/{repo}", owner, repo)
                    .retrieve()
                    .bodyToMono(GithubRepoResponse.class);
            }
        };
    }

    @Bean
    public StackOverflowClient stackOverflowClient(
        @Value("${stackoverflow.base-url:https://api.stackexchange.com}") String baseUrl) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(
                RestClient.builder()
                    .baseUrl(baseUrl)
                    .build()))
            .build();

        return factory.createClient(StackOverflowClient.class);
    }

    record Scheduler(Duration interval) {}
}
