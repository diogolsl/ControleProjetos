package trabalhoA2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("projetos", projetoRepository.findAll());
        model.addAttribute("responsaveis", responsavelRepository.findAll());
        model.addAttribute("tarefas", tarefaRepository.findAll());

        model.addAttribute("novoResponsavel", new Responsavel());

        Projeto projeto = new Projeto();
        projeto.setResponsavel(new Responsavel());
        model.addAttribute("novoProjeto", new Projeto());

        Tarefa tarefa = new Tarefa();
        tarefa.setProjeto(new Projeto());
        model.addAttribute("novaTarefa", new Tarefa());

        return "index";
    }

    // RESPONSAVEIS
    @PostMapping("/salvarResponsavelFront")
    public String salvarResponsavel(@ModelAttribute Responsavel responsavel) {
        responsavelRepository.save(responsavel);
        return "redirect:/";
    }

    @GetMapping("/responsaveis/excluir/{idResponsavel}")
    public String deletarResponsavel(@PathVariable Long idResponsavel) {
        responsavelRepository.deleteById(idResponsavel);
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
    public String salvarProjeto(@ModelAttribute Projeto projeto) {
        projetoRepository.save(projeto);
        return "redirect:/";
    }

    @GetMapping("/projetos/excluir/{idProjeto}")
    public String deletarProjeto(@PathVariable Long idProjeto) {
        projetoRepository.deleteById(idProjeto);
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
    public String salvarTarefa(@ModelAttribute Tarefa tarefa) {
        tarefaRepository.save(tarefa);
        return "redirect:/";
    }

    @GetMapping("/tarefas/excluir/{idTarefa}")
    public String excluirTarefa(@PathVariable Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);
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
