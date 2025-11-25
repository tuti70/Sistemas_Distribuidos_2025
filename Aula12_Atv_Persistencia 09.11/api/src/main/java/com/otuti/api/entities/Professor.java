package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessor;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(unique = true, length = 14)
    private String cpf;

    @Column(name = "email_institucional", length = 100)
    private String emailInstitucional;

    @Column(length = 20)
    private String telefone;

    @Column(length = 50)
    private String titulacao;

    @Column(name = "area_atuacao", length = 100)
    private String areaAtuacao;

    @Column(name = "regime_trabalho", length = 20)
    private String regimeTrabalho;

    public Professor() {
    }

    public Professor(String nomeCompleto, String cpf, String emailInstitucional,
            String telefone, String titulacao, String areaAtuacao, String regimeTrabalho) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.emailInstitucional = emailInstitucional;
        this.telefone = telefone;
        this.titulacao = titulacao;
        this.areaAtuacao = areaAtuacao;
        this.regimeTrabalho = regimeTrabalho;
    }

    // Getters e Setters
    public long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Professor professor = (Professor) o;
        return Objects.equals(idProfessor, professor.idProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfessor);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "idProfessor=" + idProfessor +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", titulacao='" + titulacao + '\'' +
                '}';
    }
}