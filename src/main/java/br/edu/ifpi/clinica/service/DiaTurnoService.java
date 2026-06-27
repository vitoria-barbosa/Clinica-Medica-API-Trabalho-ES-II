package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.DiaTurnoRequestDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.model.DiaTurno;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.model.Turno;
import br.edu.ifpi.clinica.repository.DiaRepository;
import br.edu.ifpi.clinica.repository.DiaTurnoRepository;
import br.edu.ifpi.clinica.repository.TurnoRepository;
import org.springframework.stereotype.Service;

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

    public DiaTurno insert(ProfissionalSaude profissional, DiaTurnoRequestDTO dto) {
        Dia dia = diaRepository.findById(dto.diaId())
                .orElseThrow(() -> new DadoInvalidoException("ID do dia inválido."));

        Turno turno = turnoRepository.findById(dto.turnoId())
                .orElseThrow(() -> new DadoInvalidoException("ID do turno inválido."));

        if (diaTurnoRepository.findDiaTurnoByDiaAndTurnoAndProfissionalSaude(dia, turno, profissional).isPresent()) {
            throw new DadoInvalidoException("Esse profissional já possui esse dia e turno em sua grade de horários.");
        }
        DiaTurno diaTurno = new DiaTurno(profissional, dia, turno);
        return diaTurnoRepository.save(diaTurno);
    }

    public void delete(Long id) {
        DiaTurno diaTurno = diaTurnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum dia turno com esse ID."));
        diaTurnoRepository.delete(diaTurno);
    }
}
