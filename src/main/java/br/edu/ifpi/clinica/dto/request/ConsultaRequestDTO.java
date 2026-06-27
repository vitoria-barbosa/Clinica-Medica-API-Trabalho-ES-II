package br.edu.ifpi.clinica.dto.request;

import br.edu.ifpi.clinica.model.Consulta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ConsultaRequestDTO(
        @NotNull(message = "O ID do agendamento é obrigatório")
        @Positive(message = "O ID do profissional deve ser maior que zero")
        Long agendamentoId,
        String sintomas,
        String diagnostico,
        String prescricao,
        String exames,
        @NotNull(message = "O valor da consulta é obrigatório")
        @Positive(message = "O valor total da consulta deve ser maior que zero")
        double valorTotal) {

    public Consulta toEntity() {
        return new Consulta(this.sintomas, this.diagnostico, this.prescricao,
                this.exames, this.valorTotal);
    }
}
