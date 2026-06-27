package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.PacienteRequestDTO;
import br.edu.ifpi.clinica.dto.response.PacienteDTO;
import br.edu.ifpi.clinica.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid PacienteRequestDTO dto) {
        PacienteDTO paciente = pacienteService.insert(dto);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.findById(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<PacienteDTO> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PacienteRequestDTO dto) {
        PacienteDTO paciente = pacienteService.update(id, dto);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
