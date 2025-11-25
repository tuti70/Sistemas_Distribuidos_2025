package com.otuti.api.services;

import com.otuti.api.entities.Funcionario;
import com.otuti.api.exceptions.ResourceNotFoundException;
import com.otuti.api.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class FuncionarioService {

    private Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> findAll() {
        logger.info("Buscando todos os funcionários");
        return repository.findAll();
    }

    public Funcionario findById(UUID id) {
        logger.info("Procurando funcionário pelo ID: " + id);
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Não há registros de funcionário para esse ID: " + id));
    }

    public Optional<Funcionario> findByCpf(String cpf) {
        logger.info("Procurando funcionário pelo CPF: " + cpf);
        return repository.findByCpf(cpf);
    }

    public Optional<Funcionario> findByEmailInstitucional(String emailInstitucional) {
        logger.info("Procurando funcionário pelo email: " + emailInstitucional);
        return repository.findByEmailInstitucional(emailInstitucional);
    }

    public Funcionario create(Funcionario funcionario) {
        logger.info("Criando novo funcionário: " + funcionario.getNomeCompleto());
        return repository.save(funcionario);
    }

    @Transactional
    public Funcionario update(Funcionario funcionario) {
        logger.info("Atualizando funcionário ID: " + funcionario.getIdFuncionario());

        Funcionario entity = repository.findById(funcionario.getIdFuncionario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não há registros de funcionário para esse ID: " + funcionario.getIdFuncionario()));

        entity.setNomeCompleto(funcionario.getNomeCompleto());
        entity.setCpf(funcionario.getCpf());
        entity.setEmailInstitucional(funcionario.getEmailInstitucional());
        entity.setTelefone(funcionario.getTelefone());
        entity.setCargo(funcionario.getCargo());
        entity.setTipoVinculo(funcionario.getTipoVinculo());

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        logger.info("Removendo funcionário ID: " + id);

        Funcionario entity = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Não há registros de funcionário para esse ID: " + id));

        repository.delete(entity);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}