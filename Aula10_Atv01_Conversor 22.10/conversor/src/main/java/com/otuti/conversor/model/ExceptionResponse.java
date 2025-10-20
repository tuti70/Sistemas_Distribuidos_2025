package com.otuti.conversor.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa uma resposta padronizada para exceções na API
 */
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timestamp; // Data/hora em que o erro ocorreu
    private String message; // Mensagem descritiva do erro
    private String details; // Detalhes adicionais (ex: URL da requisição)

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters e Setters
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}