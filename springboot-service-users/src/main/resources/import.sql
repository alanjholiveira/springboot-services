INSERT INTO `users` (username, password, enabled, name, nickname, email) VALUES ('user', '$2a$10$2OPP7h.fjBt/HdgI3Ajm9OI4JKWvagWcf96NL0wDknzv8M70OIbie', true, 'User', 'usr', 'user@demo.com');
INSERT INTO `users` (username, password, enabled, name, nickname, email) VALUES ('admin', '$2a$10$DPVT/ZCLqca8VaOI/72apOHRQRerXEu2/GV4gLTN8kXu.QxVnQDvS', true, 'Admin', 'adm', 'admin@demo.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USE');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1); 
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2); 
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1); 