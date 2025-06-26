package edu.java.scrapper.service;

import edu.java.scrapper.dto.LinkUpdate;
import edu.java.scrapper.dto.request.LinkUpdateRequest;
import edu.java.scrapper.appclients.BotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.use-queue", havingValue = "false", matchIfMissing = true)
@RequiredArgsConstructor
public class HttpNotificationSender implements NotificationSender {
    private final BotClient botClient;
    @Override
    public void send(LinkUpdate update) {
        LinkUpdateRequest request = new LinkUpdateRequest(
            update.url(),
            update.description(),
            update.changes().stream()
                .map(Long::parseLong)
                .toList()
        );
        botClient.sendUpdate(request);
    }
} 