package edu.java.scrapper.dto;

import java.net.URI;
import java.time.OffsetDateTime;

public record Link(
    Long id,
    URI url,
    OffsetDateTime lastCheckedAt,
    OffsetDateTime createdAt
) {} 