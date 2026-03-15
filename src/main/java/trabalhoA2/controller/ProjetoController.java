package trabalhoA2.controller;

import trabalhoA2.model.Projeto;
import trabalhoA2.repository.ProjetoRepository;
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
@RequestMapping("/api/Projeto")
public class ProjetoController {
	
	@Autowired
	ProjetoRepository projetoRepository;
	
	@GetMapping
	public List<Projeto> listaProjetos() {
		return projetoRepository.findAll();
	}
	
	@PostMapping
	public Projeto salvarProjeto(@RequestBody Projeto projeto) {
		return projetoRepository.save(projeto);
	}
	
	@DeleteMapping("/{idProjeto}")
	public void deletarProjeto(@PathVariable Long idProjeto) {
		projetoRepository.deleteById(idProjeto);
	}
	
	@PutMapping("/{idProjeto}")
	public Projeto atualizarProjeto(@PathVariable Long idProjeto, @RequestBody Projeto projeto) {
		Optional<Projeto> oProjeto = projetoRepository.findById(idProjeto);
		if(oProjeto.isPresent()) {
			Projeto p = oProjeto.get();
			p.setNomeProjeto(projeto.getNomeProjeto());
			p.setDataInicio(projeto.getDataInicio());
			p.setResponsavel(projeto.getResponsavel());
			return projetoRepository.save(p);

		}
		return null;
	}
}