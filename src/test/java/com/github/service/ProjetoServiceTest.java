package com.github.service;
import com.github.models.Projeto;
import com.github.repository.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorId() {
        Projeto projetoMock = new Projeto();
        projetoMock.setId(1L);
        projetoMock.setNome("Projeto Teste");

        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projetoMock));

        Optional<Projeto> resultado = projetoRepository.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Projeto Teste", resultado.get().getNome());
        verify(projetoRepository, times(1)).findById(1L);
    }
}
