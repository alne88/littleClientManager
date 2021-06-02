CREATE TABLE users (
    id              SERIAL              PRIMARY KEY,
    username        VARCHAR ( 50 )      UNIQUE NOT NULL,
    password        VARCHAR ( 64 )      NOT NULL,
    role            VARCHAR ( 50 )      NOT NULL,
    last_login      TIMESTAMP           NULL
);


CREATE TABLE clients (
    id                      SERIAL              PRIMARY KEY,
    created_by_user_id      BIGINT              NOT NULL,
    first_name              VARCHAR ( 50 )      NOT NULL,
    last_name               VARCHAR ( 50 )      NOT NULL,
    username                VARCHAR ( 50 )      NOT NULL,
    email                   VARCHAR ( 255 )     UNIQUE NOT NULL,
    address                 VARCHAR ( 255 )     NOT NULL,
    country                 VARCHAR ( 50 )     NOT NULL,

    FOREIGN KEY (created_by_user_id) REFERENCES users (id)
);


CREATE TABLE lookup_values (
    code        VARCHAR ( 50 )      NOT NULL,
    value       VARCHAR ( 50 )      NOT NULL,
    UNIQUE (code, value)
);