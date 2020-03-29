package me.gladysz.services.cas.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import me.gladysz.services.cas.user.domain.exception.UserAlreadyExistException;

import java.util.Collections;
import java.util.Set;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
class UserFactory {
    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    User createUser(final String email, final String password, final Set<Authority.Role> roles) {
        requireNonNull(roles, "Roles can't be null");
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new UserAlreadyExistException("User with email: " + email + " already exist.");
        });

        final Set<Authority> authorities = roles.isEmpty()
                                           ? Collections.singleton(authorityRepository.findByRole(Authority.Role.ROLE_CLIENT))
                                           : authorityRepository.findByRoleIn(roles);
        final User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authorities(authorities)
                .enabled(true)
                .build();

        return userRepository.save(user);
    }
}
