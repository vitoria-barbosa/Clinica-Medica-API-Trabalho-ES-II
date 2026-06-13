package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.EspecialidadeDTO;
import br.edu.ifpi.clinica.dto.EspecialidadeRequestDTO;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Especialidade;
import br.edu.ifpi.clinica.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    public EspecialidadeDTO insert(EspecialidadeRequestDTO dto) {
        Especialidade especialidade = dto.toEntity();
        especialidade = especialidadeRepository.save(especialidade);
        return new EspecialidadeDTO(especialidade);
    }

    public EspecialidadeDTO findById(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma especialidade com esse ID."));

        return new EspecialidadeDTO(especialidade);
    }

    public List<EspecialidadeDTO> findAll() {
        List<Especialidade> especialidades = especialidadeRepository.findAll();

        return especialidades.stream().map(EspecialidadeDTO::new).toList();
    }

    public EspecialidadeDTO update(Long id, EspecialidadeRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma especialidade com esse ID."));

        especialidade.setNome(dto.nome());
        especialidade.setValorConsulta(dto.valorConsulta());
        especialidade = especialidadeRepository.save(especialidade);

        return new EspecialidadeDTO(especialidade);
    }

    public void delete(Long id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma especialidade com esse ID."));

        try {
            especialidadeRepository.delete(especialidade);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
