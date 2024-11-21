package com.github.controllers;

import com.github.models.Membros;
import com.github.models.Pessoa;
import com.github.models.Projeto;
import com.github.service.MembrosService;
import com.github.service.PessoaService;
import com.github.service.ProjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class HomeController {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;
    private final MembrosService membrosService;


    public HomeController(ProjetoService projetoService,PessoaService pessoaService, MembrosService membrosService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
        this.membrosService = membrosService;
    }

    @GetMapping({"/", "/gerenciamentoProjetos"})
    public String home(Model model) {
        List<Projeto> projetos = projetoService.listarTodos();
        model.addAttribute("projetos", projetos);
        return "gerenciamentoProjetos";
    }

    @GetMapping("/gerenciamentoMembros")
    public String gerenciamentoMembros(Model model)
    {
        List<Membros> membros = membrosService.listarTodos();
        model.addAttribute("membros", membros);

        return "gerenciamentoMembros";
    }

    @GetMapping("/formularioMembro")
    public String formularioMembro(@RequestParam(required = false) Long id, Model model) {
        List<Projeto> projetos = projetoService.listarTodosProjetos();
        List<Pessoa> pessoas = pessoaService.listarTodos();
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("projetos", projetos);
        Membros membro = (id != null) ? membrosService.buscarPorId(id) : new Membros();
        model.addAttribute("membro", membro);
        return "formularioMembro";
    }

    @GetMapping("/gerenciamentoPessoa")
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        System.out.println("Pessoas carregadas: " + pessoas); // Adicione este log para depurar
        model.addAttribute("pessoas", pessoas);
        return "gerenciamentoPessoa";
    }



    @GetMapping("/formularioPessoa")
    public String formularioPessoa(@RequestParam(required = false) Long id, Model model) {
        Pessoa pessoa = (id != null) ? pessoaService.buscarPorId(id) : new Pessoa();
        model.addAttribute("pessoa", pessoa);
        return "formularioPessoa";
    }

    @GetMapping("/formularioProjetos")
    public String formularioProjetos(@RequestParam(required = false) Long id, Model model) {
        Projeto projeto = (id != null) ? projetoService.buscarPorId(id) : new Projeto();

        List<Pessoa> gerentes = pessoaService.listarGerentes();

        model.addAttribute("projeto", projeto);
        model.addAttribute("gerentes", gerentes);
        return "formularioProjetos";
    }
}
