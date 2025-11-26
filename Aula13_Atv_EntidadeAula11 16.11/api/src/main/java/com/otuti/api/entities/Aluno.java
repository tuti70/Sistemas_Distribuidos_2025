package com.otuti.api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAluno;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(unique = true, length = 14)
    private String cpf;

    @Column(unique = true, length = 20)
    private String matricula;

    @Column(name = "email_institucional", length = 100)
    private String emailInstitucional;

    @Column(length = 20)
    private String telefone;

    // N Alunos para 1 Professor (Orientador)
    @ManyToOne
    @JoinColumn(name = "professor_orientador_id")
    private Professor professorOrientador;

    // N Alunos para 1 Departamento
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    // 1 Aluno para 1 Carteira de Estudante
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private CarteiraEstudante carteiraEstudante;

    // N Alunos para N Funcionários (Secretários, etc.)
    @ManyToMany
    @JoinTable(name = "aluno_funcionario", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Aluno() {
    }

    // Getters e Setters (mantendo os anteriores e adicionando os novos)
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

    public Professor getProfessorOrientador() {
        return professorOrientador;
    }

    public void setProfessorOrientador(Professor professorOrientador) {
        this.professorOrientador = professorOrientador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public CarteiraEstudante getCarteiraEstudante() {
        return carteiraEstudante;
    }

    public void setCarteiraEstudante(CarteiraEstudante carteiraEstudante) {
        this.carteiraEstudante = carteiraEstudante;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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