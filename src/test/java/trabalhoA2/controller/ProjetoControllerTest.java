package trabalhoA2.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import trabalhoA2.service.ProjetoService;

@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjetoService projetoService;

    @Test
    public void deveRetornarBadRequestQuandoProjetoRequestDTOForInvalido() throws Exception {
        String payload = objectMapper.writeValueAsString(
                new Object() {
                    public final String dataInicio = "2026-06-01";
                    public final Long idResponsavel = 1L;
                }
        );

        mockMvc.perform(post("/api/Projeto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nomeProjeto").value("O nome do projeto não pode estar vazio"));
    }
}
