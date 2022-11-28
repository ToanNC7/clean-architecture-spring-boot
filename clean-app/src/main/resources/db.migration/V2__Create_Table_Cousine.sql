CREATE TABLE IF NOT EXISTS cousine(
    id bigserial NOT NULL,
    name VARCHAR(100) NOT NULL,
    UNIQUE (name),
    PRIMARY KEY (Id)
);
