package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double valorConsulta;

    @OneToMany(mappedBy = "especialidade")
    private List<ProfissionalSaude> profissionais;

    public Especialidade() {
    }

    public Especialidade(String nome, double valorConsulta) {
        this.nome = nome;
        this.valorConsulta = valorConsulta;
    }
}
