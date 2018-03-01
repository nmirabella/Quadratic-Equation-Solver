package com.github.nmirabella.quadraticsolver.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not a valid quadratic equation")  // 404
public class NotQuadraticException extends RuntimeException {
    public NotQuadraticException(String message) {
        super(message);
    }
}