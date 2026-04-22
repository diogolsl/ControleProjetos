package trabalhoA2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import trabalhoA2.dto.FeriadoResponseDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FeriadoService {

    private final RestTemplate restTemplate;
    private static final String BRASIL_API_URL = "https://brasilapi.com.br/api/feriados/v1/";

    public FeriadoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FeriadoResponseDTO> listarFeriadosPorAno(String ano) {
        String url = BRASIL_API_URL + ano;
        try {
            FeriadoResponseDTO[] feriadosArray = restTemplate.getForObject(url, FeriadoResponseDTO[].class);
            if (feriadosArray != null) {
                return Arrays.asList(feriadosArray);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar feriados: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}