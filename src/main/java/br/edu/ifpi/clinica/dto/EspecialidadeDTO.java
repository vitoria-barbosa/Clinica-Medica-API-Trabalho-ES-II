package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Especialidade;

public record EspecialidadeDTO(long id, String nome, double valorConsulta) {
    public EspecialidadeDTO(Especialidade especialidade) {
        this(especialidade.getId(), especialidade.getNome(), especialidade.getValorConsulta());
    }
}
