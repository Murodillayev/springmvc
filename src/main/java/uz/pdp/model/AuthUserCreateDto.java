package uz.pdp.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCreateDto {
    private String fullName;
    private String username;
    private String password;
    private String confirmPassword;
    private String roleId;
}
