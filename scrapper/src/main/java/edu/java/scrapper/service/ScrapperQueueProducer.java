package edu.java.scrapper.service;

import edu.java.scrapper.configuration.ApplicationConfig;
import edu.java.scrapper.dto.LinkUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer {
    private final KafkaTemplate<String, LinkUpdate> kafkaTemplate;
    private final ApplicationConfig config;

    public void send(LinkUpdate update) {
        kafkaTemplate.send(config.getScrapperTopicName(), update);
    }
} 