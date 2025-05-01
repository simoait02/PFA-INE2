package user_management_microservces.User_management.Mappers;

import org.springframework.stereotype.Component;
import user_management_microservces.User_management.DTO.dtoUser;
import user_management_microservces.User_management.Enities.Users;
import user_management_microservces.User_management.DTO.AccountDetails;

@Component
public class Mapper_Users {

    public dtoUser toDtoUser(Users user){
        return dtoUser.builder()
                .name(user.getUsername())
                .mail(user.getEmail())
                .bio(user.getBio())
                .date(user.getBirthDate())
                .id(user.getId())
                .build();
    }

    public void updateFromDto(Users users, dtoUser dtoUser){
        if(dtoUser.getName()!=null) users.setUsername(dtoUser.getName());
        if(dtoUser.getBio()!=null) users.setBio(dtoUser.getBio());
        if(dtoUser.getDate()!=null) users.setBirthDate(dtoUser.getDate());
        if(dtoUser.getMail()!=null) users.setEmail(dtoUser.getMail());

    }
    public Users toUser_authentication(AccountDetails authenticationDto){
        Users users=Users.builder()
                .email(authenticationDto.getEmail())
                .password(authenticationDto.getPassword())
                .bio(authenticationDto.getBio())
                .birthDate(authenticationDto.getBirthDate())
                .phoneNumber(authenticationDto.getPhoneNumber())
                .role(authenticationDto.getRole())
                .username(authenticationDto.getUsername())
                .build();
        if(authenticationDto.getUsername()!=null){
            users.setUsername(authenticationDto.getUsername());
        }
        return users;
    }


}
