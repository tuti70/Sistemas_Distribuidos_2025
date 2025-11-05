package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idFuncionario;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(unique = true)
    private String cpf;

    private String emailInstitucional;
    private String telefone;
    private String cargo;
    private String tipoVinculo;

    public Funcionario() {
    }

    public Funcionario(String nomeCompleto, String cpf, String emailInstitucional,
            String telefone, String cargo, String tipoVinculo) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.emailInstitucional = emailInstitucional;
        this.telefone = telefone;
        this.cargo = cargo;
        this.tipoVinculo = tipoVinculo;
    }

    // Getters e Setters
    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(idFuncionario, that.idFuncionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFuncionario);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}