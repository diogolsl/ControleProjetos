package trabalhoA2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalhoA2.dto.FeriadoResponseDTO;
import trabalhoA2.service.FeriadoService;

import java.util.List;

@RestController
@RequestMapping("/api/feriados")
public class FeriadoController {

    private final FeriadoService feriadoService;

    public FeriadoController(FeriadoService feriadoService) {
        this.feriadoService = feriadoService;
    }

    @GetMapping("/{ano}")
    public List<FeriadoResponseDTO> buscarFeriados(@PathVariable String ano) {
        return feriadoService.listarFeriadosPorAno(ano);
    }
}
