package br.edu.ifpi.clinica.controller;

import br.edu.ifpi.clinica.dto.request.EspecialidadeRequestDTO;
import br.edu.ifpi.clinica.dto.response.EspecialidadeDTO;
import br.edu.ifpi.clinica.dto.response.ProfissionalSaudeDTO;
import br.edu.ifpi.clinica.service.EspecialidadeService;
import br.edu.ifpi.clinica.service.ProfissionalSaudeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;
    private final ProfissionalSaudeService profissionalSaudeService;

    public EspecialidadeController(EspecialidadeService especialidadeService, ProfissionalSaudeService profissionalSaudeService) {
        this.especialidadeService = especialidadeService;
        this.profissionalSaudeService = profissionalSaudeService;
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

    @GetMapping("/{id}/profissionais")
    public ResponseEntity<?> buscarProfissionaisPorEspecialidade(@PathVariable long id) {
        List<ProfissionalSaudeDTO> profissionais = profissionalSaudeService.buscarProfissionaisPelaEspecialidade(id);
        return ResponseEntity.ok(profissionais);
    }
}
