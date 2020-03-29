package me.gladysz.services.cas.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public final class AuthRequestDto {

    @NotBlank(message = "Provide a email")
    private String email;

    @NotBlank(message = "Provide a password")
    private String password;
}
