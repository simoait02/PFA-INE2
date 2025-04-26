package user_management_microservces.User_management.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import user_management_microservces.User_management.Enities.Users;

import java.util.Optional;

public interface Jpa_Users extends JpaRepository<Users,Integer> {
    public Optional<Users> findByMail(String mail);
}
