package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class DiaTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalSaude profissionalSaude;

    @ManyToOne
    @JoinColumn(name = "dia_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dia dia;

    @ManyToOne
    @JoinColumn(name = "turno_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Turno turno;

    public DiaTurno() {
    }

    public DiaTurno(ProfissionalSaude profissionalSaude, Dia dia, Turno turno) {
        this.profissionalSaude = profissionalSaude;
        this.dia = dia;
        this.turno = turno;
    }
}
