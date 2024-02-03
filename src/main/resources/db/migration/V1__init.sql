CREATE TABLE IF NOT EXISTS film (
    id SERIAL,
    title VARCHAR(100),
    director VARCHAR(100),
    duration DECIMAL(5,2),
    release_date DATE,
    language VARCHAR(50),
    genre VARCHAR(50),
    image_url VARCHAR(255),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS scene (
    id SERIAL,
    title VARCHAR(100),
    description VARCHAR(255),
    budget DECIMAL(10, 2),
    minutes DECIMAL(5,2),
    film_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (film_id) REFERENCES film(id)
    );

CREATE TABLE IF NOT EXISTS characters (
    id SERIAL,
    name VARCHAR(100),
    role VARCHAR(100),
    description TEXT,
    cost DECIMAL(10, 2),
    scene_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );
