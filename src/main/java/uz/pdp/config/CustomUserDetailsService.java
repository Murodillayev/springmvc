package uz.pdp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        return new User(
                authUser.getUsername(),
                authUser.getPassword(),
                List.of(new SimpleGrantedAuthority(authUser.getRole())));
    }
}