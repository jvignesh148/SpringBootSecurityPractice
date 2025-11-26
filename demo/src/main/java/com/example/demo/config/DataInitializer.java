package com.example.demo.config;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                User user = new User();
                user.setName("John doe");
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("password123"));
                user.setRole("ROLE_ADMIN");
                userRepository.save(user);
                System.out.println("Default user created: admin / password123");
            }
        };
    }
}
