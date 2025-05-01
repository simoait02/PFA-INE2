package step.Security.payload;

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
