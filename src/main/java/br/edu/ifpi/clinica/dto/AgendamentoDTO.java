package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDTO(long id, LocalDateTime dataHora, long profissionalId, String nomeProfissional,
                             long pacienteId, String nomePaciente, long recepcionistaId, String nomeRecepcionista) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getDataHora(),
                agendamento.getProfissional().getId(), agendamento.getProfissional().getNome(),
                agendamento.getPaciente().getId(), agendamento.getPaciente().getNome(),
                agendamento.getRecepcionista().getId(), agendamento.getRecepcionista().getNome());
    }
}
