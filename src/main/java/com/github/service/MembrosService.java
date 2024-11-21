package com.github.service;

import com.github.models.Membros;
import com.github.models.Pessoa;
import com.github.models.Projeto;
import com.github.repository.MembrosRepository;
import com.github.repository.PessoaRepository;
import com.github.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembrosService {

    private final MembrosRepository membrosRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public MembrosService(MembrosRepository membrosRepository) {
        this.membrosRepository = membrosRepository;
    }

    public Membros buscarPorId(Long id) {
        return membrosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membro não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        membrosRepository.deleteById(id);
    }

    public void salvar(Membros membro) {
        Projeto projeto = projetoRepository.findById(membro.getProjeto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        Pessoa pessoa = pessoaRepository.findById(membro.getPessoa().getId())
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));

        membro.setProjeto(projeto);
        membro.setPessoa(pessoa);

        membrosRepository.save(membro);
    }


    public List<Membros> listarTodos() {
        return membrosRepository.findAll();
    }
}
