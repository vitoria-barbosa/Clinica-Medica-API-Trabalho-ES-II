package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.AgendamentoDTO;
import br.edu.ifpi.clinica.dto.AgendamentoRequestDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Agendamento;
import br.edu.ifpi.clinica.model.Paciente;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.model.Recepcionista;
import br.edu.ifpi.clinica.repository.AgendamentoRepository;
import br.edu.ifpi.clinica.repository.PacienteRepository;
import br.edu.ifpi.clinica.repository.ProfissionalSaudeRepository;
import br.edu.ifpi.clinica.repository.RecepcionistaRepository;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final PacienteRepository pacienteRepository;
    private final RecepcionistaRepository recepcionistaRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ProfissionalSaudeRepository profissionalSaudeRepository, PacienteRepository pacienteRepository, RecepcionistaRepository recepcionistaRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.pacienteRepository = pacienteRepository;
        this.recepcionistaRepository = recepcionistaRepository;
    }

    public AgendamentoDTO insert(AgendamentoRequestDTO dto) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new DadoInvalidoException("ID do profissional inválido."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new DadoInvalidoException("ID do paciente inválido."));

        Recepcionista recepcionista = recepcionistaRepository.findById(dto.recepcionistaId())
                .orElseThrow(() -> new DadoInvalidoException("ID de recepcionista inválido."));

        Agendamento agendamento = new Agendamento(dto.datHora(), profissional, paciente, recepcionista);
        agendamento = agendamentoRepository.save(agendamento);
        return new AgendamentoDTO(agendamento);
    }

    public AgendamentoDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum agendamento com esse ID."));

        return new AgendamentoDTO(agendamento);
    }

    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return agendamentos.stream().map(AgendamentoDTO::new).toList();
    }

    public AgendamentoDTO update(Long id, AgendamentoRequestDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum agendamento com esse ID."));

        ProfissionalSaude profissional = profissionalSaudeRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new DadoInvalidoException("ID do profissional inválido."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new DadoInvalidoException("ID do paciente inválido."));

        Recepcionista recepcionista = recepcionistaRepository.findById(dto.recepcionistaId())
                .orElseThrow(() -> new DadoInvalidoException("ID de recepcionista inválido."));

        agendamento.setDatHora(dto.datHora());
        agendamento.setProfissional(profissional);
        agendamento.setPaciente(paciente);
        agendamento.setRecepcionista(recepcionista);

        agendamento = agendamentoRepository.save(agendamento);

        return new AgendamentoDTO(agendamento);
    }

    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum agendamento com esse ID."));

        try {
            agendamentoRepository.delete(agendamento);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
