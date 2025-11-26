package com.otuti.api.repositories;

import com.otuti.api.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByEmailInstitucional(String emailInstitucional);
}