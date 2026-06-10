package trabalhoA2.dto;

import java.time.LocalDate;

public record ProjetoResponseDTO(
        Long idProjeto,
        String nomeProjeto,
        LocalDate dataInicio,
        String nomeResponsavel
) {}
