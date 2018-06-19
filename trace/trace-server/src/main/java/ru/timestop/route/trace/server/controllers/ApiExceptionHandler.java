package ru.timestop.route.trace.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.timestop.exceptions.WebApiException;
import ru.timestop.utilites.JsonUtil;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 01.08.2018.
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({WebApiException.class})
    public ResponseEntity<String> handlerApiException(WebApiException e) {
        return new ResponseEntity<>(JsonUtil.toJson(e), HttpStatus.I_AM_A_TEAPOT);
    }
}
