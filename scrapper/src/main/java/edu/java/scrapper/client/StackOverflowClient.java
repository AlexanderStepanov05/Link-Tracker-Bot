package edu.java.scrapper.client;

import edu.java.scrapper.dto.StackOverflowQuestionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface StackOverflowClient {
    @GetExchange("/2.3/questions/{id}")
    StackOverflowQuestionResponse fetchQuestion(
        @PathVariable("id") String questionId,
        @RequestParam("site") String site
    );
}
