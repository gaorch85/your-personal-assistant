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
    user_id  BIGINT       NOT NULL,
    topic    VARCHAR(255) NOT NULL,
    details  VARCHAR(255) NOT NULL,
    deadline TIMESTAMP    NOT NULL,
    status   BOOLEAN DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE blog
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL,
    time      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title     VARCHAR(255) NOT NULL,
    content   text         NOT NULL,
    is_public BOOLEAN               DEFAULT 0,
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

CREATE TABLE blog_view
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    blog_id    BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (blog_id) REFERENCES blog (id)
);

CREATE TABLE course
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) UNIQUE NOT NULL,
    user_id BIGINT              NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE schedule
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    time_index  INT CHECK (`time_index` >= 1 AND `time_index` <= 14),
    day_index   INT CHECK (`day_index` >= 1 AND `day_index` <= 7),
    user_id     BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (course_name) REFERENCES course (name)
);


