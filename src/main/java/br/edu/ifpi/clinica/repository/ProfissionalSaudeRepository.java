package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.ProfissionalSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfissionalSaudeRepository extends JpaRepository<ProfissionalSaude, Long> {
    @Query("SELECT p FROM ProfissionalSaude p LEFT JOIN FETCH p.gradeHorarios WHERE p.id = :id")
    Optional<ProfissionalSaude> findByIdComHorarios(@Param("id") Long id);

    @Query("SELECT DISTINCT  p FROM ProfissionalSaude p LEFT JOIN FETCH p.gradeHorarios")
    List<ProfissionalSaude> findAllComHorarios();

    List<ProfissionalSaude> findByEspecialidadeId(long especialidadeId);
}
