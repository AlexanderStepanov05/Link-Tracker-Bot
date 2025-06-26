package edu.java.scrapper.repository.jpa;

import edu.java.scrapper.domain.entity.ChatLinkEntity;
import edu.java.scrapper.domain.entity.ChatLinkEntity.ChatLinkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaChatLinkRepository extends JpaRepository<ChatLinkEntity, ChatLinkId> {
    List<ChatLinkEntity> findByIdChatId(Long chatId);
    List<ChatLinkEntity> findByIdLinkId(Long linkId);
} 