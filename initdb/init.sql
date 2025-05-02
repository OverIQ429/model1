
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
VALUES ('4f149ece-95a8-4842-8fd2-a62e032a9a72', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('e5bd331a-df23-41f7-9855-582b9a79d88c', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('498d6be6-f4b5-409b-afa1-ec7ae14fdd42', '2025-01-01', 'random_text', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('985f759b-7f5e-46a5-83ae-a145cf6365ca', '2025-01-02', 'random_string', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('2f2cbf77-f080-423e-b049-5342a94d6648', '2024-05-25', '985f759b-7f5e-46a5-83ae-a145cf6365ca', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('d8c6a392-b755-4958-a71c-6b9370e79a69', '2024-07-07', '985f759b-7f5e-46a5-83ae-a145cf6365ca', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('e4f270f0-68e4-455f-b0ac-75734638167b', '2024-05-18', '498d6be6-f4b5-409b-afa1-ec7ae14fdd42', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('a36f457d-c05c-4a5e-a12e-3a369e355a0a', '2024-05-15', '985f759b-7f5e-46a5-83ae-a145cf6365ca', 'e5bd331a-df23-41f7-9855-582b9a79d88c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('a986cf02-1592-4afd-a01b-707a80ea7373', '2024-06-03', '498d6be6-f4b5-409b-afa1-ec7ae14fdd42', '4f149ece-95a8-4842-8fd2-a62e032a9a72');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('5bd264b6-4b75-4d29-b2fe-61cc11a4fe22', '2024-07-05', '498d6be6-f4b5-409b-afa1-ec7ae14fdd42', 'e5bd331a-df23-41f7-9855-582b9a79d88c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c0bdf082-24b6-4f73-9cf7-9fa1a82ad7b9', '2025-03-18', '985f759b-7f5e-46a5-83ae-a145cf6365ca', 'e5bd331a-df23-41f7-9855-582b9a79d88c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c5520bf9-7857-4624-b502-055166263976', '2024-06-04', '985f759b-7f5e-46a5-83ae-a145cf6365ca', 'e5bd331a-df23-41f7-9855-582b9a79d88c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('54116a01-35bb-4ec1-b946-d3f4324553fa', '2024-09-11', '498d6be6-f4b5-409b-afa1-ec7ae14fdd42', 'e5bd331a-df23-41f7-9855-582b9a79d88c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('6c78a3c7-3c65-47f0-8d1b-e89bfd8fb07e', '2024-12-06', '985f759b-7f5e-46a5-83ae-a145cf6365ca', 'e5bd331a-df23-41f7-9855-582b9a79d88c');
