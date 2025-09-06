package uz.pdp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.model.AuthUser;
import uz.pdp.model.AuthUserCreateDto;
import uz.pdp.model.Role;
import uz.pdp.repository.AuthUserRepository;

import java.util.List;
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
    public String registerPage(Model model) {

        List<Role> allRoles = authUserRepository.findAllRoles();
        model.addAttribute("roles", allRoles);
        model.addAttribute("dto", new AuthUserCreateDto());
        return "/register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("dto") AuthUserCreateDto dto) {
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setRoleId(dto.getRoleId());
        authUser.setUsername(dto.getUsername());
        authUserRepository.create(authUser);
        return "/login";

    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

}
