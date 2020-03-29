package me.gladysz.services.cas.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public final class RegistrationRequestDto {

    @NotBlank(message = "Provide a email")
    private String email;

    @NotBlank(message = "Provide a password")
    private String password;

    @NotBlank(message = "Provide a password again")
    private String confirmPassword;
}
