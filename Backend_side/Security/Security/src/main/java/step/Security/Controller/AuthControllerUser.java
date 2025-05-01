package step.Security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import step.Security.Services.UserServiceAuth;
import step.Security.payload.LoginRequest;
import step.Security.payload.RegisterRequest;

@RestController
@RequestMapping("auth/users")
public class AuthControllerUser {
    @Autowired
    private UserServiceAuth userServiceAuth;
    @PostMapping("/register")
    public void Create(@RequestBody RegisterRequest authenticatedUser){
        this.userServiceAuth.Save(authenticatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequest authenticatedUser){
        return ResponseEntity.ok(this.userServiceAuth.verify(authenticatedUser));

    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<String> Validate(@PathVariable String token){
        return ResponseEntity.ok(this.userServiceAuth.Validation(token));
    }



}
