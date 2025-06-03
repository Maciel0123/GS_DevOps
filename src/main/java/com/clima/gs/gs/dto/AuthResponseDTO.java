package com.clima.gs.gs.dto;

public record AuthResponseDTO(
        String token,
        String type,
        Long id,
        String nome,
        String email) {
}
