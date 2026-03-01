package trabalhoA2.controller;

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
    public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    @DeleteMapping("/{id_tarefa}")
    public void deletarTarefa(@PathVariable Long id_tarefa) {
        tarefaRepository.deleteById(id_tarefa);
    }
    
    @PutMapping("/{id_tarefa}")
    public Tarefa atualizarTarefa(@PathVariable Long id_tarefa, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> oTarefa = tarefaRepository.findById(id_tarefa);
        if(oTarefa.isPresent()) {
            Tarefa t = oTarefa.get();
            t.setDescricao(tarefa.getDescricao());
            t.setStatus_tarefa(tarefa.getStatus_tarefa());
            return tarefaRepository.save(t);
        }
        return null;
    }
}
