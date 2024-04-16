BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users_info (user_id, email, full_name, birthday)VALUES (1, 'random@gmail.com', 'John Snow',(CURRENT_DATE - INTERVAL '18 years'));


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
INSERT INTO genres (id,name) VALUES (878,'ScienceFiction');
INSERT INTO genres (id,name) VALUES (10770,'TVMovie');
INSERT INTO genres (id,name) VALUES (53, 'Thriller');
INSERT INTO genres (id,name) VALUES (10752,'War');
INSERT INTO genres (id,name) VALUES (37,'Western');

INSERT INTO certifications (certification_id, certification, meaning) VALUES (4,'R','Under 17 requires accompanying parent or adult guardian 21 or older. The parent/guardian is required to stay with the child under 17 through the entire movie, even if the parent gives the child/teenager permission to see the film alone. These films may contain strong profanity, graphic sexuality, nudity, strong violence, horror, gore, and strong drug use. A movie rated R for profanity often has more severe or frequent language than the PG-13 rating would permit. An R-rated movie may have more blood, gore, drug use, nudity, or graphic sexuality than a PG-13 movie would admit.');
INSERT INTO certifications (certification_id, certification, meaning) VALUES (2,'PG','Some material may not be suitable for children under 10. These films may contain some mild language, crude/suggestive humor, scary moments and/or violence. No drug content is present. There are a few exceptions to this rule. A few racial insults may also be heard');
INSERT INTO certifications (certification_id, certification, meaning) VALUES (5,'NC-17','These films contain excessive graphic violence, intense or explicit sex, depraved, abhorrent behavior, explicit drug abuse, strong language, explicit nudity, or any other elements which, at present, most parents would consider too strong and therefore off-limits for viewing by their children and teens. NC-17 does not necessarily mean obscene or pornographic in the oft-accepted or legal meaning of those words.');
INSERT INTO certifications (certification_id, certification, meaning) VALUES (1,'G','All ages admitted. There is no content that would be objectionable to most parents. This is one of only two ratings dating back to 1968 that still exists today.');
INSERT INTO certifications (certification_id, certification, meaning) VALUES (0,'NR','No rating information.');
INSERT INTO certifications (certification_id, certification, meaning) VALUES (3,'PG-13','Some material may be inappropriate for children under 13. Films given this rating may contain sexual content, brief or partial nudity, some strong language and innuendo, humor, mature themes, political themes, terror and/or intense action violence. However, bloodshed is rarely present. This is the minimum rating at which drug content is present.');


COMMIT TRANSACTION;
