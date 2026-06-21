package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Dia;
import jakarta.validation.constraints.NotBlank;

public record DiaRequestDTO(
        @NotBlank(message = "O nome do dia não pode ser vazio e é obrigatório")
        String nome) {

    public Dia toEntity() {
        return new Dia(this.nome.toLowerCase());
    }
}
