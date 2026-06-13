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

import br.edu.ifpi.clinica.dto.DiaTurnoDTO;
import br.edu.ifpi.clinica.dto.DiaTurnoRequestDTO;
import br.edu.ifpi.clinica.service.DiaTurnoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/grade-horarios")
public class DiaTurnoController {
    private final DiaTurnoService diaTurnoService;

    public DiaTurnoController(DiaTurnoService diaTurnoService) {
        this.diaTurnoService = diaTurnoService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid DiaTurnoRequestDTO dto) {
        DiaTurnoDTO diaTurno = diaTurnoService.insert(dto);
        return ResponseEntity.ok(diaTurno);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        DiaTurnoDTO diaTurno = diaTurnoService.findById(id);
        return ResponseEntity.ok(diaTurno);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<DiaTurnoDTO> diaTurnos = diaTurnoService.findAll();
        return ResponseEntity.ok(diaTurnos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DiaTurnoRequestDTO dto) {
        DiaTurnoDTO diaTurno = diaTurnoService.update(id, dto);
        return ResponseEntity.ok(diaTurno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        diaTurnoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
