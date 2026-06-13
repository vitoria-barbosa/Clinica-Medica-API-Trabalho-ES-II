package br.edu.ifpi.clinica.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Recepcionista extends Usuario {
    public Recepcionista() {
    }

    public Recepcionista(String cpf, String nome, String telefone) {
        super(cpf, nome, telefone);
    }
}
