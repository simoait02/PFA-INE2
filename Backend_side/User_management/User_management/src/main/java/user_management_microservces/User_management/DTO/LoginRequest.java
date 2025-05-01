package user_management_microservces.User_management.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public  class LoginRequest {
    private String identification;
    private String password;
}
