package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaDTO(long id, LocalDateTime datHora, long profissionalId, String nomeProfissional,
                          long pacienteId, String nomePaciente, long recepcionistaId, String nomeRecepcionista,
                          String sintomas, String diagnostico, String prescricao, String exames, double valorTotal) {

    public ConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getDataHora(),
                consulta.getProfissional().getId(), consulta.getProfissional().getNome(),
                consulta.getPaciente().getId(), consulta.getPaciente().getNome(),
                consulta.getRecepcionista().getId(), consulta.getRecepcionista().getNome(),
                consulta.getSintomas(), consulta.getDiagnostico(), consulta.getPrescricao(),
                consulta.getExames(), consulta.getValorTotal());
    }
}
