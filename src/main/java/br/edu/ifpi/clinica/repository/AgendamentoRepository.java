package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByPacienteId(Long pacienteId);
    List<Agendamento> findByProfissionalId(Long profissionalId);
}