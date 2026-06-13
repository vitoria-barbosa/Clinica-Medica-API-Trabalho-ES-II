package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
