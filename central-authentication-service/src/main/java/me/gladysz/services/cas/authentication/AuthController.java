package me.gladysz.services.cas.authentication;


import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.authentication.domain.AuthFacade;
import me.gladysz.services.cas.authentication.domain.dto.AuthRequestDto;
import me.gladysz.services.cas.authentication.domain.dto.AuthResponseDto;
import me.gladysz.services.cas.authentication.domain.exception.AuthException;
import me.gladysz.services.cas.infrastrucure.authentication.AuthEndpointPath;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
class AuthController {

    private final AuthFacade authFacade;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Cas works!");
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @GetMapping("/helloSecured2")
    public ResponseEntity<String> helloWorldSecured2() {
        return ResponseEntity.ok("Cas works! SECURED");
    }

    @GetMapping("/helloSecured")
    public ResponseEntity<String> helloWorldSecured() {
        return ResponseEntity.ok("Cas works! SECURED");
    }


    @PostMapping(AuthEndpointPath.AUTH_PATH)
    public ResponseEntity<AuthResponseDto> auth(@RequestBody @Valid AuthRequestDto request) {

        Optional<AuthResponseDto> response = authFacade.generateToken(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(response.orElseThrow(() -> new AuthException("Can't create token.")));
    }
}
