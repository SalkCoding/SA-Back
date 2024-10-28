package com.aiiagcu.air.exception.calendarException;

import com.aiiagcu.air.controller.CalendarController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CalendarController.class)
public class CalendarExceptionHandler {
    
//    @ExceptionHandler
//    public ErrorResponse GeneralSecurityException(GeneralSecurityException e) {
//        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, "GeneralSecurityException").build();
//    }
//
//    @ExceptionHandler
//    public ErrorResponse IOException(IOException e) {
//        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, "IOException").build();
//    }
}
