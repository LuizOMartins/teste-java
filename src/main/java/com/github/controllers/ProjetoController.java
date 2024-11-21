package com.github.controllers;

import com.github.dtos.ProjetoDTO;
import com.github.models.Pessoa;
import com.github.models.Projeto;
import com.github.service.PessoaService;
import com.github.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;

    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Date dataInicio) {
        List<Projeto> projetos = projetoService.listarComFiltros(nome, status, dataInicio);
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        Projeto projeto = projetoService.buscarPorId(id);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projeto);
    }

    @PostMapping("/salvar")
    public String criarProjeto(@RequestBody ProjetoDTO projetoDTO) {
        Projeto projeto;

        if (projetoDTO.getId() != null) {
            projeto = projetoService.buscarPorId(projetoDTO.getId());
            if (projeto == null) {
                throw new RuntimeException("Projeto não encontrado para o ID: " + projetoDTO.getId());
            }
        } else {
            projeto = new Projeto();
        }

        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setStatus(projetoDTO.getStatus());
        projeto.setRisco(projetoDTO.getRisco());
        projeto.setOrcamento(projetoDTO.getOrcamento());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataPrevisaoFim(projetoDTO.getDataPrevisaoFim());
        projeto.setDataFim(projetoDTO.getDataFim());

        Pessoa gerente = pessoaService.buscarPorId(projetoDTO.getGerenteId());
        if (gerente == null) {
            throw new RuntimeException("Gerente não encontrado para o ID: " + projetoDTO.getGerenteId());
        }
        projeto.setGerente(gerente);
        projetoService.salvar(projeto);
        return "redirect:/gerenciamentoProjetos";
    }


    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) {
        Projeto projetoExistente = projetoService.buscarPorId(id);
        if (projetoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        projetoAtualizado.setId(id);
        Projeto projetoSalvo = projetoService.salvar(projetoAtualizado);
        return ResponseEntity.ok(projetoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        Projeto projeto = projetoService.buscarPorId(id);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
