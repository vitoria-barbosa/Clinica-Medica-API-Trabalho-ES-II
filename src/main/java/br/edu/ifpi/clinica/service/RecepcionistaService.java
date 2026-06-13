package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.RecepcionistaDTO;
import br.edu.ifpi.clinica.dto.RecepcionistaRequestDTO;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Recepcionista;
import br.edu.ifpi.clinica.repository.RecepcionistaRepository;

@Service
public class RecepcionistaService {
    private final RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaService(RecepcionistaRepository recepcionistaRepository) {
        this.recepcionistaRepository = recepcionistaRepository;
    }

    public RecepcionistaDTO insert(RecepcionistaRequestDTO dto) {
        Recepcionista recepcionista = dto.toEntity();
        recepcionista = recepcionistaRepository.save(recepcionista);
        return new RecepcionistaDTO(recepcionista);
    }

    public RecepcionistaDTO findById(Long id) {
        Recepcionista recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma recepcionista com esse ID."));

        return new RecepcionistaDTO(recepcionista);
    }

    public List<RecepcionistaDTO> findAll() {
        List<Recepcionista> recepcionistas = recepcionistaRepository.findAll();

        return recepcionistas.stream().map(RecepcionistaDTO::new).toList();
    }

    public RecepcionistaDTO update(Long id, RecepcionistaRequestDTO dto) {
        Recepcionista recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma recepcionista com esse ID."));

        recepcionista.setCpf(dto.cpf());
        recepcionista.setNome(dto.nome());
        recepcionista.setTelefone(dto.telefone());

        recepcionista = recepcionistaRepository.save(recepcionista);

        return new RecepcionistaDTO(recepcionista);
    }

    public void delete(Long id) {
        Recepcionista recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma recepcionista com esse ID."));

        try {
            recepcionistaRepository.delete(recepcionista);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
