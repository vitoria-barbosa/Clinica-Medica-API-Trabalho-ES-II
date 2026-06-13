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

import br.edu.ifpi.clinica.dto.TurnoDTO;
import br.edu.ifpi.clinica.dto.TurnoRequestDTO;
import br.edu.ifpi.clinica.service.TurnoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid TurnoRequestDTO dto) {
        TurnoDTO turno = turnoService.insert(dto);
        return ResponseEntity.ok(turno);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        TurnoDTO turno = turnoService.findById(id);
        return ResponseEntity.ok(turno);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<TurnoDTO> turnos = turnoService.findAll();
        return ResponseEntity.ok(turnos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid TurnoRequestDTO dto) {
        TurnoDTO turno = turnoService.update(id, dto);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        turnoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
