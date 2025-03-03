package edu.java.scrapper.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import edu.java.scrapper.dto.GithubRepoResponse;
import edu.java.scrapper.dto.StackOverflowQuestionResponse;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ClientTests {

    private WireMockServer wireMockServer;

    @Autowired
    private GitHubClient gitHubClient;

    @Autowired
    private StackOverflowClient stackOverflowClient;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());

        // Настройка базовых URL для клиентов
        gitHubClient = new GitHubClient() {
            private final WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:" + wireMockServer.port())
                .build();

            @Override
            public Mono<GithubRepoResponse> fetchRepository(String owner, String repo) {
                return webClient.get()
                    .uri("/repos/{owner}/{repo}", owner, repo)
                    .retrieve()
                    .bodyToMono(GithubRepoResponse.class);
            }
        };

        stackOverflowClient = HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(
                RestClient.builder()
                    .baseUrl("http://localhost:" + wireMockServer.port())
                    .build()))
            .build()
            .createClient(StackOverflowClient.class);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void testGitHubClient() {
        stubFor(get(urlEqualTo("/repos/test-owner/test-repo"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("""
                    {
                        "name": "test-repo",
                        "updated_at": "2025-03-03T10:00:00Z",
                        "description": "Test description"
                    }
                    """)));

        GithubRepoResponse response = gitHubClient.fetchRepository("test-owner", "test-repo")
            .block();

        assertThat(response.name()).isEqualTo("test-repo");
        assertThat(response.updatedAt()).isEqualTo(OffsetDateTime.parse("2025-03-03T10:00:00Z"));
    }

    @Test
    void testStackOverflowClient() {
        stubFor(get(urlEqualTo("/2.3/questions/123?site=stackoverflow"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("""
        {
            "items": [{
            "title": "Test question",
                "last_activity_date": 1646308800,
                "answer_count": 2
        }]
        }
        """)));

        StackOverflowQuestionResponse response = stackOverflowClient.fetchQuestion("123", "stackoverflow");

        assertThat(response.items()).hasSize(1);
        assertThat(response.items().get(0).title()).isEqualTo("Test question");
    }
}
