package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDepartamento;

    @Column(nullable = false)
    private String nome;

    private String sigla;
    private String centroUnidadeAcademica;
    private String emailContato;
    private String telefoneRamal;
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "chefe_departamento_id")
    private Professor chefeDepartamento;

    public Departamento() {
    }

    public Departamento(String nome, String sigla, String centroUnidadeAcademica,
            String emailContato, String telefoneRamal, String localizacao) {
        this.nome = nome;
        this.sigla = sigla;
        this.centroUnidadeAcademica = centroUnidadeAcademica;
        this.emailContato = emailContato;
        this.telefoneRamal = telefoneRamal;
        this.localizacao = localizacao;
    }

    // Getters e Setters
    public UUID getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(UUID idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCentroUnidadeAcademica() {
        return centroUnidadeAcademica;
    }

    public void setCentroUnidadeAcademica(String centroUnidadeAcademica) {
        this.centroUnidadeAcademica = centroUnidadeAcademica;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getTelefoneRamal() {
        return telefoneRamal;
    }

    public void setTelefoneRamal(String telefoneRamal) {
        this.telefoneRamal = telefoneRamal;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Professor getChefeDepartamento() {
        return chefeDepartamento;
    }

    public void setChefeDepartamento(Professor chefeDepartamento) {
        this.chefeDepartamento = chefeDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Departamento that = (Departamento) o;
        return Objects.equals(idDepartamento, that.idDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepartamento);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "idDepartamento=" + idDepartamento +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}