package com.otuti.conversor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal que inicia a aplicação Spring Boot
 * 
 * A anotação @SpringBootApplication equivale a:
 * - @Configuration: Define classe de configuração
 * - @EnableAutoConfiguration: Habilita auto-configuração do Spring Boot
 * - @ComponentScan: Escaneia componentes nos pacotes abaixo de
 * com.otuti.conversor
 */
@SpringBootApplication
public class ConversorApplication {

	/**
	 * Método main - ponto de entrada da aplicação
	 * 
	 * @param args Argumentos de linha de comando
	 */
	public static void main(String[] args) {
		// Inicializa o contexto Spring e sobe o servidor embutido
		SpringApplication.run(ConversorApplication.class, args);
	}
}