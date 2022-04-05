package com.example.mscustomer.controller;

import com.example.mscustomer.exception.ClientException;
import com.example.mscustomer.exception.NotFoundException;
import com.example.mscustomer.model.constants.ExceptionConstants;
import com.example.mscustomer.model.dto.exceptionDto.DecoderExceptionDto;
import com.example.mscustomer.model.dto.exceptionDto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.mscustomer.model.constants.ExceptionConstants.*;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handle(Exception ex) {
        log.error("Exception", ex);
        return new ExceptionDto(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handle(NotFoundException ex) {
        log.error("Exception", ex);
        return new ExceptionDto(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(ClientException.class)
    public ResponseEntity<DecoderExceptionDto> handle(ClientException ex) {
        log.error("Client exception", ex);
        DecoderExceptionDto dto = new DecoderExceptionDto(ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getCode()).body(dto);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionDto handle(MethodArgumentNotValidException ex){
        log.error("Exception " ,ex);
        return  new ExceptionDto(VALIDATION_EXCEPTION_CODE,VALIDATION_EXCEPTION_MESSAGE);
    }
}
