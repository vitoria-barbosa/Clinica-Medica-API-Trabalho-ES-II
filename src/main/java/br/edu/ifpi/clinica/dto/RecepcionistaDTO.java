package br.edu.ifpi.clinica.dto;

import br.edu.ifpi.clinica.model.Recepcionista;

public record RecepcionistaDTO(long id, String cpf, String nome, String telefone) {
    public RecepcionistaDTO(Recepcionista recepcionista) {
        this(recepcionista.getId(), recepcionista.getCpf(), recepcionista.getNome(), recepcionista.getTelefone());
    }
}
