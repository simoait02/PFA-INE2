package step.Security.Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authenticated_User {
    private String username;
    private String mail;
    private String password;
}
