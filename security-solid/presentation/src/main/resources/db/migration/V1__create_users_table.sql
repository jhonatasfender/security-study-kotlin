CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    is_enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE user_roles (
    user_id VARCHAR(36) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create admin user
INSERT INTO users (id, username, password, is_enabled) VALUES (
    '00000000-0000-0000-0000-000000000000',
    'admin',
    '$2a$10$X7r3z9Z8Q1Q2W3E4R5T6Y7U8I9O0P1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P',
    TRUE
);

-- Assign admin role
INSERT INTO user_roles (user_id, role) VALUES (
    '00000000-0000-0000-0000-000000000000',
    'ADMIN'
); 