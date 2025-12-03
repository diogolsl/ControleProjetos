package trabalhoA2;

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
	public Responsavel salvarResponsavel(@RequestBody Responsavel responsavel) {
		return responsavelRepository.save(responsavel);
	}
	
	@DeleteMapping("/{id_responsavel}")
	public void deletarResponsavel(@PathVariable Long id_responsavel) {
		responsavelRepository.deleteById(id_responsavel);
	}
	
	@PutMapping("/{id_responsavel}")
	public Responsavel atualizarResponsavel(@PathVariable Long id_responsavel, @RequestBody Responsavel responsavel) {
		Optional<Responsavel> oResponsavel = responsavelRepository.findById(id_responsavel);
		if(oResponsavel.isPresent()) {
			Responsavel r = oResponsavel.get();
			r.setNome(responsavel.getNome());
			r.setEmail(responsavel.getEmail());
			return responsavelRepository.save(r);

		}
		return null;
	}
}
