package uz.pdp.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    private boolean canLogin;


    public CustomUserDetails(String id, String username, String password, List<GrantedAuthority> authorities, boolean canLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.canLogin = canLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.canLogin;
    }
}
