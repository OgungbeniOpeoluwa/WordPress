package org.example.exception;

public class InvalidDetailsFormat extends WordPressException{
    public InvalidDetailsFormat(String message) {
        super(message);
    }
}
