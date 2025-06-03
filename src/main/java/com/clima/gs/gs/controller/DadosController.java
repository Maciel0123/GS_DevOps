package com.clima.gs.gs.controller;

import com.clima.gs.gs.model.Dados;
import com.clima.gs.gs.model.HistoricoPesquisa;
import com.clima.gs.gs.model.User;
import com.clima.gs.gs.repository.DadosRepository;
import com.clima.gs.gs.repository.HistoricoPesquisaRepository;
import com.clima.gs.gs.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dados")
public class DadosController {

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoricoPesquisaRepository historicoRepository;

    @GetMapping
    public List<Dados> listarComFiltros(
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) Long idUsuario) {

        Dados filtro = new Dados();

        if (cidade != null && !cidade.isBlank()) {
            filtro.setCidade(cidade.trim());

            if (idUsuario != null) {
                User usuario = userRepository.findById(idUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                boolean jaPesquisada = historicoRepository.existsByUsuarioAndCidadeIgnoreCase(usuario, cidade);

                if (!jaPesquisada && cidade.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                    // Busca lat/lon mockado da cidade
                    var dadosMock = dadosRepository
                            .findFirstByCidadeIgnoreCaseOrderByDataColetaDesc(cidade.trim())
                            .orElse(null);

                    HistoricoPesquisa historico = HistoricoPesquisa.builder()
                            .usuario(usuario)
                            .cidade(cidade.trim())
                            .dataPesquisa(LocalDateTime.now())
                            .latApi(dadosMock != null ? dadosMock.getLatApi() : null)
                            .lonApi(dadosMock != null ? dadosMock.getLonApi() : null)

                            .build();

                    historicoRepository.save(historico);

                }
            }
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Dados> example = Example.of(filtro, matcher);

        return dadosRepository.findAll(example);
    }

}
