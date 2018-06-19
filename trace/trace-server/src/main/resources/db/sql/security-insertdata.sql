INSERT INTO users(username,password,enabled)
VALUES ('user','d26db2e6d24c3708f28cf41ed80799f6c29d9159', true);
INSERT INTO users(username,password,enabled)
VALUES ('admin','d26db2e6d24c3708f28cf41ed80799f6c29d9159', true);
INSERT INTO users(username,password,enabled)
VALUES ('viewer','d26db2e6d24c3708f28cf41ed80799f6c29d9159', true);

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('viewer', 'ROLE_VIEWER');