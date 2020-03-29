package me.gladysz.services.cas.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gladysz.services.cas.user.domain.dto.UserDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Entity
@Table(name = "USER", schema = "CAS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, unique = true, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY",
               joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
               inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities = new HashSet<>();

    @Column(nullable = false, name = "ENABLED")
    private boolean enabled = false;

    UserDto dto() {
        return UserDto.builder()
                .email(this.email)
                .password(this.password)
                .authorities(new HashSet<>(this.authorities.stream()
                                .map(Authority::dto)
                                .collect(Collectors.toSet())
                        )
                )
                .enabled(this.enabled)
                .build();
    }
}
