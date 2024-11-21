package com.github.controllers;

import com.github.models.Membros;
import com.github.models.Pessoa;
import com.github.models.Projeto;
import com.github.service.MembrosService;
import com.github.service.PessoaService;
import com.github.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        System.out.println("Salvando Membro:");
        System.out.println("ID: " + membro.getId());
        System.out.println("Projeto ID: " + (membro.getProjeto() != null ? membro.getProjeto().getId() : "null"));
        System.out.println("Pessoa ID: " + (membro.getPessoa() != null ? membro.getPessoa().getId() : "null"));
        System.out.println("Cargo: " + membro.getCargo());
        System.out.println("Data de Associação: " + membro.getDataAssociacao());

        if (membro.getId() == null) {
            membro.setDataAssociacao(LocalDateTime.now());
        }

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

    @PostMapping("/desvincular")
    public String desvincularMembro(@RequestParam Long id) {
        try {
            System.out.println("Desvinculando membro com ID: " + id);
            membrosService.desvincularMembro(id);
            return "redirect:/gerenciamentoMembros";
        } catch (Exception e) {
            System.err.println("Erro ao desvincular membro: " + e.getMessage());
            return "redirect:/gerenciamentoMembros";
        }
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

    @GetMapping("/excluir")
    public String excluirMembro(@RequestParam("id") Long id) {
        membrosService.excluir(id);
        return "redirect:/gerenciamentoMembros";
    }

    @PostMapping("/cadastro-publico")
    @ResponseBody
    public ResponseEntity<String> cadastrarMembroPublico(
            @RequestParam Long projetoId,
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String dataNascimento,
            @RequestParam String cargo) {
        try {
            
            Projeto projeto = projetoService.buscarPorId(projetoId);
            if (projeto == null) {
                return ResponseEntity.badRequest().body("Projeto não encontrado para o ID: " + projetoId);
            }

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setDataNascimento(LocalDate.parse(dataNascimento)); // Converte String para LocalDate
            pessoa = pessoaService.salvar(pessoa);
            Membros membro = new Membros();
            membro.setPessoa(pessoa);
            membro.setProjeto(projeto);
            membro.setCargo(cargo);
            membro.setDataAssociacao(LocalDateTime.now());
            membrosService.salvar(membro);

            return ResponseEntity.ok("Membro cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar membro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar membro.");
        }
    }





}
