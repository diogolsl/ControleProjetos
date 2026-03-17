package trabalhoA2.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Object lidarComErroDeIntegridade(DataIntegrityViolationException ex, HttpServletRequest request, Model model) {
        String url = request.getRequestURI();
        String mensagemAmigavel = "Ação bloqueada: Não é possível excluir este registro porque ele já está vinculado a outro item no sistema.";

        if (url.startsWith("/api") || url.startsWith("/v3/api-docs") || url.startsWith("/swagger-ui/")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("erro", mensagemAmigavel));
        }

        model.addAttribute("erro", mensagemAmigavel);
        return "erro";
    }

    @ExceptionHandler(Exception.class)
    public String lidarComErrosGerais(Exception ex, HttpServletRequest request, Model model) throws Exception {
        String url = request.getRequestURI();

        if (url.startsWith("/api") || url.startsWith("/v3/api-docs") || url.startsWith("/swagger-ui/")) {
            throw ex;
        }

        model.addAttribute("mensagemErro", "Ocorreu um erro inesperado no sistema. Tente reiniciar a página.");
        return "erro";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> lidarComErrosDeValidacao(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}
