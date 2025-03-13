package org.example.exceptions;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(String message, HttpStatus externalSystemCode) {
}
