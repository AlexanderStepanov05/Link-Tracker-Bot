package edu.java.scrapper.client;

import edu.java.scrapper.dto.StackOverflowQuestionResponse;

public interface StackOverflowClient {
    StackOverflowQuestionResponse fetchQuestion(long questionId);
}
