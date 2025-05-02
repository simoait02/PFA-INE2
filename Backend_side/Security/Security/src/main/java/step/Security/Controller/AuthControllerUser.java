package step.Security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import step.Security.Services.UserServiceAuth;
import step.Security.payload.LoginRequest;
import step.Security.payload.RegisterRequest;

@RestController
@RequestMapping("auth/users")
public class AuthControllerUser {
    private final UserServiceAuth userServiceAuth;

    @Autowired
    public AuthControllerUser(UserServiceAuth userServiceAuth) {
        this.userServiceAuth = userServiceAuth;
    }

    @PostMapping("/register")
    public void Create(@RequestBody RegisterRequest authenticatedUser){
        this.userServiceAuth.Save(authenticatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequest authenticatedUser){
        return ResponseEntity.ok(this.userServiceAuth.verify(authenticatedUser));

    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<?> validate(@PathVariable String token) {
        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest().body("Token cannot be null or empty");
        }

        try {
            String validationResult = userServiceAuth.validation(token);
            return ResponseEntity.ok(validationResult);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }



}
