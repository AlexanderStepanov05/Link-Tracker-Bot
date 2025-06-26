package edu.java.bot.service;

import edu.java.bot.dto.LinkUpdateRequest;

public interface NotificationHandler {
    void handle(LinkUpdateRequest update);
} 