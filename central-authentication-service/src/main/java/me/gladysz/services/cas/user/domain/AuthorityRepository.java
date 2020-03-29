package me.gladysz.services.cas.user.domain;

import org.springframework.data.jpa.repository.Query;
import me.gladysz.services.cas.exception.EntityNotFoundException;

import java.util.Set;

interface AuthorityRepository extends BasicRepository<Authority, Long> {

    Set<Authority> findByRoleIn(Set<Authority.Role> roles);

    Authority findByRole(Authority.Role roles);

    @Query("SELECT auth from Authority auth JOIN auth.users usr where usr.email = :email")
    Set<Authority> findByUsersEmail(String email);

    @Override
    default Authority findOneOrThrow(String name) {
        Authority authority = findByRole(Authority.Role.valueOf(name));
        if (authority == null) {
            throw new EntityNotFoundException("Not found Authority for role name: " + name);
        }
        return authority;
    }
}
