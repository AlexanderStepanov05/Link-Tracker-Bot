package edu.java.scrapper.service;

import edu.java.scrapper.domain.entity.TgChatEntity;
import edu.java.scrapper.repository.jpa.JpaTgChatRepository;
import java.time.OffsetDateTime;

public class JpaTgChatService implements TgChatService {
    private final JpaTgChatRepository tgChatRepository;

    public JpaTgChatService(JpaTgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public void register(long tgChatId) {
        if (!tgChatRepository.existsById(tgChatId)) {
            TgChatEntity chat = new TgChatEntity();
            chat.setId(tgChatId);
            chat.setUsername("unknown");
            chat.setCreatedAt(OffsetDateTime.now());
            tgChatRepository.save(chat);
        }
    }

    @Override
    public void unregister(long tgChatId) {
        tgChatRepository.deleteById(tgChatId);
    }
} 