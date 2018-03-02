package com.github.nmirabella.quadraticsolver.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not a valid value for scale parameter")  // 404
public class InvalidScaleParameterException extends RuntimeException {
    public InvalidScaleParameterException(String message) {
        super(message);
    }
}