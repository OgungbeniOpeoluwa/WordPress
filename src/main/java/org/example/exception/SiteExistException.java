package org.example.exception;

public class SiteExistException extends WordPressException {
    public SiteExistException(String message) {
        super(message);
    }
}
