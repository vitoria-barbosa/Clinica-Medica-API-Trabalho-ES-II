package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProfissionalSaude profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recepcionista_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recepcionista recepcionista;

    public Agendamento() {
    }

    public Agendamento(LocalDateTime dataHora, ProfissionalSaude profissional, Paciente paciente, Recepcionista recepcionista) {
        this.dataHora = dataHora;
        this.profissional = profissional;
        this.paciente = paciente;
        this.recepcionista = recepcionista;
    }
}
