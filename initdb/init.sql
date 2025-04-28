
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
VALUES ('7633fc44-451c-4f92-9951-0b9f652062bd', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('14141eec-cb0d-438c-b106-442f1bcfc8ef', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('f2d07f44-def4-4ace-94d4-88e3bde7676f', '2025-01-01', 'random_text', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '2025-01-02', 'random_string', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('48922499-dc84-4930-8496-47e81b977730', '2024-08-17', 'f2d07f44-def4-4ace-94d4-88e3bde7676f', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('6cbc6793-622b-473c-ae95-2453f1b9d0d6', '2024-06-11', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '14141eec-cb0d-438c-b106-442f1bcfc8ef');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('e7c8dd0d-d0ba-41a8-a282-b0961dcfb42c', '2024-05-12', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('641aef66-8ab7-44ae-ad16-23e3672818ff', '2024-09-28', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('2d5be981-66ac-4a33-b63d-733447c1b8d2', '2025-03-21', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('46a7e2d5-3357-42e2-8125-2d2a37bcfb74', '2024-07-30', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '14141eec-cb0d-438c-b106-442f1bcfc8ef');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('fe6bc98d-d86b-46af-a7a8-e494cf106bf4', '2025-03-03', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('eee391d7-e87f-4c50-b910-8095db448c85', '2024-08-09', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '7633fc44-451c-4f92-9951-0b9f652062bd');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('766a82f9-368d-42e5-bae0-3072e306cc8f', '2025-02-24', 'f2d07f44-def4-4ace-94d4-88e3bde7676f', '14141eec-cb0d-438c-b106-442f1bcfc8ef');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('73bdccb6-3fc9-49ca-8e62-5200772a225f', '2024-12-13', '1d1e4ff3-6539-4b64-a0c3-6e4c383c4775', '14141eec-cb0d-438c-b106-442f1bcfc8ef');
