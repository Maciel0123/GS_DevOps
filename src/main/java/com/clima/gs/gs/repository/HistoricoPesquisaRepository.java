package com.clima.gs.gs.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clima.gs.gs.model.HistoricoPesquisa;
import com.clima.gs.gs.model.User;

public interface HistoricoPesquisaRepository extends JpaRepository<HistoricoPesquisa, Long> {
    List<HistoricoPesquisa> findByUsuarioOrderByDataPesquisaDesc(User usuario);

    boolean existsByUsuarioAndCidadeIgnoreCase(User usuario, String cidade);

    void deleteByDataPesquisaBefore(LocalDateTime dataLimite);
}
