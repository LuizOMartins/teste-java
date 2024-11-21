package com.github.controllers;

import com.github.models.Pessoa;
import com.github.models.Projeto;
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


    public HomeController(ProjetoService projetoService,PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping({"/", "/gerenciamentoProjetos"})
    public String home() {
        return "gerenciamentoProjetos";
    }

    @GetMapping("/gerenciamentoMembros")
    public String gerenciamentoMembros() {
        return "gerenciamentoMembros";
    }

    @GetMapping("/formularioMembro")
    public String formularioMembro() {
        return "formularioMembro";

    }


    @GetMapping("/gerenciamentoPessoa")
    public String gerenciamentoPessoa() {
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
