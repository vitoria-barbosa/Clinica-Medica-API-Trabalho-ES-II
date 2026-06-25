package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.AgendamentoRequestDTO;
import br.edu.ifpi.clinica.dto.response.AgendamentoDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.*;
import br.edu.ifpi.clinica.repository.AgendamentoRepository;
import br.edu.ifpi.clinica.repository.PacienteRepository;
import br.edu.ifpi.clinica.repository.ProfissionalSaudeRepository;
import br.edu.ifpi.clinica.repository.RecepcionistaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

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

        validarDiaTurnoProfissional(dto.dataHora(), profissional);
        validarDataHora(dto.dataHora(), dto.profissionalId());
        validarQtdAgendamentos(dto.dataHora(), dto.profissionalId());
        if (agendamentoRepository.findAgendamentoByDataHoraAndPacienteId(dto.dataHora(), dto.pacienteId()).isPresent()) {
            throw new DadoInvalidoException("Esse paciente já possui outro atendimento nesse dia e horário.");
        }

        Agendamento agendamento = new Agendamento(dto.dataHora(), profissional, paciente, recepcionista);
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

    public List<AgendamentoDTO> findByPaciente(Long pacienteId) {
        return agendamentoRepository.findByPacienteId(pacienteId)
                .stream().map(AgendamentoDTO::new).toList();
    }

    public List<AgendamentoDTO> findByProfissional(Long profissionalId) {
        return agendamentoRepository.findByProfissionalId(profissionalId)
                .stream().map(AgendamentoDTO::new).toList();
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

        validarDiaTurnoProfissional(dto.dataHora(), profissional);
        validarDataHora(dto.dataHora(), dto.profissionalId());
        validarQtdAgendamentos(dto.dataHora(), dto.profissionalId());
        if (agendamentoRepository.findAgendamentoByDataHoraAndPacienteId(dto.dataHora(), dto.pacienteId()).isPresent()) {
            throw new DadoInvalidoException("Esse paciente já possui outro atendimento nesse dia e horário.");
        }

        agendamento.setDataHora(dto.dataHora());
        agendamento.setProfissional(profissional);
        agendamento.setPaciente(paciente);
        agendamento.setRecepcionista(recepcionista);

        agendamento = agendamentoRepository.save(agendamento);

        return new AgendamentoDTO(agendamento);
    }

    public void delete(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum agendamento com esse ID."));
        agendamentoRepository.delete(agendamento);
    }

    private void validarDataHora(LocalDateTime dataHora, long profissionalId) {
        if (dataHora.toLocalTime().isBefore(LocalTime.of(8, 0)) || dataHora.toLocalTime().isAfter(LocalTime.of(18, 0))) {
            throw new DadoInvalidoException("O horário do agendamento tem que ser entre 8:00 e 18:00.");
        }

        if (agendamentoRepository.findAgendamentoByDataHoraAndProfissionalId(dataHora, profissionalId).isPresent()) {
            throw new DadoInvalidoException("Esse profissional já possui um atendimento nessa data e nesse horário.");
        }

    }

    private void validarQtdAgendamentos(LocalDateTime data, long profissionalId) {
        if (data.toLocalTime().isBefore(LocalTime.of(13, 0))) {
            validarQtdAgendamentosTurnoManha(data, profissionalId);
        } else {
            validarQtdAgendamentosTurnoTarde(data, profissionalId);
        }
    }

    private void validarQtdAgendamentosTurnoManha(LocalDateTime data, long profissionalId) {
        LocalDateTime inicioTurno = data.toLocalDate().atTime(8, 0);
        LocalDateTime fimTurno = data.toLocalDate().atTime(11, 59);
        List<Agendamento> listaAgendamentos = agendamentoRepository.findAgendamentoByDataHoraBetweenAndProfissionalId(inicioTurno, fimTurno, profissionalId);
        int qtdAgendamentos = listaAgendamentos.size();
        if (qtdAgendamentos == 15) {
            throw new DadoInvalidoException("Não é possível agendar um atendimento no turno da manhã nessa data.O máximo é 15 agendamentos por turno.");
        }
    }

    private void validarQtdAgendamentosTurnoTarde(LocalDateTime data, long profissionalId) {
        LocalDateTime inicioTurno = data.toLocalDate().atTime(14, 0);
        LocalDateTime fimTurno = data.toLocalDate().atTime(17, 59);
        List<Agendamento> listaAgendamentos = agendamentoRepository.findAgendamentoByDataHoraBetweenAndProfissionalId(inicioTurno, fimTurno, profissionalId);
        int qtdAgendamentos = listaAgendamentos.size();
        if (qtdAgendamentos == 15) {
            throw new DadoInvalidoException("Não é possível agendar um atendimento no turno da manhã nessa data.O máximo é 15 agendamentos por turno.");
        }
    }

    private void validarDiaTurnoProfissional(LocalDateTime dataHora, ProfissionalSaude profissional) {
        Locale Brasil = Locale.forLanguageTag("pt-BR");
        String nomeCompletoDia = dataHora.getDayOfWeek().getDisplayName(TextStyle.FULL, Brasil);
        String turno;

        if (dataHora.toLocalTime().isBefore(LocalTime.of(13, 0))) {
            turno = "manhã";
        } else {
            turno = "tarde";
        }

        for (DiaTurno diaTurno : profissional.getGradeHorarios()) {
            if (diaTurno.getDia().getNome().equals(nomeCompletoDia) && diaTurno.getTurno().getNome().equals(turno)) {
                return;
            }
        }
        throw new DadoInvalidoException("Esse profissional não atende no dia e horário fornecido.");
    }
}