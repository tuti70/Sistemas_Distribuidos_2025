package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "universidades")
public class Universidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUniversidade;

    @Column(name = "nome_universidade", nullable = false, length = 200)
    private String nomeUniversidade;

    @Column(name = "sigla_universidade", length = 20)
    private String siglaUniversidade;

    @Column(length = 20)
    private String tipo; // pública ou privada

    @OneToMany(mappedBy = "universidade", cascade = CascadeType.ALL)
    private List<Departamento> departamentos = new ArrayList<>();

    public Universidade() {
    }

    public Universidade(String nomeUniversidade, String siglaUniversidade, String tipo) {
        this.nomeUniversidade = nomeUniversidade;
        this.siglaUniversidade = siglaUniversidade;
        this.tipo = tipo;
    }

    // Getters e Setters
    public UUID getIdUniversidade() {
        return idUniversidade;
    }

    public void setIdUniversidade(UUID idUniversidade) {
        this.idUniversidade = idUniversidade;
    }

    public String getNomeUniversidade() {
        return nomeUniversidade;
    }

    public void setNomeUniversidade(String nomeUniversidade) {
        this.nomeUniversidade = nomeUniversidade;
    }

    public String getSiglaUniversidade() {
        return siglaUniversidade;
    }

    public void setSiglaUniversidade(String siglaUniversidade) {
        this.siglaUniversidade = siglaUniversidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Universidade that = (Universidade) o;
        return Objects.equals(idUniversidade, that.idUniversidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUniversidade);
    }

    @Override
    public String toString() {
        return "Universidade{" +
                "idUniversidade=" + idUniversidade +
                ", nomeUniversidade='" + nomeUniversidade + '\'' +
                ", siglaUniversidade='" + siglaUniversidade + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}