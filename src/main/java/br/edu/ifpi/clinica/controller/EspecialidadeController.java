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

import br.edu.ifpi.clinica.dto.EspecialidadeDTO;
import br.edu.ifpi.clinica.dto.EspecialidadeRequestDTO;
import br.edu.ifpi.clinica.service.EspecialidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid EspecialidadeRequestDTO dto) {
        EspecialidadeDTO especialidade = especialidadeService.insert(dto);
        return ResponseEntity.ok(especialidade);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        EspecialidadeDTO especialidade = especialidadeService.findById(id);
        return ResponseEntity.ok(especialidade);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<EspecialidadeDTO> especialidades = especialidadeService.findAll();
        return ResponseEntity.ok(especialidades);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid EspecialidadeRequestDTO dto) {
        EspecialidadeDTO especialidade = especialidadeService.update(id, dto);
        return ResponseEntity.ok(especialidade);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        especialidadeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
