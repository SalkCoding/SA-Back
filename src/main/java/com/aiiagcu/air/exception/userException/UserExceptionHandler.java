package com.aiiagcu.air.exception.userException;

import com.aiiagcu.air.controller.UserController;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.InputMismatchException;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler
    public ErrorResponse EntityNotFoundException(EntityNotFoundException e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
    }

    @ExceptionHandler
    public ErrorResponse EntityExistsException(EntityExistsException e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
    }

    @ExceptionHandler
    public ErrorResponse ConstraintViolationException(ConstraintViolationException e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
    }

    @ExceptionHandler
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldError().getDefaultMessage()).build();
    }
    @ExceptionHandler
    public ErrorResponse InputMismatchException(InputMismatchException e) {
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, e.getMessage()).build();
    }
}
