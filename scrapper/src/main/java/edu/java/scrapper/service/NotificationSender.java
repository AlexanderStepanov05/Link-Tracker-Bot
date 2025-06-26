package edu.java.scrapper.service;

import edu.java.scrapper.dto.LinkUpdate;

public interface NotificationSender {
    void send(LinkUpdate update);
} 