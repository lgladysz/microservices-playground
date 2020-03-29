INSERT INTO CAS.authority (id, role) VALUES (nextval('cas.authority_id_seq'), 'ROLE_ADMIN');
INSERT INTO CAS.authority (id, role) VALUES (nextval('cas.authority_id_seq'), 'ROLE_CLIENT');

INSERT INTO CAS."user" (id, email, enabled, password) VALUES (nextval('cas.user_id_seq'), 'test@gladysz.me', true, '$2a$10$j3usj6KlqoBnMHWroHhNaOOUjXX8n.D.lfgQif4mtcXFkbp95OJQ2');

INSERT INTO CAS.user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO CAS.user_authority (user_id, authority_id) VALUES (1, 2);
