package com.clima.gs.gs.service;

import com.clima.gs.gs.repository.HistoricoPesquisaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoricoCleanupService {

    @Autowired
    private HistoricoPesquisaRepository historicoRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void limparHistoricoAntigo() {
        LocalDateTime limite = LocalDateTime.now().minusDays(7);
        historicoRepository.deleteByDataPesquisaBefore(limite);
        System.out.println("Histórico antigo removido com sucesso.");
    }
}
