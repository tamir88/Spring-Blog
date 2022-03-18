package com.tamirhamtzany.springblog.service;

import com.tamirhamtzany.springblog.dto.AuthenticationResponse;
import com.tamirhamtzany.springblog.dto.LoginRequest;
import com.tamirhamtzany.springblog.dto.RegisterRequest;
import com.tamirhamtzany.springblog.model.User;
import com.tamirhamtzany.springblog.repository.UserRepository;
import com.tamirhamtzany.springblog.security.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword())); // encoding the password
        userRepository.save(user);
    }

    //private  method to encoding the password
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);

    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authenticationToken = jwtUtility.generateToken(authentication);
        String username = loginRequest.getUsername();
        return new AuthenticationResponse(authenticationToken, username);
    }

    // taking Spring security user from contexHolder and return principal as optional
    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        return Optional.of(principal);

    }
}
