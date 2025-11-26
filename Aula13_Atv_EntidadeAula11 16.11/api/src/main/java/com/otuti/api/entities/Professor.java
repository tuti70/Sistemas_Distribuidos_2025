package com.otuti.api.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProfessor;

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

    // N Professores para 1 Departamento
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    // 1 Professor para N Alunos (Orientador)
    @OneToMany(mappedBy = "professorOrientador")
    private List<Aluno> alunosOrientados = new ArrayList<>();

    // N Professores para N Funcionários
    @ManyToMany
    @JoinTable(name = "professor_funcionario", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<Funcionario> funcionarios = new ArrayList<>();

    // 1 Professor pode ser chefe de 1 Departamento
    @OneToMany(mappedBy = "chefeDepartamento")
    private List<Departamento> departamentosChefiados = new ArrayList<>();

    public Professor() {
    }

    // Getters e Setters
    public UUID getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(UUID idProfessor) {
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Aluno> getAlunosOrientados() {
        return alunosOrientados;
    }

    public void setAlunosOrientados(List<Aluno> alunosOrientados) {
        this.alunosOrientados = alunosOrientados;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Departamento> getDepartamentosChefiados() {
        return departamentosChefiados;
    }

    public void setDepartamentosChefiados(List<Departamento> departamentosChefiados) {
        this.departamentosChefiados = departamentosChefiados;
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