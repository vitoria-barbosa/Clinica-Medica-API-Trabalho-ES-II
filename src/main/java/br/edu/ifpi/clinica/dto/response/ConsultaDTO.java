package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Consulta;

public record ConsultaDTO(long id, AgendamentoDTO agendamento, String sintomas,
                          String diagnostico, String prescricao, String exames, double valorTotal) {

    public ConsultaDTO(Consulta consulta) {
        this(consulta.getId(), new AgendamentoDTO(consulta.getAgendamento()),
                consulta.getSintomas(), consulta.getDiagnostico(), consulta.getPrescricao(),
                consulta.getExames(), consulta.getValorTotal());
    }
}
