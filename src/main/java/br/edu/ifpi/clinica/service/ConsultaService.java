package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.ConsultaRequestDTO;
import br.edu.ifpi.clinica.dto.response.ConsultaDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.Agendamento;
import br.edu.ifpi.clinica.model.Consulta;
import br.edu.ifpi.clinica.repository.AgendamentoRepository;
import br.edu.ifpi.clinica.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final AgendamentoRepository agendamentoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, AgendamentoRepository agendamentoRepository) {
        this.consultaRepository = consultaRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public ConsultaDTO insert(ConsultaRequestDTO dto) {
        Agendamento agendamento = agendamentoRepository.findById(dto.agendamentoId())
                .orElseThrow(() -> new DadoInvalidoException("Não existe nenhum agendamento com esse ID."));

        Consulta consulta = dto.toEntity();
        consulta.setAgendamento(agendamento);
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
        consultaRepository.delete(consulta);
    }
}
