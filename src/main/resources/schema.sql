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
                        fullname VARCHAR(255) NOT NULL
);

CREATE TABLE Program (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         title VARCHAR(255) NOT NULL UNIQUE ,
                         cypher VARCHAR(255),
                         level VARCHAR(255),
                         standard VARCHAR(255),
                         institute_id UUID,
                         person_id UUID,
                         FOREIGN KEY (institute_id) REFERENCES Institute(id),
                         FOREIGN KEY (person_id) REFERENCES Person(id)
);

CREATE TABLE Module (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        title VARCHAR(255) NOT NULL UNIQUE

)