package com.otuti.api.repositories;

import com.otuti.api.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByCpf(String cpf);

    Optional<Professor> findByEmailInstitucional(String emailInstitucional);
}