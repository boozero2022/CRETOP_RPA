package com.kkpc.rpa.web.exception;

public class MultiUserLoginException extends RuntimeException {

    public MultiUserLoginException(String message) {
        super(message);
    }

    public MultiUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiUserLoginException(Throwable cause) {
        super(cause);
    }
}
