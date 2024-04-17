package com.tecnico.teste.cadastro.usuarios.handler.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tecnico.teste.cadastro.usuarios.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(HttpStatus status, String message, HttpServletRequest request) {
        super();
        this.date = LocalDateTime.now();
        this.status = status.value();
        this.error = status.name();
        this.message = message;
        this.path = ResponseUtils.getFullURL(request);
    }

}