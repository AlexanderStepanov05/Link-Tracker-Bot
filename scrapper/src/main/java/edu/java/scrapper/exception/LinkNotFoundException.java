package edu.java.scrapper.exception;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String description) {
        super(description);
    }
}
