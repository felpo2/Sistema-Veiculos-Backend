package com.senai.veiculos.config;

import com.senai.veiculos.model.Usuario;
import com.senai.veiculos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfig {

    @Bean
    public CommandLineRunner criarAdminPadrao(UsuarioRepository repository) {
        return args -> {
            // Verifica se o admin já existe no banco de dados
            if (repository.findByEmail("admin@senai.com").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail("admin@senai.com");
                admin.setSenha("admin123");
                admin.setRole("ADMIN");

                repository.save(admin);
                System.out.println("Administrador padrão criado com sucesso");
            }
        };
    }
}