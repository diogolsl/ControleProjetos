package trabalhoA2.dto;
import java.util.Date;

public record ProjetoRequestDTO(
        String nomeProjeto,
        Date dataInicio,
        Long idResponsavel
) {}
