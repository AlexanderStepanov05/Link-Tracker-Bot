package edu.java.scrapper.service;

import edu.java.scrapper.domain.entity.LinkEntity;
import edu.java.scrapper.domain.entity.TgChatEntity;
import edu.java.scrapper.domain.entity.ChatLinkEntity;
import edu.java.scrapper.domain.entity.ChatLinkEntity.ChatLinkId;
import edu.java.scrapper.dto.Link;
import edu.java.scrapper.repository.jpa.JpaLinkRepository;
import edu.java.scrapper.repository.jpa.JpaTgChatRepository;
import edu.java.scrapper.repository.jpa.JpaChatLinkRepository;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JpaLinkService implements LinkService {
    private final JpaLinkRepository linkRepository;
    private final JpaTgChatRepository tgChatRepository;
    private final JpaChatLinkRepository chatLinkRepository;

    public JpaLinkService(JpaLinkRepository linkRepository, JpaTgChatRepository tgChatRepository, JpaChatLinkRepository chatLinkRepository) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
        this.chatLinkRepository = chatLinkRepository;
    }

    @Override
    public Link add(long tgChatId, URI url) {
        LinkEntity link = linkRepository.findByUrl(url.toString())
            .orElseGet(() -> {
                LinkEntity newLink = new LinkEntity();
                newLink.setUrl(url.toString());
                newLink.setCreatedAt(OffsetDateTime.now());
                newLink.setLastCheckedAt(OffsetDateTime.now());
                return linkRepository.save(newLink);
            });
        
        ChatLinkEntity chatLink = new ChatLinkEntity();
        chatLink.setId(new ChatLinkEntity.ChatLinkId(tgChatId, link.getId()));
        chatLinkRepository.save(chatLink);
        
        return toDto(link);
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        LinkEntity link = linkRepository.findByUrl(url.toString()).orElseThrow();
        chatLinkRepository.deleteById(new ChatLinkId(tgChatId, link.getId()));
        return toDto(link);
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        List<ChatLinkEntity> chatLinks = chatLinkRepository.findByIdChatId(tgChatId);
        return chatLinks.stream()
            .map(cl -> linkRepository.findById(cl.getId().getLinkId()).orElse(null))
            .filter(l -> l != null)
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private Link toDto(LinkEntity entity) {
        return new Link(entity.getId(), URI.create(entity.getUrl()), entity.getLastCheckedAt(), entity.getCreatedAt());
    }
} 