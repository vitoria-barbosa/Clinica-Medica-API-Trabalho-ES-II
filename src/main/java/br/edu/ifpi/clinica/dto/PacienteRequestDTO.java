package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Paciente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PacienteRequestDTO(
        @NotBlank(message = "O CPF não pode ser vazio e é obrigatório")
        String cpf,
        @NotBlank(message = "O nome não pode ser vazio e é obrigatório")
        String nome,
        @NotBlank(message = "O telefone não pode ser vazio e é obrigatório")
        String telefone,
        @Min(value = 0, message = "A idade não pode ser negativa")
        int idade,
        String planoDeSaude,
        String historicoMedico) {

    public Paciente toEntity() {
        return new Paciente(this.cpf, this.nome, this.telefone, this.idade, this.planoDeSaude, this.historicoMedico);
    }
}
