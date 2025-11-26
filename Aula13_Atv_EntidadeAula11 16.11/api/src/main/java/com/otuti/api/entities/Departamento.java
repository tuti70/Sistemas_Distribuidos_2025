package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDepartamento;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 10)
    private String sigla;

    @Column(name = "centro_unidade_academica", length = 100)
    private String centroUnidadeAcademica;

    @Column(name = "email_contato", length = 100)
    private String emailContato;

    @Column(name = "telefone_ramal", length = 20)
    private String telefoneRamal;

    @Column(length = 50)
    private String localizacao;

    // N Departamentos para 1 Universidade
    @ManyToOne
    @JoinColumn(name = "universidade_id")
    private Universidade universidade;

    // 1 Departamento para N Professores
    @OneToMany(mappedBy = "departamento")
    private List<Professor> professores = new ArrayList<>();

    // 1 Departamento para N Funcionários
    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios = new ArrayList<>();

    // 1 Departamento para N Alunos
    @OneToMany(mappedBy = "departamento")
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "chefe_departamento_id")
    private Professor chefeDepartamento;

    public Departamento() {
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

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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