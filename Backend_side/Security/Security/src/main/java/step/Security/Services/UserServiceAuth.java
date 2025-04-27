package step.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import step.Security.Models.Authenticated_User;
import step.Security.Repository.Repo;

@Service
public class UserServiceAuth {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RestTemplate restTemplate;
    private String Register_Url="http://localhost:8070/api/users/auth/register";
    private String Login_Url="http://localhost:8070/api/users/auth/login";
    private String Validate_Url="http://localhost:8070/api/users/auth/validate/";
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private Repo repo;


    public String Verify(Authenticated_User authenticatedUser){
        Authentication authentication= this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticatedUser.getMail(),authenticatedUser.getPassword()));
        if(authentication.isAuthenticated()){
            return this.jwtService.generateToken(authenticatedUser.getMail());
        }
        throw  new RuntimeException("USER NOT FOUND");

    }
    public void Save(Authenticated_User authenticatedUser){
        authenticatedUser.setPassword(bCryptPasswordEncoder.encode(authenticatedUser.getPassword()));
        this.repo.CreateUser(authenticatedUser);
    }
    public String Validation(String token){
        return this.repo.Validation(token);
    }

}
