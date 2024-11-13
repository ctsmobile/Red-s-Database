package com.cts.redplastring.redplastringapplication.exception;

import com.cts.redplastring.redplastringapplication.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleResourceNotFound(final ValidationException exception,
                                                    final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        error.setFrontendMessage(exception.getFrontendMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}


