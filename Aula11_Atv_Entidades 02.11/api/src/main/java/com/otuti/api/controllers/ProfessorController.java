package com.otuti.api.controllers;

import com.otuti.api.entities.Professor;
import com.otuti.api.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public List<Professor> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Professor> findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Professor create(@RequestBody Professor professor) {
        return service.create(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable UUID id, @RequestBody Professor professor) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        professor.setIdProfessor(id);
        return ResponseEntity.ok(service.update(professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}