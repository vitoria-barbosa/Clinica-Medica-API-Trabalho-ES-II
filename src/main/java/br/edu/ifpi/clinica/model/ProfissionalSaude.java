package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ProfissionalSaude extends Usuario {
    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;

    @Column(nullable = false)
    private String registroProfissional;

    @OneToMany(mappedBy = "profissionalSaude")
    private List<DiaTurno> gradeHorarios;

    public ProfissionalSaude() {
    }
}
