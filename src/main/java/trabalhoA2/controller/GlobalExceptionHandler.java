package trabalhoA2.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(DataIntegrityViolationException.class)
    public String lidarComErroDeIntegridade(DataIntegrityViolationException ex, HttpServletRequest request, Model model) throws Exception {
        String mensagem = "Não foi possível excluir o registro porque ele está sendo usado em outro lugar do sistema";
        String detalhes = ex.getMessage().toLowerCase();
        String url = request.getRequestURI();

        if (url.startsWith("/api") || url.startsWith("/v3/api-docs") || url.startsWith("/swagger-ui/")) {
            throw ex;
        }

        if (detalhes.contains("tarefa")) {
        mensagem = "Ação bloqueada! Antes de apagar este Projeto, certifique-se de excluir as TAREFAS vinculadas a ele.";
        }
        else if (detalhes.contains("projeto")) {
        mensagem = "Ação bloqueada! Antes de apagar um Responsável, certifique-se de excluir ou alterar os PROJETOS vinculados a ele.";
        }

        model.addAttribute("mensagemErro", mensagem);

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

}
