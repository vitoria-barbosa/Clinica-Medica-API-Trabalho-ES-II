package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Dia;

public record DiaDTO(long id, String nome) {
    public DiaDTO(Dia dia) {
        this(dia.getId(), dia.getNome());
    }
}