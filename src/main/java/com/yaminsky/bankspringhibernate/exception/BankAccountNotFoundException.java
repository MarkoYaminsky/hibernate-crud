package com.yaminsky.bankspringhibernate.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
