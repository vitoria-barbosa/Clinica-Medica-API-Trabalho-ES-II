package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
public class ProfissionalSaude extends Usuario {
    @ManyToOne
    @JoinColumn(name = "especialidade_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Especialidade especialidade;

    @Column(nullable = false)
    private String registroProfissional;

    @OneToMany(mappedBy = "profissionalSaude", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaTurno> gradeHorarios;

    public ProfissionalSaude() {
    }
}
