package ru.timestop.exceptions;

public class WebApiException extends RuntimeException {
    public WebApiException(Throwable e) {
        super(e);
    }

    public WebApiException(String msg) {
        super(msg);
    }
}
