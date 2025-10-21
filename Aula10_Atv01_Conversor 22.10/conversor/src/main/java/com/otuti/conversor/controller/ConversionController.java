package com.otuti.conversor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.otuti.conversor.service.ConversionService;

/**
 * Controller REST que expõe os endpoints da API de conversão de moedas
 */
@RestController
@RequestMapping("/api/convert")
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    /**
     * Endpoint para conversão de moedas
     * 
     * @param from   Moeda de origem (USD, BRL, EUR)
     * @param to     Moeda de destino (USD, BRL, EUR)
     * @param amount Valor a ser convertido
     * @return Valor convertido
     * 
     *         Exemplo: GET /api/convert/currency?from=USD&to=BRL&amount=100
     */
    @GetMapping("/currency")
    public Double convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam Double amount) {

        return conversionService.convertCurrency(from, to, amount);
    }
}