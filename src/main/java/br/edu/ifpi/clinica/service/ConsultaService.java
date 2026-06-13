package br.edu.ifpi.clinica.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.ifpi.clinica.dto.ConsultaDTO;
import br.edu.ifpi.clinica.dto.ConsultaRequestDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.DatabaseException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Consulta;
import br.edu.ifpi.clinica.model.Paciente;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.model.Recepcionista;
import br.edu.ifpi.clinica.repository.ConsultaRepository;
import br.edu.ifpi.clinica.repository.PacienteRepository;
import br.edu.ifpi.clinica.repository.ProfissionalSaudeRepository;
import br.edu.ifpi.clinica.repository.RecepcionistaRepository;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final PacienteRepository pacienteRepository;
    private final RecepcionistaRepository recepcionistaRepository;

    public ConsultaService(ConsultaRepository consultaRepository, ProfissionalSaudeRepository profissionalSaudeRepository, PacienteRepository pacienteRepository, RecepcionistaRepository recepcionistaRepository) {
        this.consultaRepository = consultaRepository;
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.pacienteRepository = pacienteRepository;
        this.recepcionistaRepository = recepcionistaRepository;
    }

    public ConsultaDTO insert(ConsultaRequestDTO dto) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new DadoInvalidoException("ID do profissional inválido."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new DadoInvalidoException("ID do paciente inválido."));

        Recepcionista recepcionista = recepcionistaRepository.findById(dto.recepcionistaId())
                .orElseThrow(() -> new DadoInvalidoException("ID de recepcionista inválido."));

        Consulta consulta = dto.toEntity();
        consulta.setProfissional(profissional);
        consulta.setPaciente(paciente);
        consulta.setRecepcionista(recepcionista);
        consulta = consultaRepository.save(consulta);
        return new ConsultaDTO(consulta);
    }

    public ConsultaDTO findById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma consulta com esse ID."));

        return new ConsultaDTO(consulta);
    }

    public List<ConsultaDTO> findAll() {
        List<Consulta> consultas = consultaRepository.findAll();

        return consultas.stream().map(ConsultaDTO::new).toList();
    }

    public ConsultaDTO update(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma consulta com esse ID."));

        ProfissionalSaude profissional = profissionalSaudeRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new DadoInvalidoException("ID do profissional inválido."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new DadoInvalidoException("ID do paciente inválido."));

        Recepcionista recepcionista = recepcionistaRepository.findById(dto.recepcionistaId())
                .orElseThrow(() -> new DadoInvalidoException("ID de recepcionista inválido."));

        consulta.setDatHora(dto.datHora());
        consulta.setProfissional(profissional);
        consulta.setPaciente(paciente);
        consulta.setRecepcionista(recepcionista);
        consulta.setSintomas(dto.sintomas());
        consulta.setDiagnostico(dto.diagnostico());
        consulta.setPrescricao(dto.prescricao());
        consulta.setExames(dto.exames());
        consulta.setValorTotal(dto.valorTotal());

        consulta = consultaRepository.save(consulta);

        return new ConsultaDTO(consulta);
    }

    public void delete(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhuma consulta com esse ID."));

        try {
            consultaRepository.delete(consulta);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
