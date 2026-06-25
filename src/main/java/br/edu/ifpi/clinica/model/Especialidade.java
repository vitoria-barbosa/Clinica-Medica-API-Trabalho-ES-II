package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private double valorConsulta;

    public Especialidade() {
    }

    public Especialidade(String nome, double valorConsulta) {
        this.nome = nome;
        this.valorConsulta = valorConsulta;
    }
}
