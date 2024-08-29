-- Create users table

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    username VARCHAR(500) NOT NULL,
    email VARCHAR(1000) NOT NULL,
    password VARCHAR(2000),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    role VARCHAR(255),
    created_at DATETIME,
    created_by VARCHAR(1000),
    deleted_at DATETIME,
    deleted_by VARCHAR(1000),
    updated_at DATETIME,
    updated_by VARCHAR(1000)
);
