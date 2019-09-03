INSERT INTO role (id, name) VALUES (1, "ROLE_USER");
INSERT INTO role (id, name) VALUES (2, "ROLE_ADMIN");

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);