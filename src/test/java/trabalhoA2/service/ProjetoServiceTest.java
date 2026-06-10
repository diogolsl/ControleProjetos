package trabalhoA2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import trabalhoA2.dto.ProjetoRequestDTO;
import trabalhoA2.dto.ProjetoResponseDTO;
import trabalhoA2.model.Projeto;
import trabalhoA2.model.Responsavel;
import trabalhoA2.repository.ProjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @Test
    public void deveSalvarProjetoComSucesso() {
        ProjetoRequestDTO dtoMock = mock(ProjetoRequestDTO.class);
        when(dtoMock.nomeProjeto()).thenReturn("Ação Social Comunitária");
        when(dtoMock.dataInicio()).thenReturn(LocalDate.now());
        when(dtoMock.idResponsavel()).thenReturn(1L);

        Projeto projetoSalvo = new Projeto();
        projetoSalvo.setIdProjeto(1L);
        projetoSalvo.setNomeProjeto("Ação Social Comunitária");

        when(projetoRepository.save(any(Projeto.class))).thenReturn(projetoSalvo);

        Projeto resultado = projetoService.salvar(dtoMock);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProjeto());
        assertEquals("Ação Social Comunitária", resultado.getNomeProjeto());

        verify(projetoRepository, times(1)).save(any(Projeto.class));
    }

    @Test
    public void deveLancarExcecaoAoTentarSalvarProjetoNulo() {
        ProjetoRequestDTO dtoInvalido = null;
        assertThrows(NullPointerException.class, () -> projetoService.salvar(dtoInvalido));
    }

    @Test
    public void deveRetornarNullAoAtualizarProjetoInexistente() {
        Long idInexistente = 999L;
        ProjetoRequestDTO dtoMock = mock(ProjetoRequestDTO.class);

        when(projetoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        Projeto resultado = projetoService.atualizar(idInexistente, dtoMock);

        assertNull(resultado);
        verify(projetoRepository, never()).save(any(Projeto.class));
    }
}
