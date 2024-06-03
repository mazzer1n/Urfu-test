DROP TABLE IF EXISTS Program CASCADE;
DROP TABLE IF EXISTS Person CASCADE;
DROP TABLE IF EXISTS Institute CASCADE;
DROP TABLE IF EXISTS Module CASCADE

CREATE TABLE Institute (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           title VARCHAR(255) NOT NULL
);

CREATE TABLE Person (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        fullname VARCHAR(100) NOT NULL
);

CREATE TABLE Program (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         title VARCHAR(255) NOT NULL UNIQUE,
                         cypher VARCHAR(50) NOT NULL UNIQUE,
                         level VARCHAR(50) NOT NULL,
                         standard VARCHAR(50) NOT NULL,
                         institute_id UUID,
                         person_id UUID,
                         accreditationTime DATE,
                         FOREIGN KEY (institute_id) REFERENCES Institute(id),
                         FOREIGN KEY (person_id) REFERENCES Person(id)
);

CREATE TABLE Module (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        title VARCHAR(255) NOT NULL UNIQUE

)