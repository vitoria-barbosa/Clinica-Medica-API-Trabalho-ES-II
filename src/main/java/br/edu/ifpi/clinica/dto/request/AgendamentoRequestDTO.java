package br.edu.ifpi.clinica.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @Future(message = "A data e hora do agendamento deve ser no futuro")
        @NotNull(message = "A data e hora do agendamento são obrigatórios")
        LocalDateTime dataHora,

        @NotNull(message = "O ID do profissional é obrigatório")
        @Positive(message = "O ID do profissional deve ser maior que zero")
        long profissionalId,

        @NotNull(message = "O ID do paciente é obrigatório")
        @Positive(message = "O ID do paciente deve ser maior que zero")
        long pacienteId,

        @NotNull(message = "O ID de recepcionista é obrigatório")
        @Positive(message = "O ID de recepcionista deve ser maior que zero")
        long recepcionistaId) {
}
