package step.Security.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import step.Security.payload.RegisterRequest;

import java.util.Collection;

public class UserDetailsAuth1 implements UserDetails {
    private RegisterRequest authenticatedUser;
    public UserDetailsAuth1(RegisterRequest user){
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
        return this.authenticatedUser.getEmail();
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
