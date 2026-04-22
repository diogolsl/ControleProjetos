package trabalhoA2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import trabalhoA2.dto.FeriadoResponseDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class FeriadoServiceTest {

    private FeriadoService feriadoService;
    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        this.mockServer = MockRestServiceServer.createServer(restTemplate);
        this.feriadoService = new FeriadoService(restTemplate);
    }

    @Test
    public void deveBuscarFeriadosComSucesso() {
        String ano = "2026";
        String urlEsperada = "https://brasilapi.com.br/api/feriados/v1/" + ano;
        String jsonResposta = """
            [
              {
                "date": "2026-01-01",
                "name": "Confraternização Universal",
                "type": "national"
              },
              {
                "date": "2026-04-21",
                "name": "Tiradentes",
                "type": "national"
              }
            ]
            """;

        mockServer.expect(requestTo(urlEsperada))
                .andRespond(withSuccess(jsonResposta, MediaType.APPLICATION_JSON));

        List<FeriadoResponseDTO> resultado = feriadoService.listarFeriadosPorAno(ano);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("2026-01-01", resultado.get(0).data());
        assertEquals("Confraternização Universal", resultado.get(0).nome());

        mockServer.verify();
    }
}
