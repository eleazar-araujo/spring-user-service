package com.alejandro.userservice.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GeneralErrorResponse> handleException(UserNotFoundException exception){
        GeneralErrorResponse responseContent = new GeneralErrorResponse(HttpStatus.NOT_FOUND.value(),
                exception.getMessage(), false);
        return new ResponseEntity<>(responseContent, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GeneralErrorResponse> handleException(Exception exception){
        GeneralErrorResponse responseContent = new GeneralErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(), false);
        return new ResponseEntity<>(responseContent, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
