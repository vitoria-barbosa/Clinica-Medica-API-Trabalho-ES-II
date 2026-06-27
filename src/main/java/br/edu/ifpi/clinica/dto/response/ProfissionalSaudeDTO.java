package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.ProfissionalSaude;

import java.util.List;

public record ProfissionalSaudeDTO(long id, String cpf, String nome, String telefone, long especialidadeId,
                                   String nomeEspecialidade, String registroProfissional,
                                   List<DiaTurnoDTO> gradeHorarios) {
    public ProfissionalSaudeDTO(ProfissionalSaude profissional) {
        this(profissional.getId(), profissional.getCpf(), profissional.getNome(),
                profissional.getTelefone(), profissional.getEspecialidade().getId(), profissional.getEspecialidade().getNome(),
                profissional.getRegistroProfissional(), profissional.getGradeHorarios() == null ? null : profissional.getGradeHorarios().stream().map(DiaTurnoDTO::new).toList());
    }
}
