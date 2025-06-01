package com.clima.gs.gs.controller;

import com.clima.gs.gs.model.HistoricoPesquisa;
import com.clima.gs.gs.model.User;
import com.clima.gs.gs.repository.HistoricoPesquisaRepository;
import com.clima.gs.gs.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoPesquisaController {

    @Autowired
    private HistoricoPesquisaRepository historicoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idUsuario}")
    public List<HistoricoPesquisa> listarHistorico(@PathVariable Long idUsuario) {
        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return historicoRepository.findByUsuarioOrderByDataPesquisaDesc(usuario);
    }
}
