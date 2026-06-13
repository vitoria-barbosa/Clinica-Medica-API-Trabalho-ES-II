package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.DiaDTO;
import br.edu.ifpi.clinica.dto.DiaRequestDTO;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.repository.DiaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaService {
    private final DiaRepository diaRepository;

    public DiaService(DiaRepository diaRepository) {
        this.diaRepository = diaRepository;
    }

    public DiaDTO insert(DiaRequestDTO dto) {
        Dia dia = dto.toEntity();
        dia = diaRepository.save(dia);
        return new DiaDTO(dia);
    }

    public DiaDTO findById(Long id) {
        Dia dia = diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma categoria com esse ID."));

        return new DiaDTO(dia);
    }

    public List<DiaDTO> findAll() {
        List<Dia> dias = diaRepository.findAll();

        return dias.stream().map(DiaDTO::new).toList();
    }

    public DiaDTO update(Long id, DiaRequestDTO dto) {
        Dia dia = diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma categoria com esse ID."));

        dia.setNome(dto.nome());
        dia = diaRepository.save(dia);

        return new DiaDTO(dia);
    }

    public void delete(Long id) {
        Dia dia = diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma categoria com esse ID."));

        try {
            diaRepository.delete(dia);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
