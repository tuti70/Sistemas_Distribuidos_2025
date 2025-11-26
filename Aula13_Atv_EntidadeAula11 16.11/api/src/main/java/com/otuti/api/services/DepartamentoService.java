package com.otuti.api.services;

import com.otuti.api.entities.Departamento;
import com.otuti.api.entities.Professor;
import com.otuti.api.exceptions.ResourceNotFoundException;
import com.otuti.api.repositories.DepartamentoRepository;
import com.otuti.api.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class DepartamentoService {

    private Logger logger = Logger.getLogger(DepartamentoService.class.getName());

    private final DepartamentoRepository repository;
    private final ProfessorRepository professorRepository;

    public DepartamentoService(DepartamentoRepository repository, ProfessorRepository professorRepository) {
        this.repository = repository;
        this.professorRepository = professorRepository;
    }

    public List<Departamento> findAll() {
        logger.info("Buscando todos os departamentos");
        return repository.findAll();
    }

    public Departamento findById(UUID id) {
        logger.info("Procurando departamento pelo ID: " + id);
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Não há registros de departamento para esse ID: " + id));
    }

    public Optional<Departamento> findByNome(String nome) {
        logger.info("Procurando departamento pelo nome: " + nome);
        return repository.findByNome(nome);
    }

    public Optional<Departamento> findBySigla(String sigla) {
        logger.info("Procurando departamento pela sigla: " + sigla);
        return repository.findBySigla(sigla);
    }

    public Departamento create(Departamento departamento) {
        logger.info("Criando novo departamento: " + departamento.getNome());
        return repository.save(departamento);
    }

    @Transactional
    public Departamento update(Departamento departamento) {
        logger.info("Atualizando departamento ID: " + departamento.getIdDepartamento());

        Departamento entity = repository.findById(departamento.getIdDepartamento())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não há registros de departamento para esse ID: " + departamento.getIdDepartamento()));

        entity.setNome(departamento.getNome());
        entity.setSigla(departamento.getSigla());
        entity.setCentroUnidadeAcademica(departamento.getCentroUnidadeAcademica());
        entity.setEmailContato(departamento.getEmailContato());
        entity.setTelefoneRamal(departamento.getTelefoneRamal());
        entity.setLocalizacao(departamento.getLocalizacao());

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        logger.info("Removendo departamento ID: " + id);

        Departamento entity = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Não há registros de departamento para esse ID: " + id));

        repository.delete(entity);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    public Optional<Departamento> setChefeDepartamento(UUID departamentoId, UUID professorId) {
        logger.info("Definindo chefe do departamento. Departamento ID: " + departamentoId + ", Professor ID: "
                + professorId);

        Optional<Departamento> departamentoOpt = repository.findById(departamentoId);
        Optional<Professor> professorOpt = professorRepository.findById(professorId);

        if (departamentoOpt.isPresent() && professorOpt.isPresent()) {
            Departamento departamento = departamentoOpt.get();
            departamento.setChefeDepartamento(professorOpt.get());
            return Optional.of(repository.save(departamento));
        }

        return Optional.empty();
    }

    public Optional<Departamento> removeChefeDepartamento(UUID departamentoId) {
        logger.info("Removendo chefe do departamento ID: " + departamentoId);

        Optional<Departamento> departamentoOpt = repository.findById(departamentoId);

        if (departamentoOpt.isPresent()) {
            Departamento departamento = departamentoOpt.get();
            departamento.setChefeDepartamento(null);
            return Optional.of(repository.save(departamento));
        }

        return Optional.empty();
    }
}