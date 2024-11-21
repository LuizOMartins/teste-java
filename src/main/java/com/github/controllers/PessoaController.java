package com.github.controllers;

import com.github.models.Pessoa;
import com.github.service.PessoaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        model.addAttribute("pessoas", pessoas);
        return "listarPessoas";
    }

    @GetMapping("/formulario")
    public String formularioPessoa(@RequestParam(required = false) Long id, Model model) {
        Pessoa pessoa = (id != null) ? pessoaService.buscarPorId(id) : new Pessoa();
        model.addAttribute("pessoa", pessoa);
        return "formularioPessoa";
    }

    @PostMapping("/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        pessoaService.salvar(pessoa);
        return "redirect:/gerenciamentoPessoa";
    }


    @GetMapping("/deletar")
    public String deletarPessoa(@RequestParam Long id) {
        pessoaService.deletar(id);
        return "redirect:/gerenciamentoPessoa";
    }
}
