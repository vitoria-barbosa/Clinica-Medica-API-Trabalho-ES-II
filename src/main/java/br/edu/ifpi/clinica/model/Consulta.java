package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agendamento_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Agendamento agendamento;

    private String sintomas;

    private String diagnostico;

    private String prescricao;

    private String exames;

    @Column(nullable = false)
    private double valorTotal;

    public Consulta() {
    }

    public Consulta(String sintomas, String diagnostico, String prescricao, String exames, double valorTotal) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
        this.exames = exames;
        this.valorTotal = valorTotal;
    }
}
