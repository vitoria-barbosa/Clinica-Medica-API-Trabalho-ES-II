package br.edu.ifpi.clinica.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public Turno() {
    }

    public Turno(String nome) {
        this.nome = nome;
    }
}
