package com.sum.Security.auth;

import com.sum.Security.config.JwtService;
import com.sum.Security.user.Client;
import com.sum.Security.user.Dietitian;
import com.sum.Security.user.Trainer;
import com.sum.Security.user.User;
import com.sum.Security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {
        User user;
        switch (request.getRole()) {
            case CLIENT:
                user = new Client();
                break;
            case DIETITIAN:
                user = new Dietitian();
                break;
            case TRAINER:
                user = new Trainer();
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authanticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void forgotPassword(String email) {
        repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with this email does not exist"));
    }

    public void resetPassword(String email, String newPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with this email does not exist."));
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    public void updatePassword(String email, String newPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with this email does not exist."));
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }
}
