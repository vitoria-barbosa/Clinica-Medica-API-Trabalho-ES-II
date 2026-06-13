package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EspecialidadeRequestDTO(
        @NotBlank(message = "O nome da especialidade não pode ser vazio e é obrigatório")
        String nome,
        @Positive(message = "O valor da consulta deve ser maior que zero")
        double valorConsulta) {

    public Especialidade toEntity() {
        return new Especialidade(this.nome, this.valorConsulta);
    }
}
