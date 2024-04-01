package com.livecoding.estudos.controllers;

import com.livecoding.estudos.domain.usuarios.DTO.AuthenticationDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Usuario;
import com.livecoding.estudos.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO usuario) {
        try {
            var usernamepassword = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha());
            var auth = this.authenticationManager.authenticate(usernamepassword);

            var user = (Usuario) auth.getPrincipal();
            var token = tokenService.generateToken(user);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("name", user.getName());

            // Obter todos os códigos de perfil
            String perfilCodigos = user.getPerfis().getCodigo();
            response.put("perfilCodigos", perfilCodigos);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // Tratar a exceção de credenciais inválidas aqui
            return ResponseEntity.badRequest().body("Verifique suas credencias!");
        } catch (AuthenticationException e) {
            // Tratar outras falhas de autenticação aqui
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }


}
