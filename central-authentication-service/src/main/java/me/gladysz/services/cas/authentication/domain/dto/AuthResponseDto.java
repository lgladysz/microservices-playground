package me.gladysz.services.cas.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class AuthResponseDto {

    private final String token;
}
