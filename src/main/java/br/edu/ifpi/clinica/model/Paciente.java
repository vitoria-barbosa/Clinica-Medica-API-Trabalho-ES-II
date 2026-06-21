package br.edu.ifpi.clinica.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Paciente extends Usuario {
    private int idade;

    private String planoDeSaude;

    private String historicoMedico;

    public Paciente() {
    }

    public Paciente(String cpf, String nome, String telefone, int idade, String planoDeSaude, String historicoMedico) {
        super(cpf, nome, telefone);
        this.idade = idade;
        this.planoDeSaude = planoDeSaude;
        this.historicoMedico = historicoMedico;
    }
}
