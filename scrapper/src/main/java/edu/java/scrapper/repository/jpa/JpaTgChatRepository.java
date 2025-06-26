package edu.java.scrapper.repository.jpa;

import edu.java.scrapper.domain.entity.TgChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTgChatRepository extends JpaRepository<TgChatEntity, Long> {
} 