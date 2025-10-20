package com.otuti.conversor.service;

import org.springframework.stereotype.Component;
import com.otuti.conversor.exceptions.*;

/**
 * Classe responsável por validar os parâmetros de entrada
 * Implementa as regras de negócio para conversões
 */
@Component
public class ConversionValidator {

    // Moedas suportadas pela API
    private static final String[] SUPPORTED_CURRENCIES = { "USD", "BRL", "EUR" };
    // Unidades de temperatura suportadas
    private static final String[] SUPPORTED_TEMP_UNITS = { "C", "F", "K" };

    /**
     * Valida se uma moeda é suportada pela API
     * 
     * @param currency Código da moeda a ser validada
     * @throws UnsupportedUnitException Se a moeda não for suportada
     */
    public void validateCurrency(String currency) {
        boolean supported = false;
        for (String supportedCurrency : SUPPORTED_CURRENCIES) {
            if (supportedCurrency.equalsIgnoreCase(currency)) {
                supported = true;
                break;
            }
        }
        if (!supported) {
            throw new UnsupportedUnitException("Moeda não suportada: " + currency +
                    ". Moedas suportadas: USD, BRL, EUR");
        }
    }

    /**
     * Valida se uma unidade de temperatura é suportada
     * 
     * @param unit Unidade de temperatura a ser validada
     * @throws UnsupportedUnitException Se a unidade não for suportada
     */
    public void validateTemperatureUnit(String unit) {
        boolean supported = false;
        for (String supportedUnit : SUPPORTED_TEMP_UNITS) {
            if (supportedUnit.equalsIgnoreCase(unit)) {
                supported = true;
                break;
            }
        }
        if (!supported) {
            throw new UnsupportedUnitException("Unidade de temperatura não suportada: " + unit +
                    ". Unidades suportadas: C, F, K");
        }
    }

    /**
     * Valida se o valor é positivo
     * 
     * @param amount Valor a ser validado
     * @throws NegativeAmountNotAllowedException Se o valor for negativo
     */
    public void validatePositiveAmount(Double amount) {
        if (amount < 0) {
            throw new NegativeAmountNotAllowedException("Valor não pode ser negativo: " + amount);
        }
    }

    /**
     * Valida limites máximos para conversão
     * 
     * @param amount Valor a ser validado
     * @throws NegativeAmountNotAllowedException Se exceder o limite
     */
    public void validateAmountLimit(Double amount) {
        if (amount > 1000000) {
            throw new NegativeAmountNotAllowedException(
                    "Valor excede o limite máximo de 1.000.000: " + amount);
        }
    }
}