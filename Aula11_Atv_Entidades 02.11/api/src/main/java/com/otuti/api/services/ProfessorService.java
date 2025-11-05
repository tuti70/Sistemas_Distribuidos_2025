package com.otuti.api.services;

import com.otuti.api.entities.Professor;
import com.otuti.api.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public Optional<Professor> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Professor> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Professor create(Professor professor) {
        return repository.save(professor);
    }

    public Professor update(Professor professor) {
        return repository.save(professor);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}