package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Paciente;

public record PacienteDTO(long id, String cpf, String nome, String telefone, int idade, String planoDeSaude,
                          String historicoMedico) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getCpf(), paciente.getNome(), paciente.getTelefone(),
                paciente.getIdade(), paciente.getPlanoDeSaude(), paciente.getHistoricoMedico());
    }
}
