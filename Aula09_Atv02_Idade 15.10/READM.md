# Calculadora de Idade - Spring Boot

Uma aplicação simples para calcular idade a partir da data de nascimento.

## 🚀 Como usar

### Via URL (GET)
Acesse diretamente pelo navegador:

```
http://localhost:8080/calcular-idade?nome=Arthur&sobrenome=Renato&dataNascimento=05/10/2001
```

**Parâmetros:**
- `nome` (obrigatório): Primeiro nome
- `sobrenome` (opcional): Sobrenome
- `dataNascimento` (obrigatório): Data no formato dd/MM/yyyy

### Exemplos:

**Com nome e sobrenome:**
```
http://localhost:8080/calcular-idade?nome=Maria&sobrenome=Silva&dataNascimento=15/03/1990
```

**Apenas nome:**
```
http://localhost:8080/calcular-idade?nome=João&dataNascimento=20/08/1985
```

## 📋 Resposta

A aplicação retorna um JSON com:
```json
{
    "nomeCompleto": "Arthur Renato",
    "idade": {
        "anos": 23,
        "meses": 0,
        "dias": 10
    }
}
```

## 🛠 Tecnologias

- Java 17
- Spring Boot 3.2.0
- Maven

## ▶️ Executar

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📝 Notas

- Formato de data: **dd/MM/yyyy**
- Cálculo preciso de anos, meses e dias
- Sobrenome é opcional

## 👥 ALUNOS

**Arthur Renato Normando Vasconcelos**  
**Bruno Vaz Ferreira**

---

Desenvolvido como projeto acadêmico para cálculo de idades utilizando Spring Boot.