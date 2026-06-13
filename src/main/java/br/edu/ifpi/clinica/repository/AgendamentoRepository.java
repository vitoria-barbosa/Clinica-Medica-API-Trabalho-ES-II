package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
