CREATE TABLE user
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) unique NOT NULL,
    password  VARCHAR(255)        NOT NULL,
    salt      VARCHAR(255)        NOT NULL,
    phone_num VARCHAR(20),
    email     VARCHAR(255),
    is_vip    BOOLEAN DEFAULT 0
);

CREATE TABLE todo
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    topic    VARCHAR(255) NOT NULL,
    details  VARCHAR(255) NOT NULL,
    deadline TIMESTAMP     NOT NULL,
    status   BOOLEAN DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE blog
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    time     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title    VARCHAR(255) NOT NULL,
    content  text         NOT NULL,
    FOREIGN KEY (username) REFERENCES user (username)
);

CREATE TABLE blog_comment
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    blog_id    BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    content    TEXT   NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (blog_id) REFERENCES blog (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE blog_like
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    blog_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (blog_id) REFERENCES blog (id)
);

CREATE TABLE blog_favorite
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    blog_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (blog_id) REFERENCES blog (id)
);

