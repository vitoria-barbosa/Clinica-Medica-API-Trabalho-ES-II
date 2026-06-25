package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.ConsultaRequestDTO;
import br.edu.ifpi.clinica.dto.response.ConsultaDTO;
import br.edu.ifpi.clinica.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid ConsultaRequestDTO dto) {
        ConsultaDTO consulta = consultaService.insert(dto);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ConsultaDTO consulta = consultaService.findById(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ConsultaDTO> consultas = consultaService.findAll();
        return ResponseEntity.ok(consultas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ConsultaRequestDTO dto) {
        ConsultaDTO consulta = consultaService.update(id, dto);
        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
