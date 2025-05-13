package edu.java.scrapper.dto.response;

import java.net.URI;
import java.time.OffsetDateTime;

public record LinkResponse(
    long id,
    URI url,
    OffsetDateTime lastCheckTime
) {}
