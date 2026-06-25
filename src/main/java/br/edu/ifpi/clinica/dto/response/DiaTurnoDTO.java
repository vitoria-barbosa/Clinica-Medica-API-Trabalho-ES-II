package br.edu.ifpi.clinica.dto.response;

import br.edu.ifpi.clinica.model.Dia;
import br.edu.ifpi.clinica.model.DiaTurno;
import br.edu.ifpi.clinica.model.Turno;

public record DiaTurnoDTO(long id, Dia dia, Turno turno) {
    public DiaTurnoDTO(DiaTurno diaTurno) {
        this(diaTurno.getId(), diaTurno.getDia(), diaTurno.getTurno());
    }
}