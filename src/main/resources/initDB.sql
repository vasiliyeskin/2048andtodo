DROP TABLE IF EXISTS todo;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS names;

-- auto-generated definition
CREATE TABLE todo
(
  id   INTEGER PRIMARY KEY DEFAULT 0,
  done BOOL DEFAULT TRUE   NOT NULL,
  text VARCHAR             NOT NULL
);

-- auto-generated definition
CREATE TABLE customer
(
  id   INTEGER PRIMARY KEY DEFAULT 0,
  email      VARCHAR    NULL,
  first_name VARCHAR    NULL,
  last_name  VARCHAR    NULL
);

-- auto-generated definition
CREATE TABLE names
(
  ID   INTEGER PRIMARY KEY DEFAULT 0,
  NAME TEXT NOT NULL
);