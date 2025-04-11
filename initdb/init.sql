
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
VALUES ('09c79059-8928-4bad-8977-dce8eaf27586', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('519ed6d6-6aad-429f-b8b4-0cefd3a64497', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('78422fd5-1da8-4cf5-a2bd-309110e245eb', '2025-01-01', 'random_text', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('52c90ee0-5a4f-40c8-893d-19d95e508ad3', '2025-01-02', 'random_string', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c294fdb6-2b08-4972-b49c-1d64f086bbd5', '2024-10-22', '78422fd5-1da8-4cf5-a2bd-309110e245eb', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('48504a78-1c66-4a5f-8ccd-8a42a2d34e54', '2025-02-18', '52c90ee0-5a4f-40c8-893d-19d95e508ad3', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('48ca2785-e232-47a7-a634-8d2bd8dd68da', '2024-07-06', '52c90ee0-5a4f-40c8-893d-19d95e508ad3', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('0b4c3467-450a-4501-99ed-d33fcc106e1e', '2024-07-13', '52c90ee0-5a4f-40c8-893d-19d95e508ad3', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('98db2cdf-3565-4929-be9e-05d1db2e1ca8', '2024-11-11', '78422fd5-1da8-4cf5-a2bd-309110e245eb', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c90259d2-4bb3-408f-838a-b11c89c611d2', '2024-10-29', '52c90ee0-5a4f-40c8-893d-19d95e508ad3', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('eb9205ed-bde0-468a-bc8b-9c57af7181f6', '2024-10-31', '78422fd5-1da8-4cf5-a2bd-309110e245eb', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('cad54701-842e-4970-a066-1a41979ecc52', '2024-11-02', '78422fd5-1da8-4cf5-a2bd-309110e245eb', '09c79059-8928-4bad-8977-dce8eaf27586');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('bf676a34-1ef0-40a8-a42e-2528d6ec575b', '2024-07-11', '52c90ee0-5a4f-40c8-893d-19d95e508ad3', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('d6369c19-e934-4003-9d0f-a8e5c5a7bf18', '2024-10-26', '78422fd5-1da8-4cf5-a2bd-309110e245eb', '519ed6d6-6aad-429f-b8b4-0cefd3a64497');
