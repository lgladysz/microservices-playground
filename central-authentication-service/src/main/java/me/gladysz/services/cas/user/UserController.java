package me.gladysz.services.cas.user;


import lombok.RequiredArgsConstructor;
import me.gladysz.services.cas.user.domain.UserFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import me.gladysz.services.cas.user.domain.dto.RegistrationRequestDto;
import me.gladysz.services.cas.user.domain.dto.UserDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class UserController {

    private final UserFacade userFacade;


    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegistrationRequestDto requestDto) {
        return ResponseEntity.ok(userFacade.createUserOrThrow(requestDto));
    }
}
