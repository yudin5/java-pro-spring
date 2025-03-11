package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class ServerIntegrationException extends IntegrationException {

    public ServerIntegrationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
