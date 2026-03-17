package trabalhoA2.controller;

import trabalhoA2.dto.ResponsavelRequestDTO;
import trabalhoA2.model.Responsavel;
import trabalhoA2.repository.ResponsavelRepository;
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
@RequestMapping("/api/Responsavel")
public class ResponsavelController {
	
	@Autowired
	ResponsavelRepository responsavelRepository;
	
	@GetMapping
	public List<Responsavel> listaResponsaveis(){
		return responsavelRepository.findAll();
	}
	
	@PostMapping
	public Responsavel salvarResponsavel(@RequestBody ResponsavelRequestDTO dto) {
		Responsavel responsavel = new Responsavel();

		responsavel.setNome(dto.nome());
		responsavel.setEmail(dto.email());

		return responsavelRepository.save(responsavel);
	}
	
	@DeleteMapping("/{idResponsavel}")
	public void deletarResponsavel(@PathVariable Long idResponsavel) {
		responsavelRepository.deleteById(idResponsavel);
	}
	
	@PutMapping("/{idResponsavel}")
	public Responsavel atualizarResponsavel(@PathVariable Long idResponsavel, @RequestBody ResponsavelRequestDTO dto) {
		Optional<Responsavel> oResponsavel = responsavelRepository.findById(idResponsavel);
		if(oResponsavel.isPresent()) {
			Responsavel r = oResponsavel.get();
			r.setNome(dto.nome());
			r.setEmail(dto.email());
			return responsavelRepository.save(r);

		}
		return null;
	}
}
