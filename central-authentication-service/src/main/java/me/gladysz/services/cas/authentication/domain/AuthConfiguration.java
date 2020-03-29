package me.gladysz.services.cas.authentication.domain;

import me.gladysz.services.cas.infrastrucure.authentication.AuthTokenProperties;
import me.gladysz.services.cas.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
class AuthConfiguration {

    @Bean
    AuthFacade authFacade(AuthTokenProperties tokenProperties,
                          AuthenticationManager authenticationManager,
                          UserFacade userFacade
    ) {
        AuthTokenFactory tokenFactory = new AuthTokenFactory(tokenProperties);
        return new AuthFacade(authenticationManager, tokenFactory, userFacade);
    }
}
