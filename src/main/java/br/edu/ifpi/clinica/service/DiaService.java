package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.DiaRequestDTO;
import br.edu.ifpi.clinica.dto.response.DiaDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.repository.DiaRepository;
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
        try {
            dia = diaRepository.save(dia);
        } catch (RuntimeException e) {
            throw new DadoInvalidoException("Já existe um nome de dia igual a esse no BD.");
        }
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

        dia.setNome(dto.nome().toLowerCase());
        dia = diaRepository.save(dia);

        return new DiaDTO(dia);
    }

    public void delete(Long id) {
        Dia dia = diaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma categoria com esse ID."));
        diaRepository.delete(dia);
    }
}
