package step.Security.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import step.Security.Models.UserDetailsAuth1;
import step.Security.Repository.Repo;
import step.Security.payload.RegisterRequest;

@Service
public class UserDetailsAuth implements UserDetailsService {
    private final Repo repo;
    public UserDetailsAuth(Repo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            RegisterRequest userDetails = this.repo.findUserByEmail(username);

            if (userDetails == null) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            if (userDetails.getPassword() == null || userDetails.getPassword().isEmpty()) {
                throw new UsernameNotFoundException("User has empty password");
            }
            return new UserDetailsAuth1(userDetails);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error loading user: " + e.getMessage(), e);
        }
    }
}