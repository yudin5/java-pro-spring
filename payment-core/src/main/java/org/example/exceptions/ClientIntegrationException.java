package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class ClientIntegrationException extends IntegrationException {

    public ClientIntegrationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
