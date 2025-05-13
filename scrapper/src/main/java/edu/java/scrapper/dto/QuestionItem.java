package edu.java.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record QuestionItem(
    @JsonProperty("question_id") long questionId,
    @JsonProperty("last_activity_date")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    OffsetDateTime lastActivityDate
) {}
