package step.Security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import step.Security.Models.Authenticated_User;
import step.Security.Services.UserServiceAuth;

@RestController
@RequestMapping("auth/users")
public class AuthControllerUser {
    @Autowired
    private UserServiceAuth userServiceAuth;
    @PostMapping("/register")
    public void Create(@RequestBody Authenticated_User authenticatedUser){
        this.userServiceAuth.Save(authenticatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody Authenticated_User authenticatedUser){
        return ResponseEntity.ok(this.userServiceAuth.Verify(authenticatedUser));

    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<String> Validate(@PathVariable String token){
        return ResponseEntity.ok(this.userServiceAuth.Validation(token));
    }



}
