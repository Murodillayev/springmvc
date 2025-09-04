package uz.pdp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class TestController {

    @PreAuthorize(value = "hasAnyAuthority('user:create','super','user:super')")
    @GetMapping("/create-user")
    public String createUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "admin-page";
    }

    @PreAuthorize(value = "hasAnyAuthority('super','user:super','user:get')")
    @GetMapping("/get-user")
    public String getUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "admin-page";
    }


    @PreAuthorize(value = "hasAnyAuthority('super','user:super','user:delete')")
    @GetMapping("/delete-user")
    public String deleteUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "admin-page";
    }

    @GetMapping("/main")
    public String main() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "admin-page";
    }

}
