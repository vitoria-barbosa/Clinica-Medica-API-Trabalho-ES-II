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

import br.edu.ifpi.clinica.dto.ProfissionalSaudeDTO;
import br.edu.ifpi.clinica.dto.ProfissionalSaudeRequestDTO;
import br.edu.ifpi.clinica.service.ProfissionalSaudeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/profissionais-da-saude")
public class ProfissionalSaudeController {
    private final ProfissionalSaudeService profissionalSaudeService;

    public ProfissionalSaudeController(ProfissionalSaudeService profissionalSaudeService) {
        this.profissionalSaudeService = profissionalSaudeService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaudeDTO profissionalSaude = profissionalSaudeService.insert(dto);
        return ResponseEntity.ok(profissionalSaude);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ProfissionalSaudeDTO profissionalSaude = profissionalSaudeService.findById(id);
        return ResponseEntity.ok(profissionalSaude);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ProfissionalSaudeDTO> profissionaisSaude = profissionalSaudeService.findAll();
        return ResponseEntity.ok(profissionaisSaude);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaudeDTO profissionalSaude = profissionalSaudeService.update(id, dto);
        return ResponseEntity.ok(profissionalSaude);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        profissionalSaudeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
