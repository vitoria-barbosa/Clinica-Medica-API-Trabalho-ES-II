package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.PacienteRequestDTO;
import br.edu.ifpi.clinica.dto.response.PacienteDTO;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Paciente;
import br.edu.ifpi.clinica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        pacienteRepository.delete(paciente);
    }
}
