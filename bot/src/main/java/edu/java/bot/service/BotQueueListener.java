package edu.java.bot.service;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.dto.LinkUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotQueueListener {
    private final NotificationHandler handler;
    private final KafkaTemplate<String, LinkUpdateRequest> kafkaTemplate;
    private final ApplicationConfig config;

    @KafkaListener(topics = "${app.scrapper-topic.name}")
    public void listen(LinkUpdateRequest update) {
        try {
            handler.handle(update);
        } catch (Exception e) {
            kafkaTemplate.send(config.getScrapperDlqTopicName(), update);
        }
    }
} 