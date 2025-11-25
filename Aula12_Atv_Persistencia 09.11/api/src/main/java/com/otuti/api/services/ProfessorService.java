package com.otuti.api.services;

import com.otuti.api.entities.Professor;
import com.otuti.api.exceptions.ResourceNotFoundException;
import com.otuti.api.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProfessorService {

    private Logger logger = Logger.getLogger(ProfessorService.class.getName());

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> findAll() {
        logger.info("Buscando todos os professores");
        return repository.findAll();
    }

    public Professor findById(UUID id) {
        logger.info("Procurando professor pelo ID: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros de professor para esse ID: " + id));
    }

    public Optional<Professor> findByCpf(String cpf) {
        logger.info("Procurando professor pelo CPF: " + cpf);
        return repository.findByCpf(cpf);
    }

    public Optional<Professor> findByEmailInstitucional(String emailInstitucional) {
        logger.info("Procurando professor pelo email: " + emailInstitucional);
        return repository.findByEmailInstitucional(emailInstitucional);
    }

    public Professor create(Professor professor) {
        logger.info("Criando novo professor: " + professor.getNomeCompleto());
        return repository.save(professor);
    }

    @Transactional
    public Professor update(Professor professor) {
        logger.info("Atualizando professor ID: " + professor.getIdProfessor());

        Professor entity = repository.findById(professor.getIdProfessor())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não há registros de professor para esse ID: " + professor.getIdProfessor()));

        entity.setNomeCompleto(professor.getNomeCompleto());
        entity.setCpf(professor.getCpf());
        entity.setEmailInstitucional(professor.getEmailInstitucional());
        entity.setTelefone(professor.getTelefone());
        entity.setTitulacao(professor.getTitulacao());
        entity.setAreaAtuacao(professor.getAreaAtuacao());
        entity.setRegimeTrabalho(professor.getRegimeTrabalho());

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        logger.info("Removendo professor ID: " + id);

        Professor entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros de professor para esse ID: " + id));

        repository.delete(entity);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}