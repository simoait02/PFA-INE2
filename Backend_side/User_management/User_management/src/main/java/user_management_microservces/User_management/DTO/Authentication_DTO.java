package user_management_microservces.User_management.DTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authentication_DTO {
    private String username;
    private String mail;
    private String password;
}
