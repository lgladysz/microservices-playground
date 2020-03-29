package me.gladysz.services.cas.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class UserConfiguration {


    UserFacade userFacade(PasswordEncoder passwordEncoder) {
        return userFacade(new InMemoryUserRepository(), new InMemoryAuthorityRepository(), passwordEncoder);
    }


    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          AuthorityRepository authorityRepository,
                          PasswordEncoder passwordEncoder
    ) {
        UserFactory userFactory = new UserFactory(authorityRepository, userRepository, passwordEncoder);
        UserDetailsService userDetailsService = new UserDetailsServiceImpl(userRepository);
        return new UserFacade(userDetailsService, userFactory);
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}
