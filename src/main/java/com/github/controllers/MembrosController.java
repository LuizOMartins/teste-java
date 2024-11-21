package com.github.controllers;

import com.github.models.Membros;
import com.github.models.Pessoa;
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
        Pessoa pessoa = pessoaService.salvar(membro.getPessoa());
        membro.setPessoa(pessoa);
        membrosService.salvar(membro);
        return "redirect:/gerenciamentoMembros";
    }

}
