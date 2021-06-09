package com.bci.evaluation.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bci.evaluation.domain.ErrorResponse;
import com.bci.evaluation.exception.UserException;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({ UserException.class})
    public ResponseEntity<ErrorResponse> userProcessErrorHandler(UserException e) {
        
        ErrorResponse errorResponse=new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
