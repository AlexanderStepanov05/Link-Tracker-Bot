package edu.java.scrapper.service;

import edu.java.scrapper.repository.TgChatRepository;

public class JdbcTgChatService implements TgChatService {
    private final TgChatRepository tgChatRepository;

    public JdbcTgChatService(TgChatRepository tgChatRepository) {
        this.tgChatRepository = tgChatRepository;
    }

    @Override
    public void register(long tgChatId) {
        tgChatRepository.add(tgChatId, "unknown");
    }

    @Override
    public void unregister(long tgChatId) {
        tgChatRepository.remove(tgChatId);
    }
} 