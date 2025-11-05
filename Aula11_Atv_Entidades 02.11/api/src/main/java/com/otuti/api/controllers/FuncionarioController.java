package com.otuti.api.controllers;

import com.otuti.api.entities.Funcionario;
import com.otuti.api.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public List<Funcionario> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario create(@RequestBody Funcionario funcionario) {
        return service.create(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable UUID id, @RequestBody Funcionario funcionario) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setIdFuncionario(id);
        return ResponseEntity.ok(service.update(funcionario));
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