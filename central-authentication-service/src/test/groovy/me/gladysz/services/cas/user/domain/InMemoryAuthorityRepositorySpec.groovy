package me.gladysz.services.cas.user.domain

import org.springframework.data.domain.Pageable
import spock.lang.Specification

class InMemoryAuthorityRepositorySpec extends Specification {

    Authority authorityClient
    Authority authorityAdmin
    User user

    void setup() {

        user = new User()
        user.id = 1L
        user.email = "john@whick.me"
        user.enabled = true

        authorityClient = new Authority()
        authorityClient.id = 1L
        authorityClient.role = Authority.Role.ROLE_CLIENT
        authorityClient.users.add(user)

        authorityAdmin = new Authority()
        authorityAdmin.id = 2L
        authorityAdmin.role = Authority.Role.ROLE_ADMIN
        authorityAdmin.users.add(user)

        user.authorities.addAll(authorityClient, authorityAdmin)
    }

    def "should save one to db"() {
        given: "database"
            InMemoryAuthorityRepository db = new InMemoryAuthorityRepository()
        when: "i add authority"
            db.save(authorityClient)
        then: "database has this authority"
            db.findOneOrThrow(authorityClient.authority)
    }

    def "should find all"() {
        given: "database with roles: client and admin"
            InMemoryAuthorityRepository db = new InMemoryAuthorityRepository()
            db.save(authorityAdmin)
            db.save(authorityClient)
        when: "i find all"
            def result = db.findAll(Pageable.unpaged())
        then: "i have page with 2 roles"
            result.totalElements == 2
        and: "one role is Client"
            result.get().anyMatch({ authority -> authority.authority == Authority.Role.ROLE_CLIENT.name })
        and: "other one is Admin"
            result.get().anyMatch({ authority -> authority.authority == Authority.Role.ROLE_ADMIN.name })
    }

    def "should find authorities for user by his email"() {
        given: "database with authority bounded with user"
            InMemoryAuthorityRepository db = new InMemoryAuthorityRepository()
            db.save(authorityAdmin)
            db.save(authorityClient)
        when: "im looking for authorities for user"
            def result = db.findByUsersEmail(user.email)
        then: "i have 2 authorities"
            result.size() == 2
        and: "one of authority is Client"
            result.findAll({ authority -> authority.authority == Authority.Role.ROLE_CLIENT.name }).size() == 1
        and: "other one is Admin"
            result.findAll({ authority -> authority.authority == Authority.Role.ROLE_ADMIN.name }).size() == 1
    }
}
