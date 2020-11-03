package com.pl.api.handler;

import com.pl.exception.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileException.class)
    protected ResponseEntity<Object> fileExceptionHandler(FileException ex) {

        return new ResponseEntity<>(ex.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
