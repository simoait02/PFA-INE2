package step.Security.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import step.Security.payload.RegisterRequest;

@Component
public class Repo {

    private final RestTemplate restTemplate;

    private final String Register_Url = "http://localhost:8070/api/users/auth/register";
    private final String Login_Url = "http://localhost:8070/api/users/auth/login";
    private final String Validate_Url = "http://localhost:8070/api/users/auth/validate/";
    private final String User_Details_Url = "http://localhost:8070/api/users/auth/details/";

    @Autowired
    public Repo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void CreateUser(RegisterRequest authenticatedUser) {
        ResponseEntity<Void> response = this.restTemplate.postForEntity(Register_Url, authenticatedUser, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
        } else {
            throw new RuntimeException("ERROR IN REGISTRATION");
        }
    }


    public RegisterRequest findUserByEmail(String email) {
        try {
                String url = User_Details_Url + email;
                ResponseEntity<RegisterRequest> response = this.restTemplate.getForEntity(
                        url,
                        RegisterRequest.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    return response.getBody();
                } else {
                    throw new RuntimeException("Failed to retrieve user: " + response.getStatusCode());
                }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user details: " + e.getMessage(), e);
        }
    }

    public String validateToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        final String TOKEN_PATTERN = "^[A-Za-z0-9\\-_.]+$";
        if (!token.matches(TOKEN_PATTERN)) {
            throw new IllegalArgumentException("Invalid token format: must contain only alphanumeric characters, hyphens, underscores, or periods");
        }
        String url = this.Validate_Url + token;
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("Token validation failed with status: " + response.getStatusCode());
    }
}