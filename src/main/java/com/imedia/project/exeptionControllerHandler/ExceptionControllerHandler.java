package com.imedia.project.exeptionControllerHandler;

import com.imedia.project.exceptions.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    static final String ERROR = "Exception has been thrown : {} ";
    static final String FORM_VALIDATION_ERROR = "Error when validating the form";
    Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);

    @ExceptionHandler(value = {MyException.class})
    protected ResponseEntity<Object> handleConflict(MyException ex, WebRequest request) {
        logger.error(ERROR, ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex,FORM_VALIDATION_ERROR, headers, status, request);
    }
}
