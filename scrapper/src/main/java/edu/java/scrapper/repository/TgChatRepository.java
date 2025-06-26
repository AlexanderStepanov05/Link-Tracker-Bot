package edu.java.scrapper.repository;

import edu.java.scrapper.dto.TgChat;
import java.util.List;

public interface TgChatRepository {
    TgChat add(Long id, String username);
    TgChat remove(Long id);
    List<TgChat> findAll();
} 