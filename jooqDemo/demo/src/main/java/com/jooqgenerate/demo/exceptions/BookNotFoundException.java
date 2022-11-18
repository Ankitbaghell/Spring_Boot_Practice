package com.jooqgenerate.demo.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }


}
