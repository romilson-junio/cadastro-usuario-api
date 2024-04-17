package com.tecnico.teste.cadastro.usuarios.handler.error;

import lombok.Getter;

@Getter
public enum HandlerError {
    VALIDATION("Erro de validação");

    HandlerError(String message) {
        this.message = message;
    }

    private final String message;

}
