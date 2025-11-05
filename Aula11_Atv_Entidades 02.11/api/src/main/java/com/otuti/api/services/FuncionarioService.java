package com.otuti.api.services;

import com.otuti.api.entities.Funcionario;
import com.otuti.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    public Optional<Funcionario> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Funcionario> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Funcionario create(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public Funcionario update(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}