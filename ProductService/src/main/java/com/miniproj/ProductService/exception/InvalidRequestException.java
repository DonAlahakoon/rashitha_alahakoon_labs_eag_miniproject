package com.miniproj.ProductService.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends RuntimeException{
    private final HttpStatus httpCode;


    public InvalidRequestException(String message, HttpStatus httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }
}
