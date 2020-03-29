package me.gladysz.services.cas.authentication.domain;

import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.user.domain.UserFacade;
import org.springframework.security.authentication.*;
import me.gladysz.services.cas.authentication.domain.dto.AuthResponseDto;
import me.gladysz.services.cas.authentication.domain.exception.AuthException;
import me.gladysz.services.cas.user.domain.dto.UserDto;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthFacade {

    private final AuthenticationManager authenticationManager;
    private final AuthTokenFactory tokenFactory;
    private final UserFacade userFacade;

    public Optional<AuthResponseDto> generateToken(String email, String password) {
        try {

            final boolean isSuccessfullyAuthenticated = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password))
                    .isAuthenticated();

            if (isSuccessfullyAuthenticated) {
                final UserDto user = (UserDto) userFacade.loadUserDetails(email);
                String token = tokenFactory.generateToken(email, user.getAuthorities());
                return Optional.of(new AuthResponseDto(token));
            }

            return Optional.empty();

        } catch (DisabledException e) {
            throw new AuthException("User is disabled.", e);
        } catch (LockedException e) {
            throw new AuthException("User is locked.", e);
        } catch (BadCredentialsException e) {
            throw new AuthException("Incorrect username or password.", e);
        }
    }

    public boolean authenticate(String token) {

    }

}
