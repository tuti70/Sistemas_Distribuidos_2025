package com.otuti.conversor.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.otuti.conversor.exceptions.*;
import com.otuti.conversor.model.ExceptionResponse;

/**
 * Classe centralizada para tratamento de exceções em toda a aplicação
 * Segue o padrão ControllerAdvice demonstrado em aula
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Manipula todas as exceções genéricas não tratadas especificamente
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manipula exceções de unidades não suportadas
    @ExceptionHandler(UnsupportedUnitException.class)
    public final ResponseEntity<ExceptionResponse> handleUnsupportedUnitExceptions(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Manipula exceções de valores negativos
    @ExceptionHandler(NegativeAmountNotAllowedException.class)
    public final ResponseEntity<ExceptionResponse> handleNegativeAmountExceptions(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Manipula exceções de taxas de conversão não encontradas
    @ExceptionHandler(ConversionRateNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleConversionRateExceptions(
            Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}