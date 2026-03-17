package trabalhoA2.service;

import org.springframework.stereotype.Service;
import trabalhoA2.dto.ResponsavelRequestDTO;
import trabalhoA2.dto.ResponsavelResponseDTO;
import trabalhoA2.model.Responsavel;
import trabalhoA2.repository.ResponsavelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public Responsavel salvar(ResponsavelRequestDTO dto) {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome(dto.nome());
        responsavel.setEmail(dto.email());

        return responsavelRepository.save(responsavel);
    }

    public List<ResponsavelResponseDTO> listarTodos() {
        return responsavelRepository.findAll().stream()
                .map(r -> new ResponsavelResponseDTO(r.getIdResponsavel(), r.getNome(), r.getEmail()))
                .collect(Collectors.toList());
    }

    public Optional<ResponsavelResponseDTO> buscarPorId(Long id) {
        return responsavelRepository.findById(id)
                .map(r -> new ResponsavelResponseDTO(r.getIdResponsavel(), r.getNome(), r.getEmail()));
    }

    public Responsavel atualizar(Long id, ResponsavelRequestDTO dto) {
        Optional<Responsavel> oResponsavel = responsavelRepository.findById(id);

        if (oResponsavel.isPresent()) {
            Responsavel r = oResponsavel.get();
            r.setNome(dto.nome());
            r.setEmail(dto.email());

            return responsavelRepository.save(r);
        }
        return null;
    }

    public void deletar(Long id) {
        responsavelRepository.deleteById(id);
    }
}
