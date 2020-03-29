package me.gladysz.services.cas.authentication.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@RequiredArgsConstructor
class AuthTokenUtils {

    private final AuthTokenExtractor authTokenExtractor;

    static Date calculateExpirationDate(Date createdDate, Long expiration) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }

    static Map<String, Object> prepareClaimsForToken(Collection<? extends GrantedAuthority> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", roles);
        return claims;
    }

    private Boolean isTokenNotExpired(String token) {
        final Date expiration = authTokenExtractor.getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    Optional<Boolean> validateToken(String token) {
        return isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }
}
