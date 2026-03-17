package trabalhoA2.controller;

import trabalhoA2.dto.TarefaRequestDTO;
import trabalhoA2.model.Projeto;
import trabalhoA2.model.Tarefa;
import trabalhoA2.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Tarefa")
public class TarefaController {
    
    @Autowired
    TarefaRepository tarefaRepository;
    
    @GetMapping
    public List<Tarefa> listaTarefas(){
        return tarefaRepository.findAll();
    }
    
    @PostMapping
    public Tarefa salvarTarefa(@RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(dto.descricao());
        tarefa.setStatusTarefa(dto.statusTarefa());

        Projeto projeto = new Projeto();
        projeto.setIdProjeto(dto.idProjeto());
        tarefa.setProjeto(projeto);

        return tarefaRepository.save(tarefa);
    }
    
    @DeleteMapping("/{idTarefa}")
    public void deletarTarefa(@PathVariable Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);
    }
    
    @PutMapping("/{idTarefa}")
    public Tarefa atualizarTarefa(@PathVariable Long idTarefa, @RequestBody TarefaRequestDTO dto) {
        Optional<Tarefa> oTarefa = tarefaRepository.findById(idTarefa);
        if(oTarefa.isPresent()) {
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
}
