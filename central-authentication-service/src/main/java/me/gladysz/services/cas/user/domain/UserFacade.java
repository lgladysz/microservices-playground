package me.gladysz.services.cas.user.domain;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import me.gladysz.services.cas.user.domain.dto.RegistrationRequestDto;
import me.gladysz.services.cas.user.domain.dto.UserDto;

@RequiredArgsConstructor
public class UserFacade {

    private final UserDetailsService userDetailsService;
    private final UserFactory userFactory;


    public UserDetails loadUserDetails(String email) {
        return userDetailsService.loadUserByUsername(email);
    }

    public UserDto createUserOrThrow(RegistrationRequestDto requestDto) {
        return userFactory.createUser(requestDto.getEmail(), requestDto.getPassword(),
                Sets.newHashSet(Authority.Role.ROLE_CLIENT)).dto();
    }
}
