package com.agendas.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RestController
public class   ResponseExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ExceptionResponse> manejarModeloNotFoundException(ModeloNotFoundException ex, WebRequest request){
        log.info("ModeloNotFoundException : {}", ex.getMessage());

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e ->
              e.getDefaultMessage().concat(", ")
        ).collect(Collectors.joining());
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), mensaje, request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasExcepciones(Exception ex, WebRequest request){
        log.info("manejarTodasExcepciones : {}", ex.getMessage());

        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}