package com.yaminsky.bankspringhibernate.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }
}