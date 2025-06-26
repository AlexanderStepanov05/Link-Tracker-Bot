package edu.java.scrapper.service;

import edu.java.scrapper.dto.Link;
import edu.java.scrapper.repository.LinkRepository;
import edu.java.scrapper.repository.TgChatRepository;
import edu.java.scrapper.repository.ChatLinkRepository;
import java.net.URI;
import java.util.Collection;
import java.util.List;

public class JdbcLinkService implements LinkService {
    private final LinkRepository linkRepository;
    private final TgChatRepository tgChatRepository;
    private final ChatLinkRepository chatLinkRepository;

    public JdbcLinkService(LinkRepository linkRepository, TgChatRepository tgChatRepository, ChatLinkRepository chatLinkRepository) {
        this.linkRepository = linkRepository;
        this.tgChatRepository = tgChatRepository;
        this.chatLinkRepository = chatLinkRepository;
    }

    @Override
    public Link add(long tgChatId, URI url) {
        Link link = linkRepository.add(url);
        chatLinkRepository.add(tgChatId, link.id());
        return link;
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        Link link = linkRepository.remove(url);
        chatLinkRepository.remove(tgChatId, link.id());
        return link;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        List<Long> linkIds = chatLinkRepository.findLinksByChat(tgChatId);
        return linkRepository.findAll().stream().filter(l -> linkIds.contains(l.id())).toList();
    }
} 