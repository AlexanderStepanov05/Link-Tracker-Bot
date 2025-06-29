package edu.java.scrapper.dto.request;

import java.net.URI;
import java.util.List;

public record LinkUpdateRequest(
    URI url,
    String description,
    List<Long> tgChatIds
) {}
