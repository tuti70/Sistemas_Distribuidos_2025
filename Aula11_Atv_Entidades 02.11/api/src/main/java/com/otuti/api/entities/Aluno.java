package com.otuti.api.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAluno;

    @Column(nullable = false)
    private String nomeCompleto;

    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String matricula;

    private String emailInstitucional;
    private String telefone;

    public Aluno() {
    }

    public Aluno(String nomeCompleto, LocalDate dataNascimento, String cpf,
            String matricula, String emailInstitucional, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.matricula = matricula;
        this.emailInstitucional = emailInstitucional;
        this.telefone = telefone;
    }

    // Getters e Setters
    public UUID getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(UUID idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(idAluno, aluno.idAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAluno);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "idAluno=" + idAluno +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}