package br.edu.ifpi.clinica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Paciente extends Usuario {
    private int idade;

    private String planoDeSaude;

    private String historicoMedico;

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos;

    public Paciente() {
    }

    public Paciente(String cpf, String nome, String telefone, int idade, String planoDeSaude, String historicoMedico) {
        super(cpf, nome, telefone);
        this.idade = idade;
        this.planoDeSaude = planoDeSaude;
        this.historicoMedico = historicoMedico;
    }
}
