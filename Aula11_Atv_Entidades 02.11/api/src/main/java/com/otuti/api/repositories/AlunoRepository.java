package com.otuti.api.repositories;

import com.otuti.api.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByMatricula(String matricula);

    Optional<Aluno> findByEmailInstitucional(String emailInstitucional);
}