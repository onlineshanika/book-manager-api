package com.techreturners.bookmanager.exception;

public class BookAlreadyExistsException extends Throwable {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
