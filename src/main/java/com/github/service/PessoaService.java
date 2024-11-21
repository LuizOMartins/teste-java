package com.github.service;

import com.github.models.Pessoa;
import com.github.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarGerentes() {
        return pessoaRepository.findByGerenteTrue();
    }

    public Pessoa buscarPorId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        return pessoaOptional.orElse(null);
    }


    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public void deletar(Long id) {
        pessoaRepository.deleteById(id);
    }

}
