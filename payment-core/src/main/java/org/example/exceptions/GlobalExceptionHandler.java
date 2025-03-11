package org.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientIntegrationException.class) // Клиентские ошибки
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handle(ClientIntegrationException integrationException) {
        return new ErrorResponseDto(
                integrationException.getMessage(),
                integrationException.getHttpStatus());
    }

    @ExceptionHandler(ServerIntegrationException.class) // Серверные ошибки
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handle(ServerIntegrationException integrationException) {
        return new ErrorResponseDto(
                integrationException.getMessage(),
                integrationException.getHttpStatus());
    }

}
