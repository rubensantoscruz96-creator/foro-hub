ALTER TABLE topics CHANGE author_id user_id BIGINT NOT NULL;
ALTER TABLE responses CHANGE author_id user_id BIGINT NOT NULL;