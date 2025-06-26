package edu.java.bot.service;

import edu.java.bot.dto.LinkUpdateRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultNotificationHandler implements NotificationHandler {
    private final Counter processedMessages;

    @Autowired
    public DefaultNotificationHandler(MeterRegistry registry) {
        this.processedMessages = Counter.builder("bot_processed_messages")
            .description("Количество обработанных сообщений ботом")
            .register(registry);
    }

    @Override
    public void handle(LinkUpdateRequest update) {
        processedMessages.increment();
        // Здесь общая логика обработки уведомлений (например, отправка в Telegram)
        System.out.println("Received update: " + update);
    }
} 