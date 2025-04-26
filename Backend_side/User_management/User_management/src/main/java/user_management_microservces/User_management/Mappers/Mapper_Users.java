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
                .id(user.getId())
                .build();
    }

    public void updateFromDto(Users users,Dto_User dtoUser){
        if(dtoUser.getName()!=null) users.setUsername(dtoUser.getName());
        if(dtoUser.getBio()!=null) users.setBio(dtoUser.getBio());
        if(dtoUser.getDate()!=null) users.setDate(dtoUser.getDate());
        if(dtoUser.getMail()!=null) users.setMail(dtoUser.getMail());

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
