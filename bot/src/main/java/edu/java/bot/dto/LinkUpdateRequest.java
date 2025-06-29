package edu.java.bot.dto;

import java.net.URI;
import java.util.List;

public record LinkUpdateRequest(
    URI url,
    String description,
    List<Long> tgChatIds
) {}
