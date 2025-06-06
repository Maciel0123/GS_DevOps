package com.clima.gs.gs.dto;

public record HistoricoDTO(
        Long idUsuario,
        String cidade,
        Double latApi,
        Double lonApi) {
}
