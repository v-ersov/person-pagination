CREATE TABLE person
(
    id        DECIMAL               NOT NULL AUTO_INCREMENT,
    email     VARCHAR(100)          NOT NULL UNIQUE,
    firstname VARCHAR(50),
    lastname  VARCHAR(50),
    ssn       CHAR(11) UNIQUE,
    age       INT,
    deleted   BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT person_pk PRIMARY KEY (id)
);