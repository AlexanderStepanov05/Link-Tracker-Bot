package edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record GithubRepoResponse (
    String name,
    OffsetDateTime updatedAt,
    String description
) {}
