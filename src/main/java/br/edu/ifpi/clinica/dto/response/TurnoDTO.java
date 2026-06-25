package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Turno;

public record TurnoDTO(long id, String nome) {
    public TurnoDTO(Turno turno) {
        this(turno.getId(), turno.getNome());
    }
}
