package step.Security.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import step.Security.Models.Authenticated_User;

@Component
public class Repo {
    @Autowired
    private RestTemplate restTemplate;
    private String Register_Url="http://localhost:8070/api/users/auth/register";
    private String Login_Url="http://localhost:8070/api/users/auth/login";
    private String Validate_Url="http://localhost:8070/api/users/auth/validate/";
    public void CreateUser(Authenticated_User authenticatedUser){
        ResponseEntity<Void> response =this.restTemplate.postForEntity(Register_Url,authenticatedUser, Void.class);
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("USER REGISTERED");
        }
        else{
            throw  new RuntimeException("ERROR IN REGISTRATION");
        }
    }

    public Authenticated_User Login(String mail){
        Authenticated_User authenticatedUser=Authenticated_User.builder()
                .mail(mail)
                .build();
        ResponseEntity<Authenticated_User> response=this.restTemplate.postForEntity(Login_Url,authenticatedUser,Authenticated_User.class);
        return response.getBody();
    }
    public String Validation(String token){
        String url=this.Validate_Url+token;
        ResponseEntity<String> response=this.restTemplate.getForEntity(url,String.class);
        if(response.getStatusCode()== HttpStatus.OK){
            return response.getBody();
        }
        throw  new RuntimeException("TOKEN NOT VALIDATE");
    }
}
