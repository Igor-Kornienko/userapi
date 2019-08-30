INSERT INTO Role (name) VALUES ("USER");
INSERT INTO Role (name) VALUES ("ADMIN");

INSERT INTO UserRoles (userId, roleId) VALUES (1, 1);
INSERT INTO UserRoles (userId, roleId) VALUES (2, 1);
INSERT INTO UserRoles (userId, roleId) VALUES (2, 2);