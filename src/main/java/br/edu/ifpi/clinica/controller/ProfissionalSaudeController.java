package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.DiaTurnoRequestDTO;
import br.edu.ifpi.clinica.dto.request.ProfissionalSaudeRequestDTO;
import br.edu.ifpi.clinica.dto.response.ProfissionalSaudeDTO;
import br.edu.ifpi.clinica.service.ProfissionalSaudeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{idProfissional}/grade-horarios")
    public ResponseEntity<?> adicionarTurno(@PathVariable Long idProfissional, @RequestBody @Valid DiaTurnoRequestDTO dto) {
        ProfissionalSaudeDTO profissional = profissionalSaudeService.adicionarDiaTurnoAoProfissional(idProfissional, dto);
        return ResponseEntity.ok(profissional);
    }

    @DeleteMapping("/{idProfissional}/grade-horarios/{id}")
    public ResponseEntity<?> removerTurno(@PathVariable Long idProfissional, @PathVariable Long id) {
        profissionalSaudeService.removerDiaTurnoDeProfissional(idProfissional, id);
        return ResponseEntity.ok().build();
    }
}
