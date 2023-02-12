package com.imedia.project.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ExceptionHandler extends Exception{
    private HttpStatus httpStatus;
    public ExceptionHandler() {
    }

    public ExceptionHandler(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionHandler(Throwable cause) {
        super(cause);
    }

    public ExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
