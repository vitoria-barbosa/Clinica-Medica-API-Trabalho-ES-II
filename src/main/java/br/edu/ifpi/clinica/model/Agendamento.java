package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime datHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private ProfissionalSaude profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recepcionista_id", nullable = false)
    private Recepcionista recepcionista;

    public Agendamento() {
    }

    public Agendamento(LocalDateTime datHora, ProfissionalSaude profissional, Paciente paciente, Recepcionista recepcionista) {
        this.datHora = datHora;
        this.profissional = profissional;
        this.paciente = paciente;
        this.recepcionista = recepcionista;
    }
}
