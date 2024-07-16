CREATE TABLE doctors
(
    id             SERIAL PRIMARY KEY,
    first_name     VARCHAR(100) NOT NULL,
    last_name      VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    office         VARCHAR(10) NOT NULL
);

CREATE TABLE patients
(
    id            SERIAL PRIMARY KEY,
    idnp          VARCHAR(13)  NOT NULL,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100) NOT NULL,
    date_of_birth DATE         NOT NULL,
    gender        CHAR(1)      NOT NULL
);