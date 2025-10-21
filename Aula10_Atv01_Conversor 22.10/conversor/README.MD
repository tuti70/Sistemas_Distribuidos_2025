# 💱 Conversor de Moedas API

Uma API RESTful para conversão de moedas desenvolvida em Java 21 com Spring Boot, seguindo os princípios de sistemas distribuídos.

## 👨‍💻 Desenvolvedor

**Arthur Renato Normando Vasconcelos**  
*Aluno do 6º período de TSI*  
**Disciplina:** Sistemas Distribuídos

## 🚀 Como Executar

```bash
# Compilar e executar
mvn clean spring-boot:run

# Ou gerar JAR
mvn clean package
java -jar target/conversor-1.0.0.jar
```

A API estará disponível em: `http://localhost:8080`

## 📡 Endpoints

### 💰 Conversão de Moedas
**GET** `/api/convert/currency?from=USD&to=BRL&amount=100`

**Parâmetros:**
- `from`: Moeda origem (USD, BRL, EUR)
- `to`: Moeda destino (USD, BRL, EUR)  
- `amount`: Valor a converter

**Moedas Suportadas:** USD, BRL, EUR

**Exemplo:**
```bash
curl "http://localhost:8080/api/convert/currency?from=USD&to=BRL&amount=100"
# Resposta: 550.0
```

## 🛡️ Tratamento de Erros

A API retorna respostas padronizadas para erros:

```json
{
    "timestamp": "2024-01-15T10:30:00.000Z",
    "message": "Moeda não suportada: XYZ",
    "details": "uri=/api/convert/currency?from=XYZ&to=BRL&amount=100"
}
```

**Códigos de Status:**
- `400` - Moeda não suportada ou valor negativo
- `404` - Taxa de conversão não encontrada  
- `500` - Erro interno do servidor

## 🏗️ Estrutura do Projeto

```
com.otuti.conversor/
├── ConversorApplication.java          # Classe principal
├── controller/
│   └── ConversionController.java      # Endpoints REST
├── service/
│   ├── ConversionService.java         # Lógica de negócio
│   └── ConversionValidator.java       # Validações
├── exceptions/                        # Exceções personalizadas
├── handler/                           # Tratamento global de erros
└── model/                             # Modelos de dados
```

## 🧪 Exemplos de Uso

### ✅ Conversões Válidas
```bash
# USD para BRL
curl "http://localhost:8080/api/convert/currency?from=USD&to=BRL&amount=100"

# EUR para USD
curl "http://localhost:8080/api/convert/currency?from=EUR&to=USD&amount=50"

# Mesma moeda
curl "http://localhost:8080/api/convert/currency?from=BRL&to=BRL&amount=100"
```

### ❌ Casos de Erro
```bash
# Moeda não suportada
curl "http://localhost:8080/api/convert/currency?from=JPY&to=BRL&amount=100"

# Valor negativo
curl "http://localhost:8080/api/convert/currency?from=USD&to=BRL&amount=-50"

# Valor muito alto
curl "http://localhost:8080/api/convert/currency?from=USD&to=BRL&amount=2000000"
```

## 📋 Tecnologias

- **Java 21**
- **Spring Boot 3.2.0**
- **Maven**
- **Spring Web**

---

**🎓 Projeto Acadêmico** - Desenvolvido para a disciplina de Sistemas Distribuídos  
**Professor:** Dr. Fernando Barbosa Matos