package edu.java.scrapper.configuration;

import edu.java.scrapper.repository.jpa.JpaLinkRepository;
import edu.java.scrapper.repository.jpa.JpaTgChatRepository;
import edu.java.scrapper.repository.jpa.JpaChatLinkRepository;
import edu.java.scrapper.service.JpaLinkService;
import edu.java.scrapper.service.JpaTgChatService;
import edu.java.scrapper.service.LinkService;
import edu.java.scrapper.service.TgChatService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {
    @Bean
    public LinkService linkService(JpaLinkRepository linkRepository, JpaTgChatRepository tgChatRepository, JpaChatLinkRepository chatLinkRepository) {
        return new JpaLinkService(linkRepository, tgChatRepository, chatLinkRepository);
    }

    @Bean
    public TgChatService tgChatService(JpaTgChatRepository tgChatRepository) {
        return new JpaTgChatService(tgChatRepository);
    }
} 