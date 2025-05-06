
CREATE TABLE IF NOT EXISTS users (
    id uuid NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    fio character varying(255) COLLATE pg_catalog."default",
    registration_date timestamp(6) without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS posts (
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    publication_date timestamp(6) without time zone,
    text character varying(255) COLLATE pg_catalog."default",
    "user" uuid NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id),
    CONSTRAINT fkrgj3cqthk0cqd8lsnuy5qeikg FOREIGN KEY ("user")
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS likes (
    id uuid NOT NULL,
    publication_date timestamp(6) without time zone,
    owner_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id uuid NOT NULL,
    CONSTRAINT likes_pkey PRIMARY KEY (id),
    CONSTRAINT fkns0jk5hjuvu0adxcqe28lfngq FOREIGN KEY (owner_id)
        REFERENCES public.posts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fknvx9seeqqyy71bij291pwiwrg FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO users (id, email, fio, registration_date)
VALUES ('8bb6d1e0-5660-4cb1-b7c2-e28220b060f2', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('b33fc425-b553-4d63-84cd-4244d37a6b0f', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('4ff2eeb8-99df-4da7-84a0-e276b144bce5', '2025-01-01', 'random_text', '8bb6d1e0-5660-4cb1-b7c2-e28220b060f2');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('c1435202-4ebd-481f-b733-a6e9b52f9fbf', '2025-01-02', 'random_string', '8bb6d1e0-5660-4cb1-b7c2-e28220b060f2');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('a15547b7-2be4-4269-8703-9c5f6aec1500', '2024-09-16', 'c1435202-4ebd-481f-b733-a6e9b52f9fbf', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('3270b4f2-5b36-4bf2-b03d-e7eace6cf66e', '2025-03-17', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('1e1f139b-44ba-4b36-9507-8e4e9a1bc1b6', '2024-12-11', 'c1435202-4ebd-481f-b733-a6e9b52f9fbf', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('bc52c249-80c4-4570-b08e-a6686425cdba', '2025-04-06', 'c1435202-4ebd-481f-b733-a6e9b52f9fbf', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('606f112a-4dc0-4299-b4a3-a6bc7c5e1420', '2024-09-29', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('9f550395-250c-4eab-a1c8-1adde072e22b', '2024-11-27', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', '8bb6d1e0-5660-4cb1-b7c2-e28220b060f2');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('240e86b6-dfc8-4c79-97cf-11c2fe441eb4', '2025-02-24', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', '8bb6d1e0-5660-4cb1-b7c2-e28220b060f2');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('5d51d81a-57f7-42a9-ace0-41fb0bd7b159', '2024-11-07', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('d9410190-287b-4b90-ae55-38ba92eb4200', '2025-02-08', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', '8bb6d1e0-5660-4cb1-b7c2-e28220b060f2');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('69cb6d01-af26-41ef-a887-7c31fe96aa35', '2025-04-18', '4ff2eeb8-99df-4da7-84a0-e276b144bce5', 'b33fc425-b553-4d63-84cd-4244d37a6b0f');
