package trabalhoA2.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjetoRequestDTO(
        @NotBlank(message = "O nome do projeto não pode estar vazio")
        String nomeProjeto,

        @NotNull(message = "Data de inicio é obrigatória")
        LocalDate dataInicio,

        @NotNull(message = "O projeto precisa ter um responsável(ID)")
        Long idResponsavel
) {}
