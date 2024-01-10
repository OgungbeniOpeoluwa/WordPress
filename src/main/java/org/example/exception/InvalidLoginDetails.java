package org.example.exception;

public class InvalidLoginDetails  extends  WordPressException{
    public InvalidLoginDetails(String message) {
        super(message);
    }
}
