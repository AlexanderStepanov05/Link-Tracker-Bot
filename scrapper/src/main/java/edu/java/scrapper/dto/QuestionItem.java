package edu.java.scrapper.dto;

import java.time.OffsetDateTime;

public record QuestionItem(
    String title,
    OffsetDateTime lastActivityDate,
    Integer answerCount
) {}
