package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDTO(long id, LocalDateTime dataHora, PacienteDTO paciente, long recepcionistaId,
                             String nomeRecepcionista, long profissionalId, String nomeProfissional,
                             String telefoneProfissional, EspecialidadeDTO especialidade) {
    public AgendamentoDTO(Agendamento agendamento) {
        this(agendamento.getId(), agendamento.getDataHora(), new PacienteDTO(agendamento.getPaciente()),
                agendamento.getRecepcionista().getId(), agendamento.getRecepcionista().getNome(), agendamento.getProfissional().getId(),
                agendamento.getProfissional().getNome(), agendamento.getProfissional().getTelefone(), new EspecialidadeDTO(agendamento.getProfissional().getEspecialidade()));
    }
}