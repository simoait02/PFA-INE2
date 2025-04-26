package user_management_microservces.User_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_management_microservces.User_management.DTO.Authentication_DTO;
import user_management_microservces.User_management.DTO.Dto_User;
import user_management_microservces.User_management.Service_Users.Service_Management;

@RestController
@RequestMapping("/api/users")
public class User_Controller {
    @Autowired
    private Service_Management serviceManagement;

    @GetMapping("/")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(this.serviceManagement.getUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dto_User> getSingleUser(@PathVariable int id){
        return ResponseEntity.ok(this.serviceManagement.getSingleUser(id));
    }
    @GetMapping("/me")
    public ResponseEntity<Dto_User> getMyAccount(@RequestBody Authentication_DTO authenticationDto){
        return ResponseEntity.ok(this.serviceManagement.getMyAccount(authenticationDto.getMail()));
    }
    @PostMapping("/register")
    public ResponseEntity CreateAccount(@RequestBody Authentication_DTO authenticationDto){
        this.serviceManagement.CreateUser(authenticationDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
    @PutMapping("/me")
    public ResponseEntity<Dto_User> UpdateMyAccount(@RequestBody Dto_User user){
        return ResponseEntity.ok(this.serviceManagement.ModifyUser(user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteMyAccount(@PathVariable int id){
        this.serviceManagement.DeleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
