package trabalhoA2.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalhoA2.dto.ResponsavelRequestDTO;
import trabalhoA2.dto.ResponsavelResponseDTO;
import trabalhoA2.model.Responsavel;
import trabalhoA2.service.ResponsavelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

	private final ResponsavelService responsavelService;

	public ResponsavelController(ResponsavelService responsavelService) {
		this.responsavelService = responsavelService;
	}

	@PostMapping
	public ResponseEntity<Responsavel> salvarResponsavel(@Valid @RequestBody ResponsavelRequestDTO dto) {
		Responsavel responsavel = responsavelService.salvar(dto);
		return ResponseEntity.ok(responsavel);
	}

	@GetMapping
	public ResponseEntity<List<ResponsavelResponseDTO>> listarTodos() {
		return ResponseEntity.ok(responsavelService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponsavelResponseDTO> buscarPorId(@PathVariable Long id) {
		Optional<ResponsavelResponseDTO> responsavel = responsavelService.buscarPorId(id);

		if (responsavel.isPresent()) {
			return ResponseEntity.ok(responsavel.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Responsavel> atualizarResponsavel(@PathVariable Long id, @Valid @RequestBody ResponsavelRequestDTO dto) {
		Responsavel atualizado = responsavelService.atualizar(id, dto);

		if (atualizado != null) {
			return ResponseEntity.ok(atualizado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarResponsavel(@PathVariable Long id) {
		Optional<ResponsavelResponseDTO> responsavel = responsavelService.buscarPorId(id);

		if (responsavel.isPresent()) {
			responsavelService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
