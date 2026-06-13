package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Dia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaRepository extends JpaRepository<Dia, Long> {
}
