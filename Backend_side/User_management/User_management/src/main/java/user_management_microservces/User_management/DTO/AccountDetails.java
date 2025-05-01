
package user_management_microservces.User_management.DTO;

import lombok.*;
import user_management_microservces.User_management.Enities.Roles;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Date birthDate;
    private String bio;
    private Roles role;
}
