package com.example.mscustomer.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{

    private final int code;

    public ClientException(String message, int code) {
        super(message);
        this.code = code;
    }
}
