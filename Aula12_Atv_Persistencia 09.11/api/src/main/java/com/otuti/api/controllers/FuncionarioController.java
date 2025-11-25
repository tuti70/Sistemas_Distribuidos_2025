package com.otuti.api.controllers;

import com.otuti.api.entities.Funcionario;
import com.otuti.api.exceptions.UnsupportedServiceException;
import com.otuti.api.services.FuncionarioService;
import com.otuti.api.services.ValidaIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private ValidaIdService validaIdService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Funcionario> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Funcionario> findById(@PathVariable(value = "id") String id) {
        if (!validaIdService.validaUUID(id)) {
            throw new UnsupportedServiceException("UUID não é válido: " + id);
        }
        UUID idBD = validaIdService.convertToUUID(id);
        return ResponseEntity.ok(service.findById(idBD));
    }

    @GetMapping(value = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Funcionario> findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Funcionario create(@RequestBody Funcionario funcionario) {
        return service.create(funcionario);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Funcionario> update(@PathVariable String id, @RequestBody Funcionario funcionario) {
        if (!validaIdService.validaUUID(id)) {
            throw new UnsupportedServiceException("UUID não é válido: " + id);
        }
        UUID idBD = validaIdService.convertToUUID(id);

        if (!service.existsById(idBD)) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setIdFuncionario(idBD);
        return ResponseEntity.ok(service.update(funcionario));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!validaIdService.validaUUID(id)) {
            throw new UnsupportedServiceException("UUID não é válido: " + id);
        }
        UUID idBD = validaIdService.convertToUUID(id);

        if (!service.existsById(idBD)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(idBD);
        return ResponseEntity.noContent().build();
    }
}