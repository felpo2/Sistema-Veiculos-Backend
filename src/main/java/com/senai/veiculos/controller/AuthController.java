package com.senai.veiculos.controller;

import com.senai.veiculos.model.Usuario;
import com.senai.veiculos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            return ResponseEntity.ok("Usuário cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.autenticar(request.getEmail(), request.getSenha());
        if (usuario != null) {
            return ResponseEntity.ok(new LoginResponse(usuario.getEmail(), usuario.getRole()));
        }
        return ResponseEntity.badRequest().body("Email ou senha inválidos");
    }
}

class LoginRequest {
    private String email;
    private String senha;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}

class LoginResponse {
    private String email;
    private String role;
    public LoginResponse(String email, String role) {
        this.email = email;
        this.role = role;
    }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}