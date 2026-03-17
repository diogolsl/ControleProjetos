package trabalhoA2.service;

import org.springframework.stereotype.Service;
import trabalhoA2.dto.TarefaRequestDTO;
import trabalhoA2.dto.TarefaResponseDTO;
import trabalhoA2.model.Projeto;
import trabalhoA2.model.Tarefa;
import trabalhoA2.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa salvar(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.descricao());
        tarefa.setStatusTarefa(dto.statusTarefa());

        Projeto projeto = new Projeto();
        projeto.setIdProjeto(dto.idProjeto());
        tarefa.setProjeto(projeto);

        return tarefaRepository.save(tarefa);
    }

    public List<TarefaResponseDTO> listarTodos() {
        return tarefaRepository.findAll().stream()
                .map(t -> new TarefaResponseDTO(
                        t.getIdTarefa(),
                        t.getDescricao(),
                        t.getStatusTarefa(),
                        t.getProjeto() != null ? t.getProjeto().getNomeProjeto() : "Sem projeto"
                ))
                .collect(Collectors.toList());
    }

    public Optional<TarefaResponseDTO> buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .map(t -> new TarefaResponseDTO(
                        t.getIdTarefa(),
                        t.getDescricao(),
                        t.getStatusTarefa(),
                        t.getProjeto() != null ? t.getProjeto().getNomeProjeto() : "Sem projeto"
                ));
    }

    public Tarefa atualizar(Long id, TarefaRequestDTO dto) {
        Optional<Tarefa> oTarefa = tarefaRepository.findById(id);

        if (oTarefa.isPresent()) {
            Tarefa t = oTarefa.get();
            t.setDescricao(dto.descricao());
            t.setStatusTarefa(dto.statusTarefa());

            Projeto projeto = new Projeto();
            projeto.setIdProjeto(dto.idProjeto());
            t.setProjeto(projeto);

            return tarefaRepository.save(t);
        }
        return null;
    }

    public void deletar(Long id) {
        tarefaRepository.deleteById(id);
    }
}
