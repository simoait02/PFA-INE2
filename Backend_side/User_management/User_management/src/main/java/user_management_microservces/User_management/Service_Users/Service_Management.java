package user_management_microservces.User_management.Service_Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user_management_microservces.User_management.DTO.Authentication_DTO;
import user_management_microservces.User_management.DTO.Dto_User;
import user_management_microservces.User_management.Dao.Jpa_Users;
import user_management_microservces.User_management.Enities.Users;
import user_management_microservces.User_management.Mappers.Mapper_Users;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Service_Management {
    @Autowired
    private Mapper_Users mapper;
    @Autowired
    private Jpa_Users jpaUsers;

    public List<Dto_User> getUsers(){
        return this.jpaUsers.findAll()
                .stream()
                .map(mapper::toDtoUser)
                .collect(Collectors.toList());
    }

    public Dto_User getSingleUser(int id){
        return this.mapper.toDtoUser(this.jpaUsers.findById(id).get());
    }
   public Dto_User getMyAccount(String mail){
        return this.mapper.toDtoUser(this.jpaUsers.findByMail(mail).get());
   }

    public void CreateUser(Authentication_DTO user){
        this.jpaUsers.save(this.mapper.toUser_authentication(user));
    }
    public Dto_User ModifyUser(Dto_User user){
        Users users=this.jpaUsers.findById(user.getId()).get();
        this.mapper.updateFromDto(users,user);
        this.jpaUsers.save(users);
        return this.mapper.toDtoUser(users);
    }
    public void DeleteUser(int id){
        this.jpaUsers.deleteById(id);
    }







}
