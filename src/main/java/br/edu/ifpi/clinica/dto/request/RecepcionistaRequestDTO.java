package br.edu.ifpi.clinica.dto.request;

import br.edu.ifpi.clinica.model.Recepcionista;
import jakarta.validation.constraints.NotBlank;

public record RecepcionistaRequestDTO(
        @NotBlank(message = "O CPF não pode ser vazio e é obrigatório")
        String cpf,
        @NotBlank(message = "O nome não pode ser vazio e é obrigatório")
        String nome,
        @NotBlank(message = "O telefone não pode ser vazio e é obrigatório")
        String telefone) {

    public Recepcionista toEntity() {
        return new Recepcionista(this.cpf, this.nome, this.telefone);
    }
}
