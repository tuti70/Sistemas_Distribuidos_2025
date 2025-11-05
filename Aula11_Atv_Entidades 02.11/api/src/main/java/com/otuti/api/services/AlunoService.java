package com.otuti.api.services;

import com.otuti.api.entities.Aluno;
import com.otuti.api.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Optional<Aluno> findByMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }

    public Aluno create(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno update(Aluno aluno) {
        return repository.save(aluno);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}