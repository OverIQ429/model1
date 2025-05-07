
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
VALUES ('c654c01a-866b-4aea-808c-90857b8535ec', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('1581e66d-c214-4f49-9c46-89210ad88bb1', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('e7b96939-f626-4eef-8e69-d63021200d3f', '2025-01-01', 'random_text', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('ace4b44c-c319-4cae-97e2-e8e81c7c7846', '2025-01-02', 'random_string', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('5739a206-1022-4471-93e1-c8dedc58cbcb', '2024-07-24', 'e7b96939-f626-4eef-8e69-d63021200d3f', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c09c0018-0032-49b4-be8b-ebbc14e33621', '2025-02-25', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', '1581e66d-c214-4f49-9c46-89210ad88bb1');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('8568c0aa-9ee5-4ca9-873d-7374806995f3', '2025-01-01', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f7f2d19c-003a-4058-bf30-d84316131275', '2024-11-01', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('4be83646-a62f-4513-baa3-2586b6b31741', '2024-08-30', 'e7b96939-f626-4eef-8e69-d63021200d3f', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('7e837255-9f67-48c5-8846-b47adc83c383', '2024-10-11', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('5b611448-ef75-4758-8477-7a6bb5a90055', '2024-08-05', 'e7b96939-f626-4eef-8e69-d63021200d3f', '1581e66d-c214-4f49-9c46-89210ad88bb1');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('4b72c9bb-9068-4640-ac77-bc6250e3b991', '2024-10-11', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', 'c654c01a-866b-4aea-808c-90857b8535ec');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('8d9c0137-daa6-4214-b7db-1bb686e018a6', '2024-10-31', 'ace4b44c-c319-4cae-97e2-e8e81c7c7846', '1581e66d-c214-4f49-9c46-89210ad88bb1');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('d6123c5e-85dd-4098-a2c7-410ba1ef0adc', '2024-05-23', 'e7b96939-f626-4eef-8e69-d63021200d3f', 'c654c01a-866b-4aea-808c-90857b8535ec');
