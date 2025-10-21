package com.otuti.conversor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otuti.conversor.exceptions.UnsupportedCurrencyException;

/**
 * Serviço que contém a lógica de negócio para conversões de moedas
 */
@Service
public class ConversionService {

    @Autowired
    private ConversionValidator validator;

    // Taxas de câmbio fixas para exemplo (em produção, viriam de API externa)
    private static final double USD_TO_BRL = 5.50;
    private static final double EUR_TO_BRL = 6.20;
    private static final double USD_TO_EUR = 0.89;

    /**
     * Converte valores entre moedas diferentes
     * 
     * @param from   Moeda de origem (ex: "USD")
     * @param to     Moeda de destino (ex: "BRL")
     * @param amount Valor a ser convertido
     * @return Valor convertido
     */
    public Double convertCurrency(String from, String to, Double amount) {
        // Aplica validações de negócio
        validator.validateCurrency(from);
        validator.validateCurrency(to);
        validator.validatePositiveAmount(amount);
        validator.validateAmountLimit(amount);

        // Normaliza os códigos das moedas
        String normalizedFrom = from.toUpperCase();
        String normalizedTo = to.toUpperCase();

        // Verifica se é a mesma moeda
        if (normalizedFrom.equals(normalizedTo)) {
            return amount;
        }

        // Executa a conversão baseada nas moedas envolvidas
        String conversionKey = normalizedFrom + "_TO_" + normalizedTo;

        switch (conversionKey) {
            case "USD_TO_BRL":
                return amount * USD_TO_BRL;
            case "BRL_TO_USD":
                return amount / USD_TO_BRL;
            case "EUR_TO_BRL":
                return amount * EUR_TO_BRL;
            case "BRL_TO_EUR":
                return amount / EUR_TO_BRL;
            case "USD_TO_EUR":
                return amount * USD_TO_EUR;
            case "EUR_TO_USD":
                return amount / USD_TO_EUR;
            default:
                throw new UnsupportedCurrencyException(
                        "Conversão não suportada: " + from + " para " + to);
        }
    }
}