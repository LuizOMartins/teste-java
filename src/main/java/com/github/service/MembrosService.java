package com.github.service;

import com.github.models.Membros;
import com.github.repository.MembrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembrosService {

    private final MembrosRepository membrosRepository;

    public MembrosService(MembrosRepository membrosRepository) {
        this.membrosRepository = membrosRepository;
    }

    public Membros buscarPorId(Long id) {
        Optional<Membros> membro = membrosRepository.findById(id);
        return membro.orElse(null);
    }

    public void deletar(Long id) {
        membrosRepository.deleteById(id);
    }

    public Membros salvar(Membros membro) {
        return membrosRepository.save(membro);
    }

    public List<Membros> listarTodos() {
        return membrosRepository.findAll();
    }
}
