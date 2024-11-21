package com.github.service;

import com.github.models.Projeto;
import com.github.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> listarComFiltros(String nome, String status, Date dataInicio) {
        if (nome != null && status != null && dataInicio != null) {
            return projetoRepository.findByNomeAndStatusAndDataInicio(nome, status, dataInicio);
        } else if (nome != null && status != null) {
            return projetoRepository.findByNomeAndStatus(nome, status);
        } else if (nome != null) {
            return projetoRepository.findByNome(nome);
        } else {
            return projetoRepository.findAll();
        }
    }

    public Projeto buscarPorId(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.orElse(null);
    }

    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void deletar(Long id) {
        projetoRepository.deleteById(id);
    }

    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAll();
    }

    public void removerProjeto(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isPresent()) {
            projetoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto com ID " + id + " não encontrado.");
        }
    }



}
