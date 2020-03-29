package me.gladysz.services.cas.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gladysz.services.cas.user.domain.dto.AuthorityDto;
import me.gladysz.services.cas.user.domain.dto.RoleDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTHORITY", schema = "CAS")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    AuthorityDto dto() {
        return AuthorityDto.builder()
                .role(this.role.dto())
                .build();
    }

    enum Role {
        ROLE_CLIENT("ROLE_CLIENT"),
        ROLE_ADMIN("ROLE_ADMIN");

        @Getter
        private final String name;

        Role(String name) {
            this.name = name;
        }

        RoleDto dto() {
            return RoleDto.valueOf(name());
        }
    }
}
