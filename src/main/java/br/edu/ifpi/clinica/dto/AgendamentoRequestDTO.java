package br.edu.ifpi.clinica.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @Future(message = "A data e hora do agendamento deve ser no futuro")
        @NotNull(message = "A data e hora do agendamento são obrigatórios")
        LocalDateTime dataHora,

        @Positive(message = "O ID do profissional deve ser maior que zero")
        @NotNull(message = "O ID do profissional é obrigatório")
        long profissionalId,

        @Positive(message = "O ID do paciente deve ser maior que zero")
        @NotNull(message = "O ID do paciente é obrigatório")
        long pacienteId,

        @Positive(message = "O ID de recepcionista deve ser maior que zero")
        @NotNull(message = "O ID de recepcionista é obrigatório")
        long recepcionistaId) {
}
