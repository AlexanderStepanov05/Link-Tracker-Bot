package edu.java.scrapper.configuration;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationConfig(
    Scheduler scheduler
) {
    public record Scheduler(
        Duration interval
    ) {}
}
