package com.mana.bookshelf.exception.handler;

import com.mana.bookshelf.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> entityNotFound(EntityNotFoundException e) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
//
//    @ExceptionHandler({IllegalArgumentException.class})
//    public ResponseEntity<ApiError> illegalArgument(IllegalArgumentException e) {
//        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler({IllegalStateException.class})
//    public ResponseEntity<ApiError> illegalState(IllegalStateException e) {
//        return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT.value(), e.getMessage()), HttpStatus.CONFLICT);
//    }
}
