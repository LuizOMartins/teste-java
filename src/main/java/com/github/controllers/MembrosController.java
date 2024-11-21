package com.github.controllers;

import com.github.models.Membros;
import com.github.models.Pessoa;
import com.github.models.Projeto;
import com.github.service.MembrosService;
import com.github.service.PessoaService;
import com.github.service.ProjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/membros")
public class MembrosController {

    private final MembrosService membrosService;
    private final PessoaService pessoaService;
    private final ProjetoService projetoService;

    public MembrosController(MembrosService membrosService, PessoaService pessoaService, ProjetoService projetoService) {
        this.membrosService = membrosService;
        this.pessoaService = pessoaService;
        this.projetoService = projetoService;
    }

    @GetMapping
    public String listarMembros(Model model) {
        List<Membros> membros = membrosService.listarTodos();
        model.addAttribute("membros", membros);
        return "gerenciamentoMembros";
    }

    @GetMapping("/novo")
    public String novoMembro(Model model) {
        model.addAttribute("membro", new Membros());
        model.addAttribute("projetos", projetoService.listarTodos());
        return "gerenciamentoMembros";
    }

    @PostMapping("/salvar")
    public String salvarMembro(@ModelAttribute Membros membro) {
        System.out.println("Membro recebido:");
        System.out.println("ID: " + membro.getId());
        System.out.println("Projeto ID: " + (membro.getProjeto() != null ? membro.getProjeto().getId() : "null"));
        System.out.println("Pessoa ID: " + (membro.getPessoa() != null ? membro.getPessoa().getId() : "null"));
        System.out.println("Cargo: " + membro.getCargo());
        System.out.println("Data de Associação: " + membro.getDataAssociacao());


        if (membro.getProjeto() == null || membro.getProjeto().getId() == null) {
            throw new IllegalArgumentException("Projeto não informado!");
        }
        if (membro.getPessoa() == null || membro.getPessoa().getId() == null) {
            throw new IllegalArgumentException("Pessoa não informada!");
        }

        Pessoa pessoa = pessoaService.buscarPorId(membro.getPessoa().getId());
        Projeto projeto = projetoService.buscarPorId(membro.getProjeto().getId());

        membro.setPessoa(pessoa);
        membro.setProjeto(projeto);

        membrosService.salvar(membro);
        return "redirect:/gerenciamentoMembros";
    }


    @GetMapping("/formularioMembro")
    public String formularioMembro(@RequestParam(required = false) Long id, Model model) {
        List<Projeto> projetos = projetoService.listarTodosProjetos();
        List<Pessoa> pessoas = pessoaService.listarTodos();

        Membros membro = id != null ? membrosService.buscarPorId(id) : new Membros();

        model.addAttribute("projetos", projetos);
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("membro", membro);

        return "formularioMembro";
    }

}
