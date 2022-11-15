package com.yaminsky.bankspringhibernate.exception;

public class ContinentNotFoundException extends RuntimeException {
    public ContinentNotFoundException(String message) {
        super(message);
    }
}
