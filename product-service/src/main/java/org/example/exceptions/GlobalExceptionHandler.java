package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public IntegrationException handle(IntegrationException integrationException) {
        return new IntegrationException(
                integrationException.getMessage(),
                integrationException.getHttpStatus());
    }

    // Дефолтный обработчик для всех остальных исключений
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public IntegrationException handleCommonException(Exception exception) {
        return new IntegrationException(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
