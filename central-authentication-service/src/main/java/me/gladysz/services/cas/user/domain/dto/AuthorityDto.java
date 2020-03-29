package me.gladysz.services.cas.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Getter
@EqualsAndHashCode
public class AuthorityDto implements GrantedAuthority {
    @JsonIgnore
    private RoleDto role;

    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
