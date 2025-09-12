package uz.pdp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.model.AuthUser;
import uz.pdp.model.AuthUserCreateDto;
import uz.pdp.model.Role;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.valid.AuthUserValidator;

import java.util.List;

@Controller
public class AuthController {

    private final AuthUserValidator validator;
    private final AuthUserRepository authUserRepository;


    public AuthController(AuthUserValidator validator, AuthUserRepository authUserRepository) {
        this.validator = validator;
        this.authUserRepository = authUserRepository;
    }


    @RequestMapping("/register")
    public String registerPage(Model model, @RequestParam(name = "error_message", required = false) String errorMessage) {
        List<Role> allRoles = authUserRepository.findAllRoles();
        model.addAttribute("roles", allRoles);
        model.addAttribute("error_message", errorMessage);
        model.addAttribute("dto", new AuthUserCreateDto());
        return "/register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("dto") AuthUserCreateDto dto, BindingResult bindingResult,Model model) {

//        validator.validateOnCreate(dto);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/register";
        }
        AuthUser authUser = new AuthUser();
        authUser.setFullName(dto.getFullName());
//        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
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
