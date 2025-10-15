package com.otuti.Idade;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@RestController
public class IdadeController {

    // Endpoint GET para calcular idade via URL
    @GetMapping("/calcular-idade")
    public RespostaIdade calcularIdadeGet(
            @RequestParam String nome,
            @RequestParam(required = false) String sobrenome,
            @RequestParam String dataNascimento) {

        PessoaRequest request = new PessoaRequest();
        request.setNome(nome);
        request.setSobrenome(sobrenome);
        request.setDataNascimento(dataNascimento);

        return new RespostaIdade(request, calcularIdadeExata(request.getDataNascimento()));
    }

    // Endpoint POST (mantido para compatibilidade)
    @PostMapping("/calcular-idade")
    public RespostaIdade calcularIdadePost(@RequestBody PessoaRequest request) {
        return new RespostaIdade(request, calcularIdadeExata(request.getDataNascimento()));
    }

    // Página inicial
    @GetMapping("/")
    public String home() {
        return "Bem-vindo ao Calculador de Idade!<br><br>" +
                "Use GET: /calcular-idade?nome=Arthur&sobrenome=Renato&dataNascimento=05/10/2001";
    }

    private Idade calcularIdadeExata(String dataNascimento) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate nascimento = LocalDate.parse(dataNascimento, formatter);
            LocalDate hoje = LocalDate.now();

            if (nascimento.isAfter(hoje)) {
                throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
            }

            Period periodo = Period.between(nascimento, hoje);

            return new Idade(periodo.getYears(), periodo.getMonths(), periodo.getDays());
        } catch (Exception e) {
            throw new IllegalArgumentException("Data inválida. Use o formato dd/MM/yyyy");
        }
    }

    public static class PessoaRequest {
        private String nome;
        private String sobrenome;
        private String dataNascimento;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
    }

    public static class Idade {
        private int anos;
        private int meses;
        private int dias;

        public Idade(int anos, int meses, int dias) {
            this.anos = anos;
            this.meses = meses;
            this.dias = dias;
        }

        public int getAnos() {
            return anos;
        }

        public int getMeses() {
            return meses;
        }

        public int getDias() {
            return dias;
        }
    }

    public static class RespostaIdade {
        private String nomeCompleto;
        private Idade idade;

        public RespostaIdade(PessoaRequest request, Idade idade) {
            this.nomeCompleto = request.getSobrenome() != null && !request.getSobrenome().isEmpty()
                    ? request.getNome() + " " + request.getSobrenome()
                    : request.getNome();
            this.idade = idade;
        }

        public String getNomeCompleto() {
            return nomeCompleto;
        }

        public Idade getIdade() {
            return idade;
        }
    }
}