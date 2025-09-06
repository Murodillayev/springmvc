package uz.pdp.valid;

import org.springframework.stereotype.Component;
import uz.pdp.exception.BadRequestException;
import uz.pdp.model.AuthUserCreateDto;

@Component
public class AuthUserValidator {
    public void validateOnCreate(AuthUserCreateDto dto) {
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            throw new BadRequestException("Username cannot be null or empty");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new BadRequestException("Password cannot be null or empty");
        }
        if (dto.getPassword().length() > 16) {
            throw new BadRequestException("Password must be at most 16 characters");
        }
        if (dto.getUsername().length() > 10) {
            throw new BadRequestException("Username must be at most 10 characters");
        }
        if (dto.getPassword().length() < 8) {
            throw new BadRequestException("Password must be at least 8 characters");
        }
        if (dto.getFullName() == null || dto.getFullName().isEmpty()) {
            throw new BadRequestException("Full name cannot be null or empty");
        }

        if (dto.getRoleId() == null || dto.getRoleId().isEmpty()) {
            throw new BadRequestException("Role cannot be null or empty");
        }
    }
}
