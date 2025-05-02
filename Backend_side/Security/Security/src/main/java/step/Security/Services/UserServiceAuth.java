package step.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import step.Security.Repository.Repo;
import step.Security.payload.LoginRequest;
import step.Security.payload.RegisterRequest;

@Service
public class UserServiceAuth {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Repo repo;

    @Autowired
    public UserServiceAuth(BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationManager authenticationManager, JwtService jwtService, Repo repo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.repo = repo;
    }

    public String verify(LoginRequest authenticatedUser) {
        if (authenticatedUser.getIdentification() == null || authenticatedUser.getIdentification().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (authenticatedUser.getPassword() == null || authenticatedUser.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticatedUser.getIdentification(),
                            authenticatedUser.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                return this.jwtService.generateToken(authenticatedUser.getIdentification());
            } else {
                throw new org.springframework.security.authentication.BadCredentialsException("Invalid credentials");
            }
        } catch (AuthenticationException e) {
            throw new org.springframework.security.authentication.BadCredentialsException("Invalid credentials");
        }
    }

    public void Save(RegisterRequest authenticatedUser){
        authenticatedUser.setPassword(bCryptPasswordEncoder.encode(authenticatedUser.getPassword()));
        this.repo.CreateUser(authenticatedUser);
    }
    public String validation(String token){
        return this.repo.validateToken(token);
    }

}
