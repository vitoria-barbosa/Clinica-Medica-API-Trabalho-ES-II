package br.edu.ifpi.clinica.service;

import br.edu.ifpi.clinica.dto.request.DiaTurnoRequestDTO;
import br.edu.ifpi.clinica.dto.request.ProfissionalSaudeRequestDTO;
import br.edu.ifpi.clinica.dto.response.ProfissionalSaudeDTO;
import br.edu.ifpi.clinica.exception.DadoInvalidoException;
import br.edu.ifpi.clinica.exception.RecursoNaoEncontradoException;
import br.edu.ifpi.clinica.model.DiaTurno;
import br.edu.ifpi.clinica.model.Especialidade;
import br.edu.ifpi.clinica.model.ProfissionalSaude;
import br.edu.ifpi.clinica.repository.EspecialidadeRepository;
import br.edu.ifpi.clinica.repository.ProfissionalSaudeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalSaudeService {
    private final ProfissionalSaudeRepository profissionalSaudeRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final DiaTurnoService diaTurnoService;

    public ProfissionalSaudeService(ProfissionalSaudeRepository profissionalSaudeRepository, EspecialidadeRepository especialidadeRepository, DiaTurnoService diaTurnoService) {
        this.profissionalSaudeRepository = profissionalSaudeRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.diaTurnoService = diaTurnoService;
    }

    public ProfissionalSaudeDTO insert(ProfissionalSaudeRequestDTO dto) {
        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new DadoInvalidoException("ID da especialidade inválido."));

        ProfissionalSaude profissional = dto.toEntity();
        profissional.setEspecialidade(especialidade);
        profissional = profissionalSaudeRepository.save(profissional);
        return new ProfissionalSaudeDTO(profissional);
    }

    public ProfissionalSaudeDTO findById(Long id) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findByIdComHorarios(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        return new ProfissionalSaudeDTO(profissional);
    }

    public List<ProfissionalSaudeDTO> findAll() {
        List<ProfissionalSaude> profissionais = profissionalSaudeRepository.findAllComHorarios();

        return profissionais.stream().map(ProfissionalSaudeDTO::new).toList();
    }

    public ProfissionalSaudeDTO update(Long id, ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findByIdComHorarios(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new DadoInvalidoException("ID da especialidade inválido."));

        profissional.setCpf(dto.cpf());
        profissional.setNome(dto.nome());
        profissional.setTelefone(dto.telefone());
        profissional.setEspecialidade(especialidade);
        profissional.setRegistroProfissional(dto.registroProfissional());

        profissional = profissionalSaudeRepository.save(profissional);

        return new ProfissionalSaudeDTO(profissional);
    }

    public void delete(Long id) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        profissionalSaudeRepository.delete(profissional);
    }

    public ProfissionalSaudeDTO adicionarDiaTurnoAoProfissional(long id, DiaTurnoRequestDTO dto) {
        ProfissionalSaude profissional = profissionalSaudeRepository.findByIdComHorarios(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        DiaTurno dt = diaTurnoService.insert(profissional, dto);
        profissional.getGradeHorarios().add(dt);
        return new ProfissionalSaudeDTO(profissional);
    }

    public void removerDiaTurnoDeProfissional(long idProfissional, long idDiaTurno) {
        profissionalSaudeRepository.findById(idProfissional)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Não existe nenhum profissional com esse ID."));

        diaTurnoService.delete(idDiaTurno);
    }

    public List<ProfissionalSaudeDTO> buscarProfissionaisPelaEspecialidade(long especialidadeId) {
        List<ProfissionalSaude> profissionais = profissionalSaudeRepository.findByEspecialidadeId(especialidadeId);

        return profissionais.stream().map(ProfissionalSaudeDTO::new).toList();
    }
}
