package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.RecepcionistaRequestDTO;
import br.edu.ifpi.clinica.dto.response.RecepcionistaDTO;
import br.edu.ifpi.clinica.service.RecepcionistaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
