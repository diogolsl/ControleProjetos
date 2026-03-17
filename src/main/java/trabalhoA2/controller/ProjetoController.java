package trabalhoA2.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalhoA2.dto.ProjetoRequestDTO;
import trabalhoA2.dto.ProjetoResponseDTO;
import trabalhoA2.model.Projeto;
import trabalhoA2.service.ProjetoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Projeto")
public class ProjetoController {

	private final ProjetoService projetoService;

	public ProjetoController(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	@PostMapping
	public ResponseEntity<Projeto> salvarProjeto(@Valid @RequestBody ProjetoRequestDTO dto) {
		Projeto projetoSalvo = projetoService.salvar(dto);
		return ResponseEntity.ok(projetoSalvo);
	}

	@GetMapping
	public ResponseEntity<List<ProjetoResponseDTO>> listarTodos() {
		return ResponseEntity.ok(projetoService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
		Optional<ProjetoResponseDTO> projeto = projetoService.buscarPorId(id);

		if (projeto.isPresent()) {
			return ResponseEntity.ok(projeto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @Valid @RequestBody ProjetoRequestDTO dto) {
		Projeto projetoAtualizado = projetoService.atualizar(id, dto);

		if (projetoAtualizado != null) {
			return ResponseEntity.ok(projetoAtualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
		Optional<ProjetoResponseDTO> projeto = projetoService.buscarPorId(id);

		if (projeto.isPresent()) {
			projetoService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}