package edu.java.bot.service;

import edu.java.bot.dto.LinkUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class DefaultNotificationHandler implements NotificationHandler {
    @Override
    public void handle(LinkUpdateRequest update) {
        // Здесь общая логика обработки уведомлений (например, отправка в Telegram)
        System.out.println("Received update: " + update);
    }
} 