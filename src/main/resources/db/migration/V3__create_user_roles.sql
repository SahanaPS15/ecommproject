CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    roles VARCHAR(255),
    CONSTRAINT fk_user_roles FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
