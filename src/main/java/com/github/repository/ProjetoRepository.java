package com.github.repository;

import com.github.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p WHERE " +
            "(:nome IS NULL OR p.nome LIKE %:nome%) AND " +
            "(:status IS NULL OR p.status = :status) AND " +
            "(:dataInicio IS NULL OR p.dataInicio >= :dataInicio)")
    List<Projeto> findByFiltros(@Param("nome") String nome,
                                @Param("status") String status,
                                @Param("dataInicio") Date dataInicio);


    List<Projeto> findByNome(String nome);


    List<Projeto> findByNomeAndStatus(String nome, String status);


    List<Projeto> findByNomeAndStatusAndDataInicio(String nome, String status, Date dataInicio);
}
