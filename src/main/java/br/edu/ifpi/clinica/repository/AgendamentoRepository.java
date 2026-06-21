package br.edu.ifpi.clinica.repository;

import br.edu.ifpi.clinica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByPacienteId(Long pacienteId);

    List<Agendamento> findByProfissionalId(Long profissionalId);

    Optional<Agendamento> findAgendamentoByDataHoraAndProfissionalId(LocalDateTime dataHora, long ProfissionalId);

    List<Agendamento> findAgendamentoByDataHoraBetweenAndProfissionalId(LocalDateTime dataHoraAfter, LocalDateTime dataHoraBefore, long profissionalId);

    Optional<Agendamento> findAgendamentoByDataHoraAndPacienteId(LocalDateTime dataHora, long pacienteId);
}