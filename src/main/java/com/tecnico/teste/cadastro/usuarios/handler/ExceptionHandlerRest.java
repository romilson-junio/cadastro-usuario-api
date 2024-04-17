package com.tecnico.teste.cadastro.usuarios.handler;

import com.tecnico.teste.cadastro.usuarios.handler.error.HandlerError;
import com.tecnico.teste.cadastro.usuarios.handler.error.StandardError;
import com.tecnico.teste.cadastro.usuarios.handler.error.ValidationError;
import com.tecnico.teste.cadastro.usuarios.handler.exception.WebApplicationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerRest {

    @ExceptionHandler(WebApplicationException.class)
    public ResponseEntity<StandardError> errorResponse(WebApplicationException e, HttpServletRequest request) {
        StandardError err = new StandardError(e.getStatus(), e.getMessage(), request);
        return ResponseEntity.status(e.getStatus()).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST, HandlerError.VALIDATION.getMessage(), request);
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> errorResponse(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND, e.getMessage(), request);
        return ResponseEntity.status(err.getStatus()).body(err);
    }


}