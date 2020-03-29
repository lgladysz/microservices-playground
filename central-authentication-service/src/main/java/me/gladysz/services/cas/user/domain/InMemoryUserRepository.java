package me.gladysz.services.cas.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import me.gladysz.services.cas.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@NoRepositoryBean
class InMemoryUserRepository implements UserRepository {

    private ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();

    @Override
    public User save(User object) {
        requireNonNull(object);
        map.put(object.getEmail(), object);
        return object;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(map.get(email));
    }

    @Override
    public User findOneOrThrow(String email) {
        User user = map.get(email);
        if (user == null) {
            throw new EntityNotFoundException("User with email: " + email + " not found.");
        }
        return user;
    }

    public void delete(String email) {
        map.remove(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
}
