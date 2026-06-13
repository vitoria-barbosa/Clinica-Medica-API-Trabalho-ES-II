package br.edu.ifpi.clinica.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpi.clinica.dto.RecepcionistaDTO;
import br.edu.ifpi.clinica.dto.RecepcionistaRequestDTO;
import br.edu.ifpi.clinica.service.RecepcionistaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/recepcionistas")
public class RecepcionistaController {
    private final RecepcionistaService recepcionistaService;

    public RecepcionistaController(RecepcionistaService recepcionistaService) {
        this.recepcionistaService = recepcionistaService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid RecepcionistaRequestDTO dto) {
        RecepcionistaDTO recepcionista = recepcionistaService.insert(dto);
        return ResponseEntity.ok(recepcionista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        RecepcionistaDTO recepcionista = recepcionistaService.findById(id);
        return ResponseEntity.ok(recepcionista);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RecepcionistaDTO> recepcionistas = recepcionistaService.findAll();
        return ResponseEntity.ok(recepcionistas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid RecepcionistaRequestDTO dto) {
        RecepcionistaDTO recepcionista = recepcionistaService.update(id, dto);
        return ResponseEntity.ok(recepcionista);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        recepcionistaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
