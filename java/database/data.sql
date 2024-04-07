BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO genres (id,name) VALUES (28,'Action');
INSERT INTO genres (id,name) VALUES (12,'Adventure');
INSERT INTO genres (id,name) VALUES (16,'Animation');
INSERT INTO genres (id,name) VALUES (35,'Comedy');
INSERT INTO genres (id,name) VALUES (80,'Crime');
INSERT INTO genres (id,name) VALUES (99,'Documentary');
INSERT INTO genres (id,name) VALUES (18, 'Drama');
INSERT INTO genres (id,name) VALUES (10751,'Family');
INSERT INTO genres (id,name) VALUES (14,'Fantasy');
INSERT INTO genres (id,name) VALUES (36,'History');
INSERT INTO genres (id,name) VALUES (27,'Horror');
INSERT INTO genres (id,name) VALUES (10402,'Music');
INSERT INTO genres (id,name) VALUES (9648,'Mystery');
INSERT INTO genres (id,name) VALUES (10749,'Romance');
INSERT INTO genres (id,name) VALUES (878,'Science Fiction');
INSERT INTO genres (id,name) VALUES (10770,'TV Movie');
INSERT INTO genres (id,name) VALUES (53, 'Thriller');
INSERT INTO genres (id,name) VALUES (10752,'War');
INSERT INTO genres (id,name) VALUES (37,'Western');

COMMIT TRANSACTION;
