package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.TurnoRequestDTO;
import br.edu.ifpi.clinica.dto.response.TurnoDTO;
import br.edu.ifpi.clinica.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
