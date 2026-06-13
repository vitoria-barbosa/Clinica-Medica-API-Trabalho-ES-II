package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.DiaTurnoDTO;
import br.edu.ifpi.clinica.dto.DiaTurnoRequestDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.model.DiaTurno;
import br.edu.ifpi.clinica.model.Turno;
import br.edu.ifpi.clinica.repository.DiaRepository;
import br.edu.ifpi.clinica.repository.DiaTurnoRepository;
import br.edu.ifpi.clinica.repository.TurnoRepository;

@Service
public class DiaTurnoService {
    private final DiaTurnoRepository diaTurnoRepository;
    private final DiaRepository diaRepository;
    private final TurnoRepository turnoRepository;

    public DiaTurnoService(DiaTurnoRepository diaTurnoRepository, DiaRepository diaRepository, TurnoRepository turnoRepository) {
        this.diaTurnoRepository = diaTurnoRepository;
        this.diaRepository = diaRepository;
        this.turnoRepository = turnoRepository;
    }

    public DiaTurnoDTO insert(DiaTurnoRequestDTO dto) {
        Dia dia = diaRepository.findById(dto.diaId())
                .orElseThrow(() -> new DadoInvalidoException("ID do dia inválido."));

        Turno turno = turnoRepository.findById(dto.turnoId())
                .orElseThrow(() -> new DadoInvalidoException("ID do turno inválido."));

        DiaTurno diaTurno = new DiaTurno(dia, turno);
        diaTurno = diaTurnoRepository.save(diaTurno);
        return new DiaTurnoDTO(diaTurno);
    }

    public DiaTurnoDTO findById(Long id) {
        DiaTurno diaTurno = diaTurnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum dia turno com esse ID."));

        return new DiaTurnoDTO(diaTurno);
    }

    public List<DiaTurnoDTO> findAll() {
        List<DiaTurno> diaTurnos = diaTurnoRepository.findAll();

        return diaTurnos.stream().map(DiaTurnoDTO::new).toList();
    }

    public DiaTurnoDTO update(Long id, DiaTurnoRequestDTO dto) {
        DiaTurno diaTurno = diaTurnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum dia turno com esse ID."));

        Dia dia = diaRepository.findById(dto.diaId())
                .orElseThrow(() -> new DadoInvalidoException("ID do dia inválido."));

        Turno turno = turnoRepository.findById(dto.turnoId())
                .orElseThrow(() -> new DadoInvalidoException("ID do turno inválido."));

        diaTurno.setDia(dia);
        diaTurno.setTurno(turno);

        diaTurno = diaTurnoRepository.save(diaTurno);

        return new DiaTurnoDTO(diaTurno);
    }

    public void delete(Long id) {
        DiaTurno diaTurno = diaTurnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum dia turno com esse ID."));

        try {
            diaTurnoRepository.delete(diaTurno);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
