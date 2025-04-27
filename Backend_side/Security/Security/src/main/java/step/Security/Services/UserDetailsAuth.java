package step.Security.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import step.Security.Models.Authenticated_User;
import step.Security.Models.UserDetailsAuth1;
import step.Security.Repository.Repo;

@Service
public class UserDetailsAuth implements UserDetailsService {
    @Autowired
    Repo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authenticated_User authenticatedUser=this.repo.Login(username);
        return new UserDetailsAuth1(authenticatedUser);
    }
}
