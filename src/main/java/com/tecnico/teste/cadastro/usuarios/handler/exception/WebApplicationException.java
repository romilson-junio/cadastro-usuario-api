package com.tecnico.teste.cadastro.usuarios.handler.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebApplicationException extends RuntimeException {

    private final HttpStatus status;

    public WebApplicationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public WebApplicationException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
