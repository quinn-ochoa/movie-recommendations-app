BEGIN TRANSACTION;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE movies (
	id INT,
	title VARCHAR(200) NOT NULL,
	overview TEXT,
	poster_path VARCHAR(200),
	vote_average DECIMAL (5,3),
	do_not_show boolean,
	CONSTRAINT PK_movie_id PRIMARY KEY (id)
);

CREATE TABLE movies_users(
	movie_id int,
	user_id int,
	liked boolean,
	CONSTRAINT PK_movie_user_id PRIMARY KEY (movie_id, user_id),
	CONSTRAINT FK_movie_id FOREIGN KEY (movie_id) REFERENCES movies(id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE genres (
    id INT,
    name VARCHAR(30) NOT NULL,
	CONSTRAINT PK_genre_id PRIMARY KEY (id),
	CONSTRAINT UQ_id UNIQUE (id)
);

CREATE TABLE certifications (
	certification_id INT,
    certification VARCHAR(30),
	meaning TEXT,
	CONSTRAINT PK_certification_id PRIMARY KEY (certification_id)
);



CREATE TABLE users_info (
	user_id SERIAL,
	email varchar(50),
	full_name varchar(200),
	birthday DATE NOT NULL,
	nc17_requested boolean,
	CONSTRAINT PK_user_id PRIMARY KEY (user_id),
	CONSTRAINT FK_user_info FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE movies_genres (
    movie_id INT,
    genre_id INT,
    CONSTRAINT PK_movies_genrse PRIMARY KEY (movie_id, genre_id),
    CONSTRAINT FK_movie_id FOREIGN KEY (movie_id) REFERENCES movies(id),
    CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE users_genres (
    user_id INT,
    genre_id INT,
    CONSTRAINT PK_users_genres PRIMARY KEY (user_id, genre_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id)
);

CREATE TABLE movies_certifications (
    certification_id INT,
	movie_id INT,
    CONSTRAINT PK_movies_certifications PRIMARY KEY (certification_id, movie_id),
    CONSTRAINT FK_movie_id FOREIGN KEY (movie_id) REFERENCES movies(id),
    CONSTRAINT FK_certification_id FOREIGN KEY (certification_id) REFERENCES certifications(certification_id)
);

COMMIT TRANSACTION;


