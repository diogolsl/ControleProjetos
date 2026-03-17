package trabalhoA2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaRequestDTO(
        @NotNull(message = "A tarefa precisa estar vinculada a um projeto(ID)")
        Long idProjeto,

        @NotBlank(message = "A descrição da tarefa é obrigatória")
        String descricao,

        @NotBlank(message = "O status da tarefa é obrigatório")
        String statusTarefa
) {}
