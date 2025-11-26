public 
package com.otuti.api.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "carteiras_estudante")
public class CarteiraEstudante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCarteira;
    
    @Column(name = "numero_carteira", unique = true, nullable = false, length = 50)
    private String numeroCarteira;
    
    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;
    
    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;
    
    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    
    public CarteiraEstudante() {}
    
    public CarteiraEstudante(String numeroCarteira, LocalDate dataEmissao, LocalDate dataValidade, Aluno aluno) {
        this.numeroCarteira = numeroCarteira;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.aluno = aluno;
    }
    
    // Getters e Setters
    public UUID getIdCarteira() { return idCarteira; }
    public void setIdCarteira(UUID idCarteira) { this.idCarteira = idCarteira; }
    
    public String getNumeroCarteira() { return numeroCarteira; }
    public void setNumeroCarteira(String numeroCarteira) { this.numeroCarteira = numeroCarteira; }
    
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    
    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraEstudante that = (CarteiraEstudante) o;
        return Objects.equals(idCarteira, that.idCarteira);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idCarteira);
    }
    
    @Override
    public String toString() {
        return "CarteiraEstudante{" +
                "idCarteira=" + idCarteira +
                ", numeroCarteira='" + numeroCarteira + '\'' +
                ", dataEmissao=" + dataEmissao +
                ", dataValidade=" + dataValidade +
                '}';
    }
} {
    
}
