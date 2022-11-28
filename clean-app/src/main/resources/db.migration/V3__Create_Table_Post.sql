CREATE TABLE IF NOT EXISTS t_post (
  id bigserial NOT NULL,
  author_id BIGINT NOT NULL,
  title VARCHAR(75) NOT NULL,
  meta_title VARCHAR(100) NULL,
  slug VARCHAR(100) NOT NULL,
  summary text NULL,
  published bool NULL,
  content TEXT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_post_user
    FOREIGN KEY (author_id)
    REFERENCES t_user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
CREATE INDEX idx_post_user
  ON t_post (author_id ASC);