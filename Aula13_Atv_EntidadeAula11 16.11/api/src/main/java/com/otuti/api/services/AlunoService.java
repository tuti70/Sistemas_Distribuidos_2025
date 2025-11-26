package com.otuti.api.services;

import com.otuti.api.entities.Aluno;
import com.otuti.api.exceptions.ResourceNotFoundException;
import com.otuti.api.repositories.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class AlunoService {

    private Logger logger = Logger.getLogger(AlunoService.class.getName());

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> findAll() {
        logger.info("Buscando todos os alunos");
        return repository.findAll();
    }

    public Aluno findById(UUID id) {
        logger.info("Procurando aluno pelo ID: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros de aluno para esse ID: " + id));
    }

    public Optional<Aluno> findByCpf(String cpf) {
        logger.info("Procurando aluno pelo CPF: " + cpf);
        return repository.findByCpf(cpf);
    }

    public Optional<Aluno> findByMatricula(String matricula) {
        logger.info("Procurando aluno pela matrícula: " + matricula);
        return repository.findByMatricula(matricula);
    }

    public Optional<Aluno> findByEmailInstitucional(String emailInstitucional) {
        logger.info("Procurando aluno pelo email: " + emailInstitucional);
        return repository.findByEmailInstitucional(emailInstitucional);
    }

    public Aluno create(Aluno aluno) {
        logger.info("Criando novo aluno: " + aluno.getNomeCompleto());
        return repository.save(aluno);
    }

    @Transactional
    public Aluno update(Aluno aluno) {
        logger.info("Atualizando aluno ID: " + aluno.getIdAluno());

        Aluno entity = repository.findById(aluno.getIdAluno())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não há registros de aluno para esse ID: " + aluno.getIdAluno()));

        entity.setNomeCompleto(aluno.getNomeCompleto());
        entity.setDataNascimento(aluno.getDataNascimento());
        entity.setCpf(aluno.getCpf());
        entity.setMatricula(aluno.getMatricula());
        entity.setEmailInstitucional(aluno.getEmailInstitucional());
        entity.setTelefone(aluno.getTelefone());

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        logger.info("Removendo aluno ID: " + id);

        Aluno entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registros de aluno para esse ID: " + id));

        repository.delete(entity);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}