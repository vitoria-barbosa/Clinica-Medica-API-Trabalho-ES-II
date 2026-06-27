package br.edu.ifpi.clinica.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DiaTurnoRequestDTO(
        @Positive(message = "O ID do dia deve ser maior que zero")
        @NotNull(message = "O ID do dia é obrigatório")
        long diaId,
        @Positive(message = "O ID do turno deve ser maior que zero")
        @NotNull(message = "O ID do turno é obrigatório")
        long turnoId) {
}
