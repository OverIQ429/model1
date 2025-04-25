
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
VALUES ('08442fa7-0d06-4a7c-902d-21414e975395', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('ef96bb6e-da39-4645-8421-1b8bb487ed3c', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('db4f5464-107c-4bce-90ef-5620259772ea', '2025-01-01', 'random_text', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('a0703a0a-ee28-437e-b282-3f872f58e257', '2025-01-02', 'random_string', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('ba4d8fc5-80d5-4405-b52f-034e0815fdf4', '2025-02-20', 'db4f5464-107c-4bce-90ef-5620259772ea', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('a857c82a-cceb-4737-b893-919749404716', '2024-06-05', 'a0703a0a-ee28-437e-b282-3f872f58e257', 'ef96bb6e-da39-4645-8421-1b8bb487ed3c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('fd0b6abd-e08a-463a-9bb0-ebf7b07f9f1b', '2024-07-16', 'db4f5464-107c-4bce-90ef-5620259772ea', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('55dd8592-2d1e-4d80-9eca-05c8b2d1e775', '2024-08-29', 'a0703a0a-ee28-437e-b282-3f872f58e257', 'ef96bb6e-da39-4645-8421-1b8bb487ed3c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('ea1b9620-98b6-492c-a541-904107e1db1b', '2025-02-14', 'db4f5464-107c-4bce-90ef-5620259772ea', 'ef96bb6e-da39-4645-8421-1b8bb487ed3c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('e48c77a0-1a99-4527-884a-25fb56ba042a', '2024-10-30', 'a0703a0a-ee28-437e-b282-3f872f58e257', 'ef96bb6e-da39-4645-8421-1b8bb487ed3c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f0a6650b-3e82-447a-a576-2e716bf1526e', '2024-08-05', 'a0703a0a-ee28-437e-b282-3f872f58e257', 'ef96bb6e-da39-4645-8421-1b8bb487ed3c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('90058a0c-0547-48fb-a062-4feacefb4f5b', '2024-12-01', 'a0703a0a-ee28-437e-b282-3f872f58e257', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('7633a0f2-e151-43f6-920a-8672e0ff5245', '2025-03-23', 'db4f5464-107c-4bce-90ef-5620259772ea', '08442fa7-0d06-4a7c-902d-21414e975395');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('0cd92a08-f92b-4564-8c37-fd86ba53c0df', '2024-07-15', 'db4f5464-107c-4bce-90ef-5620259772ea', '08442fa7-0d06-4a7c-902d-21414e975395');
