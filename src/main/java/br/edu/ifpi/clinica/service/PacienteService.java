package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.PacienteDTO;
import br.edu.ifpi.clinica.dto.PacienteRequestDTO;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Paciente;
import br.edu.ifpi.clinica.repository.PacienteRepository;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteDTO insert(PacienteRequestDTO dto) {
        Paciente paciente = dto.toEntity();
        paciente = pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
    }

    public PacienteDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum paciente com esse ID."));

        return new PacienteDTO(paciente);
    }

    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        return pacientes.stream().map(PacienteDTO::new).toList();
    }

    public PacienteDTO update(Long id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum paciente com esse ID."));

        paciente.setCpf(dto.cpf());
        paciente.setNome(dto.nome());
        paciente.setTelefone(dto.telefone());
        paciente.setIdade(dto.idade());
        paciente.setPlanoDeSaude(dto.planoDeSaude());
        paciente.setHistoricoMedico(dto.historicoMedico());

        paciente = pacienteRepository.save(paciente);

        return new PacienteDTO(paciente);
    }

    public void delete(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum paciente com esse ID."));

        try {
            pacienteRepository.delete(paciente);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
