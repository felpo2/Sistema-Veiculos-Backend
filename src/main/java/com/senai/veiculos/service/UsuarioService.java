package com.senai.veiculos.service;

import com.senai.veiculos.model.Usuario;
import com.senai.veiculos.model.Veiculo;
import com.senai.veiculos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario autenticar(String email, String senha) {
        return usuarioRepository.findByEmail(email).filter(u -> u.getSenha().equals(senha)).orElse(null);
    }


    public Usuario cadastrar(Usuario usuario) {

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        usuario.setRole("USER");
        return usuarioRepository.save(usuario);
    }
}
