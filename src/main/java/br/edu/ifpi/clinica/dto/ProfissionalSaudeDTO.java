package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.ProfissionalSaude;

public record ProfissionalSaudeDTO(long id, String cpf, String nome, String telefone, long especialidadeId,
                                   String nomeEspecialidade, String registroProfissional) {
    public ProfissionalSaudeDTO(ProfissionalSaude profissional) {
        this(profissional.getId(), profissional.getCpf(), profissional.getNome(), profissional.getTelefone(), profissional.getEspecialidade().getId(), profissional.getEspecialidade().getNome(), profissional.getRegistroProfissional());
    }
}
