package com.clima.gs.gs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clima.gs.gs.dto.AuthResponseDTO;
import com.clima.gs.gs.model.Token;
import com.clima.gs.gs.model.User;
import com.clima.gs.gs.service.AuthService;
import com.clima.gs.gs.service.TokenService;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody Credentials credentials) {
        var userDetails = authService.loadUserByUsername(credentials.email());

        if (!passwordEncoder.matches(credentials.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        var user = (User) userDetails;

        String jwt = tokenService.generateJwt(user);

        return new AuthResponseDTO(
                jwt,
                "Bearer",
                user.getIdUser(),
                user.getNomeUser(),
                user.getEmail());
    }

}