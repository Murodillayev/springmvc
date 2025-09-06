package uz.pdp.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserCreateDto {

    @NotBlank(message = "fullName is required")
    @Size(min = 12,message = "fullname uzunligi 12 dan katta bolishi kerak")
    private String fullName;

    @NotBlank(message = "authuser.usernemae.required")
    @Pattern(regexp = "[a-z]")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
    private String confirmPassword;

    @NotBlank(message = "roleId is required")
    private String roleId;
}
