package me.gladysz.services.cas.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto implements UserDetails {
    private String email;
    private String password;
    private HashSet<AuthorityDto> authorities;
    private boolean enabled;

    @Override
    public Set<AuthorityDto> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }
}
