package user_management_microservces.User_management.Enities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users extends AbstractUser implements Behavior{


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
