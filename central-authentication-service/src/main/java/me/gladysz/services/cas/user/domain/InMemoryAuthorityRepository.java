package me.gladysz.services.cas.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import me.gladysz.services.cas.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

class InMemoryAuthorityRepository implements AuthorityRepository {

    private ConcurrentHashMap<String, Authority> map = new ConcurrentHashMap<>();

    @Override
    public Set<Authority> findByRoleIn(Set<Authority.Role> roles) {
        Set<Authority> authorities = new HashSet<>();
        roles.forEach(role -> {
            if (map.containsKey(role.getName())) {
                authorities.add(map.get(role.getName()));
            }
        });
        return authorities;
    }

    @Override
    public Authority findByRole(Authority.Role role) {
        requireNonNull(role);
        if (map.containsKey(role.getName())) {
            return map.get(role.getName());
        } else {
            throw new EntityNotFoundException("Not found Authority for role: " + role.getName());
        }
    }

    @Override
    public Set<Authority> findByUsersEmail(String email) {
        Set<Authority> authorities = new HashSet<>();
        map.values()
                .forEach(authority -> authority.getUsers()
                        .forEach(user -> {
                            if (user.getEmail().equals(email)) {
                                authorities.add(authority);
                            }
                        })
                );

        return authorities;
    }

    @Override
    public Authority save(Authority object) {
        requireNonNull(object);
        map.put(object.getRole().getName(), object);
        return object;
    }

    @Override
    public Authority findOneOrThrow(String name) {
        Authority user = map.get(name);
        if (user == null) {
            throw new EntityNotFoundException("Entity with name: " + name + " not found.");
        }
        return user;
    }

    public void delete(String name) {
        map.remove(name);
    }

    @Override
    public Page<Authority> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }
}
