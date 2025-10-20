package com.otuti.conversor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otuti.conversor.exceptions.*;

/**
 * Classe de serviço que contém a lógica de negócio para conversões
 * Segrega a regra de negócio do controller conforme demonstrado em aula
 */
@Service
public class ConversionService {

    @Autowired
    private ConversionValidator validator;

    // Taxas de câmbio fixas para exemplo (em produção, viriam de uma API externa)
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
                throw new UnsupportedUnitException(
                        "Conversão não suportada: " + from + " para " + to);
        }
    }

    /**
     * Converte valores entre unidades de temperatura
     * 
     * @param from  Unidade de origem (ex: "C")
     * @param to    Unidade de destino (ex: "F")
     * @param value Valor a ser convertido
     * @return Valor convertido
     */
    public Double convertTemperature(String from, String to, Double value) {
        // Aplica validações
        validator.validateTemperatureUnit(from);
        validator.validateTemperatureUnit(to);

        // Normaliza as unidades
        String normalizedFrom = from.toUpperCase();
        String normalizedTo = to.toUpperCase();

        // Verifica se é a mesma unidade
        if (normalizedFrom.equals(normalizedTo)) {
            return value;
        }

        // Validação específica para temperatura em Celsius
        if (normalizedFrom.equals("C") && value < -273.15) {
            throw new NegativeAmountNotAllowedException(
                    "Temperatura em Celsius não pode ser inferior ao zero absoluto (-273.15°C)");
        }

        // Validação específica para temperatura em Kelvin
        if (normalizedFrom.equals("K") && value < 0) {
            throw new NegativeAmountNotAllowedException(
                    "Temperatura em Kelvin não pode ser negativa");
        }

        // Executa a conversão de temperatura
        String conversionKey = normalizedFrom + "_TO_" + normalizedTo;

        switch (conversionKey) {
            case "C_TO_F":
                return (value * 9 / 5) + 32;
            case "F_TO_C":
                return (value - 32) * 5 / 9;
            case "C_TO_K":
                return value + 273.15;
            case "K_TO_C":
                return value - 273.15;
            case "F_TO_K":
                return ((value - 32) * 5 / 9) + 273.15;
            case "K_TO_F":
                return ((value - 273.15) * 9 / 5) + 32;
            default:
                throw new UnsupportedUnitException(
                        "Conversão de temperatura não suportada: " + from + " para " + to);
        }
    }
}