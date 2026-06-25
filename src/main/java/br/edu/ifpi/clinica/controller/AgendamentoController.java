package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.AgendamentoRequestDTO;
import br.edu.ifpi.clinica.dto.response.AgendamentoDTO;
import br.edu.ifpi.clinica.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid AgendamentoRequestDTO dto) {
        AgendamentoDTO agendamento = agendamentoService.insert(dto);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AgendamentoDTO agendamento = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AgendamentoDTO> agendamentos = agendamentoService.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO dto) {
        AgendamentoDTO agendamento = agendamentoService.update(id, dto);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        agendamentoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/paciente/{pacienteId}")
    public ResponseEntity<List<AgendamentoDTO>> findByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(agendamentoService.findByPaciente(pacienteId));
    }

    @GetMapping(value = "/profissional/{profissionalId}")
    public ResponseEntity<List<AgendamentoDTO>> findByProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(agendamentoService.findByProfissional(profissionalId));
    }
}
