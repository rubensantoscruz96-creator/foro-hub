CREATE TABLE courses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status TINYINT NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topics_author_id FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_topics_course_id FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE responses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message TEXT NOT NULL,
    topic_id BIGINT NOT NULL,
    creation_date DATETIME NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_responses_topic_id FOREIGN KEY (topic_id) REFERENCES topics(id),
    CONSTRAINT fk_responses_author_id FOREIGN KEY (author_id) REFERENCES users(id)
);