package trabalhoA2.dto;

public record TarefaResponseDTO(
        Long idTarefa,
        String descricao,
        String statusTarefa,
        String nomeProjeto
) {}
