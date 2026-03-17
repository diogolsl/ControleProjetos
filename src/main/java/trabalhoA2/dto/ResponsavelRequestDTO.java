package trabalhoA2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResponsavelRequestDTO(
        @NotBlank(message = "O nome do responsável é obrigatório")
        String nome,

        @Email(message = "Formato inválido")
        @NotBlank(message = "Email é obrigatório")
        String email
) {}
