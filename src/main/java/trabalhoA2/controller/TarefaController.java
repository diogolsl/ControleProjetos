package trabalhoA2.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalhoA2.dto.TarefaRequestDTO;
import trabalhoA2.dto.TarefaResponseDTO;
import trabalhoA2.model.Tarefa;
import trabalhoA2.service.TarefaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> salvarTarefa(@Valid @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = tarefaService.salvar(dto);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tarefaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<TarefaResponseDTO> tarefa = tarefaService.buscarPorId(id);

        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaRequestDTO dto) {
        Tarefa atualizada = tarefaService.atualizar(id, dto);

        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        Optional<TarefaResponseDTO> tarefa = tarefaService.buscarPorId(id);

        if (tarefa.isPresent()) {
            tarefaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}