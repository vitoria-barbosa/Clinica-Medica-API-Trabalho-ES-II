package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DiaTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "dia_id", nullable = false)
    private Dia dia;

    @ManyToOne
    @JoinColumn(name = "turno_id", nullable = false)
    private Turno turno;

    @ManyToMany(mappedBy = "gradeHorarios")
    private List<ProfissionalSaude> profissionais;

    public DiaTurno() {
    }

    public DiaTurno(Dia dia, Turno turno) {
        this.dia = dia;
        this.turno = turno;
    }
}
