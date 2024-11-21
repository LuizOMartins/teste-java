package com.github.controllers;

import com.github.models.Projeto;
import com.github.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProjetoControllerTest {

    @Mock
    private ProjetoService projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRemoverProjetoComSucesso() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus("Planejado");

        when(projetoService.buscarPorId(1L)).thenReturn(projeto);
        
        ResponseEntity<Void> response = projetoController.removerProjeto(1L);
        verify(projetoService, times(1)).removerProjeto(1L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testRemoverProjetoNaoEncontrado() {
        when(projetoService.buscarPorId(1L)).thenReturn(null);
        ResponseEntity<Void> response = projetoController.removerProjeto(1L);
        verify(projetoService, never()).removerProjeto(anyLong());
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testRemoverProjetoComStatusRestrito() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setStatus("Iniciado");

        when(projetoService.buscarPorId(1L)).thenReturn(projeto);
        ResponseEntity<Void> response = projetoController.removerProjeto(1L);
        verify(projetoService, never()).removerProjeto(anyLong());
        assertEquals(400, response.getStatusCodeValue());
    }
}
