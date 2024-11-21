package com.github.service;

import com.github.models.Pessoa;
import com.github.repository.PessoaRepository;
import org.springframework.stereotype.Service;


@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
