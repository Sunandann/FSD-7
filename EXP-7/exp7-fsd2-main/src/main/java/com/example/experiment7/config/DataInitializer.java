package com.example.experiment7.config;

import com.example.experiment7.entity.Role;
import com.example.experiment7.entity.User;
import com.example.experiment7.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        User user = new User();
        user.setUsername("user1");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRole(Role.ROLE_USER);

        User admin = new User();
        admin.setUsername("admin1");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ROLE_ADMIN);

        userRepository.saveAll(List.of(user, admin));
    }
}
