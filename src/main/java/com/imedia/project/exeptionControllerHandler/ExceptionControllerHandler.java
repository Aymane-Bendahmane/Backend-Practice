package com.imedia.project.exeptionControllerHandler;

import com.imedia.project.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ExceptionHandler.class})
    protected ResponseEntity<Object> handleConflict(ExceptionHandler ex, WebRequest request) {
        logger.error("Exception has been thrown : {} ", ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getHttpStatus(), request);

    }

}
