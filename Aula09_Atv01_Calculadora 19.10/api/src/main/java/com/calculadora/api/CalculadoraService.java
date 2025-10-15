package com.calculadora.api;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    // 1) Subtração
    public double subtrair(double n1, double n2) {
        return n1 - n2;
    }

    // 2) Multiplicação
    public double multiplicar(double n1, double n2) {
        return n1 * n2;
    }

    // 3) Divisão
    public double dividir(double n1, double n2) {
        if (n2 == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida.");
        }
        return n1 / n2;
    }

    // 4) Raiz Quadrada
    public double raizQuadrada(double numero) {
        return Math.sqrt(numero);
    }

    // 5) Raiz Cúbica
    public double raizCubica(double numero) {
        return Math.cbrt(numero);
    }

    // 6) Potência
    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }

    // 7) Converter para Binário
    public String paraBinario(int numero) {
        return Integer.toBinaryString(numero);
    }

    // 8) Dez elevado a uma potência
    public double dezElevadoA(double expoente) {
        return Math.pow(10, expoente);
    }

    // 9) Fatorial
    public long fatorial(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Fatorial não é definido para números negativos.");
        }
        if (numero == 0) {
            return 1;
        }
        long resultado = 1;
        for (int i = 1; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // 10) Valor de PI
    public double valorDePi() {
        return Math.PI;
    }
}