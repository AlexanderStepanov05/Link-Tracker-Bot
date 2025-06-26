package edu.java.scrapper.repository;

import java.util.List;

public interface ChatLinkRepository {
    void add(long chatId, long linkId);
    void remove(long chatId, long linkId);
    List<Long> findLinksByChat(long chatId);
    List<Long> findChatsByLink(long linkId);
} 