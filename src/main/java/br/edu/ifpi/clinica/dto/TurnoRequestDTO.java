package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Turno;
import jakarta.validation.constraints.NotBlank;

public record TurnoRequestDTO(
        @NotBlank(message = "O nome do turno não pode ser vazio e é obrigatório")
        String nome) {

    public Turno toEntity() {
        return new Turno(this.nome.toLowerCase());
    }
}
