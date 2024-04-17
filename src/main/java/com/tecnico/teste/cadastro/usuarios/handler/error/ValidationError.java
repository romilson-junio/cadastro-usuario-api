package com.tecnico.teste.cadastro.usuarios.handler.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(HttpStatus status, String message, HttpServletRequest request) {
        super(status, message, request);
    }

    public void addError(String fieldName, String messagem) {
        errors.add(new FieldMessage(fieldName, messagem));
    }
}