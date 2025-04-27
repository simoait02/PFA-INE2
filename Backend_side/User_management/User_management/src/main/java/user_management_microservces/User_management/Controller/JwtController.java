package user_management_microservces.User_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_management_microservces.User_management.DTO.Authentication_DTO;
import user_management_microservces.User_management.DTO.Dto_User;
import user_management_microservces.User_management.Service_Users.Service_Management;

@RestController
@RequestMapping("/api/users/auth")
public class JwtController {
    @Autowired
    private Service_Management serviceManagement;
    @PostMapping("/register")
    public ResponseEntity CreateAccount(@RequestBody Authentication_DTO authenticationDto){
        this.serviceManagement.CreateUser(authenticationDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Authentication_DTO> Login(@RequestBody Authentication_DTO authenticationDto){
        return ResponseEntity.ok(this.serviceManagement.getMyAccountForLogin(authenticationDto.getMail()));
    }
    @GetMapping("/validate/{token}")
    public ResponseEntity<String> isValidate(@PathVariable String token){
        if(this.serviceManagement.TokenValidation(token)) return ResponseEntity.ok("VALID");
        throw new RuntimeException("TOKEN IS NOT VALID");
    }

}
