package user_management_microservces.User_management.Enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users  implements Behavior{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String mail;
    private Date date;
    private String password;
    private String bio;
    @Override
    public void CreateChanel() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void CreateStream() {

    }
}
