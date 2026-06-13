package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
