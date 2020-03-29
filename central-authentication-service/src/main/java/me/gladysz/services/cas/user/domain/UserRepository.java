package me.gladysz.services.cas.user.domain;

import me.gladysz.services.cas.exception.EntityNotFoundException;

import java.util.Optional;

interface UserRepository extends BasicRepository<User, Long> {

    @Override
    default User findOneOrThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not found User with email: " + email));
    }

    Optional<User> findByEmail(String email);
}
