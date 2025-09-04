package uz.pdp.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                prepareAuthority(authUser));
    }



    private @NonNull List<GrantedAuthority> prepareAuthority(AuthUser authUser) {
        String role = repository.findRoleByRoleId(authUser.getRoleId());
        List<String> permissions = repository.findAllPermissionsByRoleId(authUser.getRoleId());

        List<String> stringAuthorities = new ArrayList<>();

        stringAuthorities.addAll(permissions);
        stringAuthorities.add("ROLE_" + role);

        return stringAuthorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}


// update_user, update_product, ROLE_admin,