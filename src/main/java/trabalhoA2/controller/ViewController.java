package trabalhoA2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import trabalhoA2.model.Projeto;
import trabalhoA2.model.Responsavel;
import trabalhoA2.model.Tarefa;
import trabalhoA2.repository.ProjetoRepository;
import trabalhoA2.repository.ResponsavelRepository;
import trabalhoA2.repository.TarefaRepository;

@Controller
public class ViewController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String index(Model model) {
        carregarDadosIndex(model);
        return "index";
    }

    private void carregarDadosIndex(Model model) {
        model.addAttribute("projetos", projetoRepository.findAll());
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        model.addAttribute("tarefas", tarefaRepository.findAll());

        model.addAttribute("novoResponsavel", new Responsavel());

        Projeto projeto = new Projeto();
        projeto.setResponsavel(new Responsavel());
        model.addAttribute("novoProjeto", projeto);

        Tarefa tarefa = new Tarefa();
        tarefa.setProjeto(new Projeto());
        model.addAttribute("novaTarefa", tarefa);
    }

    // RESPONSAVEIS
    @PostMapping("/salvarResponsavelFront")
    public String salvarResponsavel(@ModelAttribute Responsavel responsavel, RedirectAttributes redirectAttributes) {
        try {
            responsavelRepository.save(responsavel);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Responsável salvo com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Já existe um responsável com esse email.");
        }

        return "redirect:/";
    }

    @GetMapping("/responsaveis/excluir/{idResponsavel}")
    public String deletarResponsavel(@PathVariable Long idResponsavel, RedirectAttributes redirectAttributes) {
        try {
            responsavelRepository.deleteById(idResponsavel);
            redirectAttributes.addFlashAttribute("mensagemExclusao", "Responsável excluído com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir este responsável pois ele já está atrelado a um ou mais projetos.");
        }

        return "redirect:/";
    }

    @GetMapping("/responsaveis/editar/{idResponsavel}")
    public String carregarTelaEdicaoResponsavel(@PathVariable Long idResponsavel, Model model) {
        Responsavel responsavel = responsavelRepository.findById(idResponsavel)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + idResponsavel));
        model.addAttribute("responsavel", responsavel);
        return "editar-responsavel";
    }

    // PROJETOS
    @PostMapping("/salvarProjetoFront")
    public String salvarProjeto(@ModelAttribute Projeto projeto, RedirectAttributes redirectAttributes) {
        try {
            projetoRepository.save(projeto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Projeto salvo com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível salvar o projeto. Confira o responsável selecionado.");
        }

        return "redirect:/";
    }

    @GetMapping("/projetos/excluir/{idProjeto}")
    public String deletarProjeto(@PathVariable Long idProjeto, RedirectAttributes redirectAttributes) {
        try {
            projetoRepository.deleteById(idProjeto);
            redirectAttributes.addFlashAttribute("mensagemExclusao", "Projeto excluído com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir este projeto pois ele já possui tarefas cadastradas.");
        }
        return "redirect:/";
    }

    @GetMapping("/projetos/editar/{idProjeto}")
    public String carregarTelaEdicaoProjeto(@PathVariable Long idProjeto, Model model) {
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + idProjeto));
        model.addAttribute("projeto", projeto);

        // dropdown responsavel
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        return "editar-projeto";
    }

    // TAREFAS
    @PostMapping("/salvarTarefaFront")
    public String salvarTarefa(@ModelAttribute Tarefa tarefa, RedirectAttributes redirectAttributes) {
        try {
            tarefaRepository.save(tarefa);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa salva com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível salvar a tarefa. Confira o projeto selecionado.");
        }

        return "redirect:/";
    }

    @GetMapping("/tarefas/excluir/{idTarefa}")
    public String excluirTarefa(@PathVariable Long idTarefa, RedirectAttributes redirectAttributes) {
        try {
            tarefaRepository.deleteById(idTarefa);
            redirectAttributes.addFlashAttribute("mensagemExclusao", "Tarefa excluída com sucesso!");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Não foi possível excluir esta tarefa.");
        }

        return "redirect:/";
    }

    @GetMapping("/tarefas/editar/{idTarefa}")
    public String carregarTelaEdicaoTarefa(@PathVariable Long idTarefa, Model model) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + idTarefa));
        model.addAttribute("tarefa", tarefa);

        // dropdown projetos
        model.addAttribute("projetos", projetoRepository.findAll());
        return "editar-tarefa";
    }
}
