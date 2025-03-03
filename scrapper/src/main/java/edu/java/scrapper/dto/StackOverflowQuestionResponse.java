package edu.java.scrapper.dto;

import java.util.List;

public record StackOverflowQuestionResponse(
    List<QuestionItem> items
) {}

