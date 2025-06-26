package edu.java.scrapper.configuration;

import edu.java.scrapper.appclients.BotClient;
import edu.java.scrapper.appclients.BotWebClient;
import edu.java.scrapper.appclients.ScrapperClient;
import edu.java.scrapper.appclients.ScrapperWebClient;
import edu.java.scrapper.client.GitHubClient;
import edu.java.scrapper.client.StackOverflowClient;
import edu.java.scrapper.client.impl.GitHubClientImpl;
import edu.java.scrapper.client.impl.StackOverflowClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class ClientConfiguration {

    @Value("${github.base-url:https://api.github.com}")
    private String githubBaseUrl;

    @Value("${stackoverflow.base-url:https://api.stackexchange.com/2.3}")
    private String stackoverflowBaseUrl;

    @Value("${scrapper.base-url:http://localhost:8080}")
    private String scrapperBaseUrl;

    @Value("${bot.base-url:http://localhost:8090}")
    private String botBaseUrl;

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperWebClient(scrapperBaseUrl);
    }

    @Bean
    public BotClient botClient() {
        return new BotWebClient(botBaseUrl);
    }

    @Bean
    public GitHubClient gitHubClient(WebClient.Builder webClientBuilder) {
        return new GitHubClientImpl(webClientBuilder.baseUrl(githubBaseUrl).build());
    }

    @Bean
    public StackOverflowClient stackOverflowClient(WebClient.Builder webClientBuilder) {
        return new StackOverflowClientImpl(
            webClientBuilder.baseUrl(stackoverflowBaseUrl).build()
        );
    }
}
