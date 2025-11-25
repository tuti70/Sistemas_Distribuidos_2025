package com.otuti.api.repositories;

import com.otuti.api.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {
    Optional<Departamento> findByNome(String nome);

    Optional<Departamento> findBySigla(String sigla);
}