package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.ProfissionalSaudeDTO;
import br.edu.ifpi.clinica.dto.ProfissionalSaudeRequestDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Especialidade;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.repository.EspecialidadeRepository;
import br.edu.ifpi.clinica.repository.ProfissionalSaudeRepository;

@Service
public class ProfissionalSaudeService {
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final EspecialidadeRepository especialidadeRepository;

    public ProfissionalSaudeService(ProfissionalSaudeRepository profissionalSaudeRepository, EspecialidadeRepository especialidadeRepository) {
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.especialidadeRepository = especialidadeRepository;
    }

    public ProfissionalSaudeDTO insert(ProfissionalSaudeRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new DadoInvalidoException("ID da especialidade inválido."));

        ProfissionalSaude profissional = dto.toEntity();
        profissional.setEspecialidade(especialidade);
        profissional = profissionalSaudeRepository.save(profissional);
        return new ProfissionalSaudeDTO(profissional);
    }

    public ProfissionalSaudeDTO findById(Long id) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        return new ProfissionalSaudeDTO(profissional);
    }

    public List<ProfissionalSaudeDTO> findAll() {
        List<ProfissionalSaude> profissionais = profissionalSaudeRepository.findAll();

        return profissionais.stream().map(ProfissionalSaudeDTO::new).toList();
    }

    public ProfissionalSaudeDTO update(Long id, ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new DadoInvalidoException("ID da especialidade inválido."));

        profissional.setCpf(dto.cpf());
        profissional.setNome(dto.nome());
        profissional.setTelefone(dto.telefone());
        profissional.setEspecialidade(especialidade);
        profissional.setRegistroProfissional(dto.registroProfissional());

        profissional = profissionalSaudeRepository.save(profissional);

        return new ProfissionalSaudeDTO(profissional);
    }

    public void delete(Long id) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        try {
            profissionalSaudeRepository.delete(profissional);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
