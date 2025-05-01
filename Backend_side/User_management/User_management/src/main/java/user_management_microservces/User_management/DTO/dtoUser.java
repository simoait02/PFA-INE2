package user_management_microservces.User_management.DTO;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class dtoUser {
    private Integer id;
    private String name;
    private Date date;
    private String mail;
    private String bio;

}
