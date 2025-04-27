package step.Security.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsAuth1 implements UserDetails {
    private Authenticated_User authenticatedUser;
    public UserDetailsAuth1(Authenticated_User user){
        this.authenticatedUser=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.authenticatedUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.authenticatedUser.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
