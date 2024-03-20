package com.livecoding.estudos.controllers;

import com.livecoding.estudos.domain.usuarios.DTO.AuthenticationDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Usuario;
import com.livecoding.estudos.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://192.168.100.124:4200", "http://192.168.100.127:4200", "http://192.168.0.24:4200", "http://192.168.100.115:4200"})

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid AuthenticationDTO usuario) {
        var usernamepassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha());
        var auth = this.authenticationManager.authenticate(usernamepassword);

        var user = (Usuario) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("name", user.getName());

        // Obter todos os códigos de perfil
        Set<String> perfilCodigos = user.getPerfis().stream()
                .map(perfil -> perfil.getCodigo())
                .collect(Collectors.toSet());

        response.put("perfilCodigos", perfilCodigos);

        return ResponseEntity.ok(response);
    }


}
