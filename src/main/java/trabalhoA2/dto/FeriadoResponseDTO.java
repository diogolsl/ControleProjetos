package trabalhoA2.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record FeriadoResponseDTO(
        @JsonAlias("date")
        String data,

        @JsonAlias("name")
        String nome,

        @JsonAlias("type")
        String tipo
) {}
