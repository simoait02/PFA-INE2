package user_management_microservces.User_management.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_management_microservces.User_management.DTO.dtoUser;
import user_management_microservces.User_management.Service_Users.Service_Management;
import user_management_microservces.User_management.DTO.AccountDetails;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Service_Management userService;

    public UserController(Service_Management userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/details/{email}")
    public ResponseEntity<AccountDetails> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getAccountDetailsForLogin(email));
    }

    @GetMapping("/")
    public ResponseEntity<List<dtoUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<dtoUser> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDetails> getMyAccount(@RequestHeader("Authorization") String authHeader) {
        String token = userService.extractTokenFromHeader(authHeader);
        String email = userService.extractEmailFromToken(token);
        return ResponseEntity.ok(userService.getAccountDetailsForLogin(email));
    }

    @PutMapping("/me")
    public ResponseEntity<dtoUser> updateMyAccount(@RequestBody dtoUser user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}