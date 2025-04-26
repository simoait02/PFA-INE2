package user_management_microservces.User_management.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import user_management_microservces.User_management.Enities.Users;

public interface Jpa_Users extends JpaRepository<Users,Integer> {
}
