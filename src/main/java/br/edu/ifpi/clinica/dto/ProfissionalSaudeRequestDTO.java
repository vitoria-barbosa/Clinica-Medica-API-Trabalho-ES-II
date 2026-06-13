package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.ProfissionalSaude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProfissionalSaudeRequestDTO(
        @NotBlank(message = "O CPF não pode ser vazio e é obrigatório")
        String cpf,
        @NotBlank(message = "O nome não pode ser vazio e é obrigatório")
        String nome,
        @NotBlank(message = "O telefone não pode ser vazio e é obrigatório")
        String telefone,
        @Positive(message = "O ID da especialidade deve ser maior que zero")
        @NotNull(message = "O ID da especialidade é obrigatório")
        long especialidadeId,
        @NotBlank(message = "O registro profissional não pode ser vazio e é obrigatório")
        String registroProfissional) {

    public ProfissionalSaude toEntity() {
        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setCpf(this.cpf);
        profissional.setNome(this.nome);
        profissional.setTelefone(this.telefone);
        profissional.setRegistroProfissional(this.registroProfissional);
        return profissional;
    }
}
