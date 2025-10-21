package com.otuti.conversor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma moeda não é suportada pela API
 * Exemplo: Tentar converter de "XYZ" para "BRL"
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedCurrencyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnsupportedCurrencyException(String message) {
        super(message);
    }
}