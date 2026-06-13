package br.edu.ifpi.clinica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Consulta extends Agendamento {
    private String sintomas;

    private String diagnostico;

    private String prescricao;

    private String exames;

    @Column(nullable = false)
    private double valorTotal;

    public Consulta() {
    }


}
