package edu.java.bot.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class ApplicationConfig {
    @NotEmpty
    @Value("${app.telegram-token}")
    String telegramToken;

    private String kafkaBootstrapServers;
    private ScrapperTopic scrapperTopic;

    public String getKafkaBootstrapServers() { return kafkaBootstrapServers; }
    public String getScrapperTopicName() { return scrapperTopic.name; }
    public String getScrapperDlqTopicName() { return scrapperTopic.dlqName; }

    public static class ScrapperTopic {
        private String name;
        private String dlqName;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDlqName() { return dlqName; }
        public void setDlqName(String dlqName) { this.dlqName = dlqName; }
    }
}
