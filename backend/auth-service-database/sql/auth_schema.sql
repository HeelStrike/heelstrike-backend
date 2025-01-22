CREATE DATABASE auth_db;

\c auth_db;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS app_roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS app_users (
    uuid UUID DEFAULT uuid_generate_v4() UNIQUE PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    primary_email VARCHAR (58) NOT NULL,
    secondary_email VARCHAR (58),
    mobile BIGINT, -- TODO: Change to BIGINT for mobile numbers (int out of range)
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES app_roles(id)
);