package com.realtime.api.realtimeapp.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseApiException extends RuntimeException{

    public BaseApiException(String message) {
        super(message);
    }
    public abstract HttpStatus getStatus();
}
