package com.github.controllers;

import com.github.models.Projeto;
import com.github.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
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
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(projeto);
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.salvar(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
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
