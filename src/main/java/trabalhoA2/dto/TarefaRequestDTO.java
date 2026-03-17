package trabalhoA2.dto;

public record TarefaRequestDTO(
        Long idProjeto,
        String descricao,
        String statusTarefa
) {}
