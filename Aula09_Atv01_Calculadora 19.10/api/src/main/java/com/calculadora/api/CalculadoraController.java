package com.calculadora.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora") // Todos os endpoints começarão com /calculadora
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    // Ex: http://localhost:8080/calculadora/subtrair?n1=10&n2=7
    @GetMapping("/subtrair")
    public double subtrair(@RequestParam double n1, @RequestParam double n2) {
        return calculadoraService.subtrair(n1, n2);
    }

    // Ex: http://localhost:8080/calculadora/multiplicar?n1=5&n2=4
    @GetMapping("/multiplicar")
    public double multiplicar(@RequestParam double n1, @RequestParam double n2) {
        return calculadoraService.multiplicar(n1, n2);
    }

    // Ex: http://localhost:8080/calculadora/dividir?n1=20&n2=4
    @GetMapping("/dividir")
    public double dividir(@RequestParam double n1, @RequestParam double n2) {
        return calculadoraService.dividir(n1, n2);
    }

    // Ex: http://localhost:8080/calculadora/raiz-quadrada?numero=81
    @GetMapping("/raiz-quadrada")
    public double raizQuadrada(@RequestParam double numero) {
        return calculadoraService.raizQuadrada(numero);
    }

    // Ex: http://localhost:8080/calculadora/raiz-cubica?numero=27
    @GetMapping("/raiz-cubica")
    public double raizCubica(@RequestParam double numero) {
        return calculadoraService.raizCubica(numero);
    }

    // Ex: http://localhost:8080/calculadora/potencia?base=2&expoente=10
    @GetMapping("/potencia")
    public double potencia(@RequestParam double base, @RequestParam double expoente) {
        return calculadoraService.potencia(base, expoente);
    }

    // Ex: http://localhost:8080/calculadora/binario?numero=13
    @GetMapping("/binario")
    public String paraBinario(@RequestParam int numero) {
        return calculadoraService.paraBinario(numero);
    }

    // Ex: http://localhost:8080/calculadora/dez-elevado?expoente=3
    @GetMapping("/dez-elevado")
    public double dezElevadoA(@RequestParam double expoente) {
        return calculadoraService.dezElevadoA(expoente);
    }
    
    // Ex: http://localhost:8080/calculadora/fatorial?numero=5
    @GetMapping("/fatorial")
    public long fatorial(@RequestParam int numero) {
        return calculadoraService.fatorial(numero);
    }

    // Ex: http://localhost:8080/calculadora/pi
    @GetMapping("/pi")
    public double valorDePi() {
        return calculadoraService.valorDePi();
    }
}