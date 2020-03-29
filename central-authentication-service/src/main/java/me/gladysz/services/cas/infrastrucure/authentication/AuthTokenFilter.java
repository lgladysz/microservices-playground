package me.gladysz.services.cas.infrastrucure.authentication;

import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.authentication.domain.AuthFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
class AuthTokenFilter extends OncePerRequestFilter {

    private final AuthTokenProperties authTokenProperties;
    private final AuthFacade authFacade;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        String header = request.getHeader(authTokenProperties.getHeader());

        if (headerIsValid(header)) {
            try {
                authFacade.authenticate(getToken(header));
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        goToNextFilter(request, response, filterChain);
    }

    private boolean headerIsValid(String requestHeader) {
        return (requestHeader != null && requestHeader.startsWith(authTokenProperties.getPrefix()));
    }

    private String getToken(String header) {
        return header.replace(authTokenProperties.getPrefix(), "");
    }

    private void goToNextFilter(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
