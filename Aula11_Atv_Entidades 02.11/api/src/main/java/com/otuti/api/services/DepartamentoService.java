package com.otuti.api.services;

import com.otuti.api.entities.Departamento;
import com.otuti.api.entities.Professor;
import com.otuti.api.repositories.DepartamentoRepository;
import com.otuti.api.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Departamento> findAll() {
        return repository.findAll();
    }

    public Optional<Departamento> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Departamento> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public Departamento create(Departamento departamento) {
        return repository.save(departamento);
    }

    public Departamento update(Departamento departamento) {
        return repository.save(departamento);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    public Optional<Departamento> setChefeDepartamento(UUID departamentoId, UUID professorId) {
        Optional<Departamento> departamentoOpt = repository.findById(departamentoId);
        Optional<Professor> professorOpt = professorRepository.findById(professorId);

        if (departamentoOpt.isPresent() && professorOpt.isPresent()) {
            Departamento departamento = departamentoOpt.get();
            departamento.setChefeDepartamento(professorOpt.get());
            return Optional.of(repository.save(departamento));
        }

        return Optional.empty();
    }
}