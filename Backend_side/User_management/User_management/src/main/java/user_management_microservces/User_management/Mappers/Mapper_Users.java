package user_management_microservces.User_management.Mappers;

import org.springframework.stereotype.Component;
import user_management_microservces.User_management.DTO.Authentication_DTO;
import user_management_microservces.User_management.DTO.Dto_User;
import user_management_microservces.User_management.Enities.Users;

@Component
public class Mapper_Users {

    public Dto_User toDtoUser(Users user){
        return Dto_User.builder()
                .name(user.getUsername())
                .mail(user.getMail())
                .bio(user.getBio())
                .date(user.getDate())
                .build();
    }

    public Users toUser(Dto_User user){
        return Users.builder()
                .username(user.getName())
                .bio(user.getBio())
                .mail(user.getMail())
                .date(user.getDate())
                .build();
    }
    public Users toUser_authentication(Authentication_DTO authenticationDto){
        Users users=Users.builder()
                .mail(authenticationDto.getMail())
                .password(authenticationDto.getPassword())
                .build();
        if(authenticationDto.getUsername()!=null){
            users.setUsername(authenticationDto.getUsername());
        }
        return users;
    }


}
