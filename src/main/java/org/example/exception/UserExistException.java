package org.example.exception;

public class UserExistException extends WordPressException {
    public UserExistException(String message) {
        super(message);
    }
}
