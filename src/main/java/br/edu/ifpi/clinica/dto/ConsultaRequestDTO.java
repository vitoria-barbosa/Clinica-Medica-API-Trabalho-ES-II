package br.edu.ifpi.clinica.dto;

import java.time.LocalDateTime;

import br.edu.ifpi.clinica.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ConsultaRequestDTO(
        @Future(message = "A data e hora da consulta deve ser no futuro")
        @NotNull(message = "A data e hora da consulta é obrigatória")
        LocalDateTime datHora,
        @Positive(message = "O ID do profissional deve ser maior que zero")
        @NotNull(message = "O ID do profissional é obrigatório")
        long profissionalId,
        @Positive(message = "O ID do paciente deve ser maior que zero")
        @NotNull(message = "O ID do paciente é obrigatório")
        long pacienteId,
        @Positive(message = "O ID da recepcionista deve ser maior que zero")
        @NotNull(message = "O ID da recepcionista é obrigatório")
        long recepcionistaId,
        String sintomas,
        String diagnostico,
        String prescricao,
        String exames,
        @Positive(message = "O valor total da consulta deve ser maior que zero")
        double valorTotal) {

    public Consulta toEntity() {
        Consulta consulta = new Consulta();
        consulta.setDatHora(this.datHora);
        consulta.setSintomas(this.sintomas);
        consulta.setDiagnostico(this.diagnostico);
        consulta.setPrescricao(this.prescricao);
        consulta.setExames(this.exames);
        consulta.setValorTotal(this.valorTotal);
        return consulta;
    }
}
