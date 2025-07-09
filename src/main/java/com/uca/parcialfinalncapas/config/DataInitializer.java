package com.uca.parcialfinalncapas.config;

import com.uca.parcialfinalncapas.entities.User;
import com.uca.parcialfinalncapas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            // Usuario normal (USER)
            if (repo.findByCorreo("user@ejemplo.com").isEmpty()) {
                repo.save(User.builder()
                        .nombre("Usuario Cliente")
                        .correo("user@ejemplo.com")       // ahora coincide con el login
                        .password(encoder.encode("userpass"))
                        .nombreRol("USER")
                        .build());
            }
            // Técnico (TECH)
            if (repo.findByCorreo("tech@ejemplo.com").isEmpty()) {
                repo.save(User.builder()
                        .nombre("Técnico Soporte")
                        .correo("tech@ejemplo.com")       // ahora coincide con el login
                        .password(encoder.encode("techpass"))
                        .nombreRol("TECH")
                        .build());
            }
        };
    }
}
