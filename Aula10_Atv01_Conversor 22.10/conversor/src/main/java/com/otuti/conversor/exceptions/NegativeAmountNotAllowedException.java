package com.otuti.conversor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando valores negativos são informados
 * Aplicável para valores monetários e algumas conversões de unidades
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeAmountNotAllowedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegativeAmountNotAllowedException(String message) {
        super(message);
    }
}