INSERT INTO `users` (username, password, enabled, name, nickname, email) VALUES ('admin', '12345', 1, 'Admin', 'ADM', 'admin@demo.com');
INSERT INTO `users` (username, password, enabled, name, nickname, email) VALUES ('adm', '12345', 1, 'John', 'Doe', 'jhon.doe@demo.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USE');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1); 
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2); 
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1); 