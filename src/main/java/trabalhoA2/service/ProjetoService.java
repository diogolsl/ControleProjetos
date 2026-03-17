package trabalhoA2.service;

import org.springframework.stereotype.Service;
import trabalhoA2.dto.ProjetoRequestDTO;
import trabalhoA2.dto.ProjetoResponseDTO;
import trabalhoA2.model.Projeto;
import trabalhoA2.model.Responsavel;
import trabalhoA2.repository.ProjetoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    // CREATE (POST)
    public Projeto salvar(ProjetoRequestDTO dto) {
        Projeto novoProjeto = new Projeto();
        novoProjeto.setNomeProjeto(dto.nomeProjeto());
        novoProjeto.setDataInicio(dto.dataInicio());

        Responsavel responsavel = new Responsavel();
        responsavel.setIdResponsavel(dto.idResponsavel());
        novoProjeto.setResponsavel(responsavel);

        return projetoRepository.save(novoProjeto);
    }



    public List<ProjetoResponseDTO> listarTodos() {
        List<Projeto> projetos = projetoRepository.findAll();

        return projetos.stream()
                .map(projeto -> new ProjetoResponseDTO(
                        projeto.getIdProjeto(),
                        projeto.getNomeProjeto(),
                        projeto.getDataInicio(),
                        projeto.getResponsavel() != null ? projeto.getResponsavel().getNome() : "Sem responsável"
                ))
                .collect(Collectors.toList());
    }

    public Optional<ProjetoResponseDTO> buscarPorId(Long id) {
        return projetoRepository.findById(id).map(projeto -> new ProjetoResponseDTO(
                projeto.getIdProjeto(),
                projeto.getNomeProjeto(),
                projeto.getDataInicio(),
                projeto.getResponsavel() != null ? projeto.getResponsavel().getNome() : "Sem responsável"
        ));
    }

    public Projeto atualizar(Long id, ProjetoRequestDTO dto) {
        Optional<Projeto> oProjeto = projetoRepository.findById(id);

        if (oProjeto.isPresent()) {
            Projeto p = oProjeto.get();
            p.setNomeProjeto(dto.nomeProjeto());
            p.setDataInicio(dto.dataInicio());

            Responsavel responsavel = new Responsavel();
            responsavel.setIdResponsavel(dto.idResponsavel());
            p.setResponsavel(responsavel);

            return projetoRepository.save(p);
        }
        return null;
    }

    public void deletar(Long id) {
        projetoRepository.deleteById(id);
    }
}
