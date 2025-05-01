package user_management_microservces.User_management.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_management_microservces.User_management.DTO.LoginRequest;
import user_management_microservces.User_management.DTO.dtoUser;
import user_management_microservces.User_management.Service_Users.Service_Management;
import user_management_microservces.User_management.DTO.AccountDetails;

@RestController
@RequestMapping("/api/users/auth")
public class JwtController {

    private final Service_Management userService;

    public JwtController(Service_Management userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createAccount(@RequestBody AccountDetails accountDetails) {
        userService.createUser(accountDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<dtoUser> login(@RequestBody LoginRequest accountDetails) {
        return ResponseEntity.ok(userService.getUserByEmail(accountDetails.getIdentification()));
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token) {
        if (userService.validateToken(token)) {
            return ResponseEntity.ok("VALID");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("TOKEN IS NOT VALID");
    }
}