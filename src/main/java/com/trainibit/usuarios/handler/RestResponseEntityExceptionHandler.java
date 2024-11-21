package com.trainibit.usuarios.handler;

import com.trainibit.usuarios.response.ApiErrorResponse;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalAccessException.class})
    protected ResponseEntity<Object> handlerConflict(IllegalAccessException ex, WebRequest request) {
        ApiErrorResponse bodyOfResponse = new ApiErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
