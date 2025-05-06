
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
VALUES ('5907167e-4a2d-4851-ae53-4311403c9922', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('f66a213a-cd42-4800-9406-92cbf4c08a7f', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('d7ad2d17-d6ad-48ee-b3c9-99b57a21fbb1', '2025-01-01', 'random_text', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('918d7c8a-b301-49f1-afc6-bef7f04bb7b9', '2025-01-02', 'random_string', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f668813c-faa4-4cd5-aa9f-d526990467b3', '2024-09-26', 'd7ad2d17-d6ad-48ee-b3c9-99b57a21fbb1', 'f66a213a-cd42-4800-9406-92cbf4c08a7f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('155668c8-b70f-4ad3-b26b-0dc425da77df', '2024-06-03', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', 'f66a213a-cd42-4800-9406-92cbf4c08a7f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('6d63ded5-3a87-4b9f-ab32-b9a952612779', '2024-10-21', 'd7ad2d17-d6ad-48ee-b3c9-99b57a21fbb1', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('932c6fec-b73c-428f-9a61-d51fae2a4f49', '2024-09-02', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('54876e7a-596f-4e87-8d74-3344d297cefd', '2025-04-08', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('b4338b15-8915-4048-ab7f-b721b5aafdc8', '2024-10-26', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('e18a8e59-c341-4e86-96d7-61225178daa8', '2025-02-20', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', 'f66a213a-cd42-4800-9406-92cbf4c08a7f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('bc3507f7-76a3-4127-9e94-e2272260564f', '2025-04-06', 'd7ad2d17-d6ad-48ee-b3c9-99b57a21fbb1', '5907167e-4a2d-4851-ae53-4311403c9922');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('7e270e1f-d672-4cf7-9bf0-0f6c14215141', '2024-12-20', '918d7c8a-b301-49f1-afc6-bef7f04bb7b9', 'f66a213a-cd42-4800-9406-92cbf4c08a7f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f1b9d05a-e17b-405f-9413-8e97c95c6235', '2025-02-09', 'd7ad2d17-d6ad-48ee-b3c9-99b57a21fbb1', 'f66a213a-cd42-4800-9406-92cbf4c08a7f');
