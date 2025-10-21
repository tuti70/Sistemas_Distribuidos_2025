package com.otuti.conversor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando não é encontrada uma taxa de conversão
 * para as moedas solicitadas
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConversionRateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConversionRateNotFoundException(String message) {
        super(message);
    }
}