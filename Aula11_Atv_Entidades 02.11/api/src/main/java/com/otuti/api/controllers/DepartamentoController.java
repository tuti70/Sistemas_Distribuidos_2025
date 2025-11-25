package com.otuti.api.controllers;

import com.otuti.api.entities.Departamento;
import com.otuti.api.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/departamentos")
public class DepartamentoController {
    
    @Autowired
    private DepartamentoService service;

    @GetMapping
    public List<Departamento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> findById(@PathVariable UUID id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Departamento create(@RequestBody Departamento departamento) {
        return service.create(departamento);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Departamento> update(@PathVariable UUID id, @RequestBody Departamento departamento) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        departamento.setIdDepartamento(id);
        return ResponseEntity.ok(service.update(departamento));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}