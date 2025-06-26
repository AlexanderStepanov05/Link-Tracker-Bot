package edu.java.scrapper.exception;

public class LinkAlreadyTrackedException extends RuntimeException {
    public LinkAlreadyTrackedException(String description) {
        super(description);
    }
}
