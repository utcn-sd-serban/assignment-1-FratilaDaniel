CREATE TABLE IF NOT EXISTS question(
    id serial PRIMARY KEY,
    author_id INTEGER NOT NULL,
    title VARCHAR(32) NOT NULL,
    text VARCHAR(64) NOT NULL,
    creation_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS apptags(
    id serial PRIMARY KEY,
    content VARCHAR(32) NOT NULL
);
CREATE TABLE IF NOT EXISTS verde(
    id serial PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    pass VARCHAR(32) NOT NULL
);


CREATE TABLE IF NOT EXISTS questiontags(
    id serial PRIMARY KEY,
    question_id INTEGER,
    tag_id INTEGER
);