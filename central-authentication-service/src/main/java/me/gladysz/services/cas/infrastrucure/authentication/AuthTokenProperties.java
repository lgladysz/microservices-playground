package me.gladysz.services.cas.infrastrucure.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Configuration
@Validated
@PropertySource("classpath:security.yml")
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class AuthTokenProperties {

    @NotBlank
    private String header = "Authorization";

    @NotBlank
    private String prefix = "Bearer ";

    @NotNull
    private Long expiration = 86400L;

    @NotBlank
    private String secret = "JwtSecretKey";
}
