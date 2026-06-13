package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProfissionalSaude extends Usuario {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;

    @Column(nullable = false)
    private String registroProfissional;

    @ManyToMany()
    @JoinTable(name = "grade_horario", joinColumns = @JoinColumn(name = "profissional_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "dia_turno_id", nullable = false)
    )
    private List<DiaTurno> gradeHorarios;

    @OneToMany(mappedBy = "profissional")
    private List<Agendamento> agendamentos;

    public ProfissionalSaude() {
    }
}
