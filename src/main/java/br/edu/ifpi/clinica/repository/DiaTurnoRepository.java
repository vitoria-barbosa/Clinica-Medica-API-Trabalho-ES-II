package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.model.DiaTurno;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaTurnoRepository extends JpaRepository<DiaTurno, Long> {
    Optional<DiaTurno> findDiaTurnoByDiaAndTurnoAndProfissionalSaude(Dia dia, Turno turno, ProfissionalSaude profissionalSaude);
}
