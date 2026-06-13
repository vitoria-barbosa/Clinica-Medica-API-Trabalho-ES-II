package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.TurnoDTO;
import br.edu.ifpi.clinica.dto.TurnoRequestDTO;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Turno;
import br.edu.ifpi.clinica.repository.TurnoRepository;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO insert(TurnoRequestDTO dto) {
        Turno turno = dto.toEntity();
        turno = turnoRepository.save(turno);
        return new TurnoDTO(turno);
    }

    public TurnoDTO findById(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum turno com esse ID."));

        return new TurnoDTO(turno);
    }

    public List<TurnoDTO> findAll() {
        List<Turno> turnos = turnoRepository.findAll();

        return turnos.stream().map(TurnoDTO::new).toList();
    }

    public TurnoDTO update(Long id, TurnoRequestDTO dto) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum turno com esse ID."));

        turno.setNome(dto.nome());
        turno = turnoRepository.save(turno);

        return new TurnoDTO(turno);
    }

    public void delete(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum turno com esse ID."));

        try {
            turnoRepository.delete(turno);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
