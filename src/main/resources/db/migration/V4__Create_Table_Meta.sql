CREATE TABLE IF NOT EXISTS t_meta (
  id bigserial NOT NULL,
  post_id BIGINT NOT NULL,
  key VARCHAR(50) NOT NULL,
  content TEXT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_meta_post
    FOREIGN KEY (post_id)
    REFERENCES t_post(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE INDEX idx_meta_post
  ON t_meta (post_id ASC);