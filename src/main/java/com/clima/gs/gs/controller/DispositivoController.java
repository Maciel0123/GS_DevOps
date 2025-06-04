package com.clima.gs.gs.controller;

import com.clima.gs.gs.model.DispositivoIot;
import com.clima.gs.gs.repository.DispositivoRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DispositivoIot>> listar(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoIot> buscarPorId(@PathVariable Long id) {
        Optional<DispositivoIot> dispositivo = repository.findById(id);
        return dispositivo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DispositivoIot> salvar(@RequestBody @Valid DispositivoIot dispositivo) {
        DispositivoIot salvo = repository.save(dispositivo);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
