INSERT INTO users(username,password,enabled)
VALUES ('service','d26db2e6d24c3708f28cf41ed80799f6c29d9159', true);

INSERT INTO authorities (username, authority)
VALUES ('service', 'ROLE_SERVICE');