package edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record TgChat(
    Long id,
    String username,
    OffsetDateTime createdAt
) {} 