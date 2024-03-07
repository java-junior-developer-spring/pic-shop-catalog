CREATE TABLE IF NOT EXISTS pic_catalog_genres
(
    id         SERIAL PRIMARY KEY,
    genre_name VARCHAR(255) not null,
    url        VARCHAR(255) not null
);

CREATE Table IF NOT EXISTS pic_catalog_pictures
(
    id                  SERIAL PRIMARY KEY,
    picture_name        VARCHAR(255) not null,
    picture_description VARCHAR(255) not null,
    tag                 VARCHAR(255) not null,
    img_path            VARCHAR(255) not null,
    price               int          not null,
    created_at          TIMESTAMP    not null,
    in_archive          boolean      not null,
    genre_id            integer      not null
        REFERENCES pic_catalog_genres ("id")
);