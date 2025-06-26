package edu.java.scrapper.service;

import edu.java.scrapper.dto.LinkUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.use-queue", havingValue = "true")
@RequiredArgsConstructor
public class QueueNotificationSender implements NotificationSender {
    private final ScrapperQueueProducer producer;
    @Override
    public void send(LinkUpdate update) {
        producer.send(update);
    }
} 