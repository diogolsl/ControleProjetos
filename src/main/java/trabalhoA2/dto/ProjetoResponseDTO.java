package trabalhoA2.dto;

import java.util.Date;

public record ProjetoResponseDTO(
        Long idProjeto,
        String nomeProjeto,
        Date dataInicio,
        String nomeResponsavel
) {}
