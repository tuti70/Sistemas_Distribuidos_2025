package com.otuti.api.controllers;

import com.otuti.api.entities.Departamento;
import com.otuti.api.exceptions.UnsupportedServiceException;
import com.otuti.api.services.DepartamentoService;
import com.otuti.api.services.ValidaIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @Autowired
    private ValidaIdService validaIdService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Departamento> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departamento> findById(@PathVariable(value = "id") String id) {
        if (!validaIdService.validaUUID(id)) {
            throw new UnsupportedServiceException("UUID não é válido: " + id);
        }
        UUID idBD = validaIdService.convertToUUID(id);
        return ResponseEntity.ok(service.findById(idBD));
    }

    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departamento> findByNome(@PathVariable String nome) {
        return service.findByNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Departamento create(@RequestBody Departamento departamento) {
        return service.create(departamento);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Departamento> update(@PathVariable String id, @RequestBody Departamento departamento) {
        if (!validaIdService.validaUUID(id)) {
            throw new UnsupportedServiceException("UUID não é válido: " + id);
        }
        UUID idBD = validaIdService.convertToUUID(id);

        if (!service.existsById(idBD)) {
            return ResponseEntity.notFound().build();
        }
        departamento.setIdDepartamento(idBD);
        return ResponseEntity.ok(service.update(departamento));
    }

    @PutMapping(value = "/{departamentoId}/chefe/{professorId}")
    public ResponseEntity<Departamento> setChefeDepartamento(
            @PathVariable String departamentoId,
            @PathVariable String professorId) {
        if (!validaIdService.validaUUID(departamentoId) || !validaIdService.validaUUID(professorId)) {
            throw new UnsupportedServiceException("UUID não é válido");
        }
        UUID departamentoUUID = validaIdService.convertToUUID(departamentoId);
        UUID professorUUID = validaIdService.convertToUUID(professorId);

        return service.setChefeDepartamento(departamentoUUID, professorUUID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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