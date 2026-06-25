package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.DiaRequestDTO;
import br.edu.ifpi.clinica.dto.response.DiaDTO;
import br.edu.ifpi.clinica.service.DiaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dias")
public class DiaController {
    private final DiaService diaService;

    public DiaController(DiaService diaService) {
        this.diaService = diaService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid DiaRequestDTO dto) {
        DiaDTO dia = diaService.insert(dto);
        return ResponseEntity.ok(dia);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        DiaDTO dia = diaService.findById(id);
        return ResponseEntity.ok(dia);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<DiaDTO> dias = diaService.findAll();
        return ResponseEntity.ok(dias);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DiaRequestDTO dto) {
        DiaDTO dia = diaService.update(id, dto);
        return ResponseEntity.ok(dia);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        diaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
