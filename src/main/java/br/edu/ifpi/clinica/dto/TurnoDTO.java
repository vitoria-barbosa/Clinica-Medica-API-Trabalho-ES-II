package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Turno;

public record TurnoDTO(long id, String nome) {
    public TurnoDTO(Turno turno) {
        this(turno.getId(), turno.getNome());
    }
}
