package edu.java.scrapper.exception;

import edu.java.scrapper.dto.response.ApiErrorResponse;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateChatException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateChat(DuplicateChatException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(createErrorResponse(ex, "Chat already exists"));
    }

    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleChatNotFound(ChatNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(createErrorResponse(ex, "Chat not found"));
    }

    private ApiErrorResponse createErrorResponse(Exception ex, String description) {
        return new ApiErrorResponse(
            description,
            String.valueOf(HttpStatus.BAD_REQUEST.value()),
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .toList()
        );
    }
}
