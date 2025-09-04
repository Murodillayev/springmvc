package uz.pdp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.AuthUser;
import uz.pdp.repository.AuthUserRepository;

import java.util.UUID;

@Controller
public class AuthController {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;



    public AuthController(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping("/register")
    public String register() {

        AuthUser authUser = AuthUser.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Imron")
                .password(passwordEncoder.encode("1111"))
                .username("manager")
                .roleId("3")
                .build();

        authUserRepository.create(authUser);

        return "/login";

    }

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }

}
