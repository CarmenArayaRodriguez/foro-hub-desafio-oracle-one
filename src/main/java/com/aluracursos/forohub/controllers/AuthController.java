package com.aluracursos.forohub.controllers;

import com.aluracursos.forohub.dto.LoginRequest;
import com.aluracursos.forohub.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            // Autenticación exitosa, generar el token JWT
            String token = tokenService.generateToken(authentication.getName());
            return token; // Devolver el token JWT
        } catch (AuthenticationException e) {
            // Manejar autenticación fallida
            return "Authentication failed";
        }
    }
}
