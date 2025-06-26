package edu.java.scrapper.configuration;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationConfig(
    Scheduler scheduler,
    String kafkaBootstrapServers,
    ScrapperTopic scrapperTopic,
    boolean useQueue
) {
    public record Scheduler(
        Duration interval
    ) {}

    public String getKafkaBootstrapServers() { return kafkaBootstrapServers; }
    public String getScrapperTopicName() { return scrapperTopic.name; }
    public String getScrapperDlqTopicName() { return scrapperTopic.dlqName; }
    public boolean isUseQueue() { return useQueue; }

    public static class ScrapperTopic {
        private String name;
        private String dlqName;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDlqName() { return dlqName; }
        public void setDlqName(String dlqName) { this.dlqName = dlqName; }
    }
}
