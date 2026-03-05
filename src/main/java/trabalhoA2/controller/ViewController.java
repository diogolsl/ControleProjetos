package trabalhoA2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/salvarResponsavelFront")
    public String salvarResponsavel(@ModelAttribute Responsavel responsavel) {
        responsavelRepository.save(responsavel);
        return "redirect:/";
    }

    @PostMapping("/salvarProjetoFront")
    public String salvarProjeto(@ModelAttribute Projeto projeto) {

        // utilizando essa estrutura para tentar resolver problema status 500 no swagger
        Long idDoResponsavel = projeto.getResponsavel().getIdResponsavel();
        Responsavel responsavelCompleto = responsavelRepository.findById(idDoResponsavel)
                .orElseThrow(() -> new IllegalArgumentException("Responsável inválido"));
        projeto.setResponsavel(responsavelCompleto);

        projetoRepository.save(projeto);
        return "redirect:/";
    }

    @PostMapping("/salvarTarefaFront")
    public String salvarTarefa(@ModelAttribute Tarefa tarefa) {

        // utilizando essa estrutura para tentar resolver problema status 500 no swagger
        Long idDoProjeto = tarefa.getProjeto().getIdProjeto();
        Projeto projetoCompleto = projetoRepository.findById(idDoProjeto)
                .orElseThrow(() -> new IllegalArgumentException("Projeto inválido"));
        tarefa.setProjeto(projetoCompleto);

        tarefaRepository.save(tarefa);
        return "redirect:/";
    }
}
