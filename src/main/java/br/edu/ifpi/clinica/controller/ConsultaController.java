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

import br.edu.ifpi.clinica.dto.ConsultaDTO;
import br.edu.ifpi.clinica.dto.ConsultaRequestDTO;
import br.edu.ifpi.clinica.service.ConsultaService;
import jakarta.validation.Valid;

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
