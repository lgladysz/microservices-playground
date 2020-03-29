package me.gladysz.services.cas.authentication.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.infrastrucure.authentication.AuthTokenProperties;

import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
class AuthTokenExtractor {

    private final AuthTokenProperties authTokenProperties;

    String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(authTokenProperties.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

}
