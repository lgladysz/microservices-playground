package me.gladysz.services.cas.authentication.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.infrastrucure.authentication.AuthTokenProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
class AuthTokenFactory {

    private final AuthTokenProperties tokenProperties;

    String generateToken(String email, Collection<? extends GrantedAuthority> roles) {
        final Date createdDate = new Date();
        final Date expirationDate = AuthTokenUtils.calculateExpirationDate(createdDate,
                tokenProperties.getExpiration()
        );

        return Jwts.builder()
                .setClaims(AuthTokenUtils.prepareClaimsForToken(roles))
                .setSubject(email)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret().getBytes())
                .compact();
    }
}
