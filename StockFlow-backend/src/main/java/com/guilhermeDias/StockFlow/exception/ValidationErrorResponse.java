package com.guilhermeDias.StockFlow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    public ValidationErrorResponse(LocalDateTime timestamp, Integer status, String error, String message, String path, Map<String, String> erros) {
        super(timestamp, status, error, message, path);
        this.erros = erros;
    }

    private Map<String, String> erros;
}
