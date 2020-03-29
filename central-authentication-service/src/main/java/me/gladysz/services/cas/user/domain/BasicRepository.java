package me.gladysz.services.cas.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface BasicRepository<T, ID> extends Repository<T, ID> {
    T save(T object);

    T findOneOrThrow(String name);

    Page<T> findAll(Pageable pageable);
}
