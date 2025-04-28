
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
VALUES ('f081c105-9c0b-4f58-9f2c-f494616a463c', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('967191a8-7052-4b4b-98ce-84a69a655402', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('70ed5676-46c3-455d-89b7-3700c677d6d3', '2025-01-01', 'random_text', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('65f76722-3c77-4e1e-aa2f-ead3f1ff6d14', '2025-01-02', 'random_string', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('b3e106e0-f49c-4ede-ad42-4ba301dcf0f8', '2024-07-25', '70ed5676-46c3-455d-89b7-3700c677d6d3', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f4bebece-df63-4b14-9639-f450f72e73e8', '2024-05-21', '65f76722-3c77-4e1e-aa2f-ead3f1ff6d14', '967191a8-7052-4b4b-98ce-84a69a655402');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('13746da7-7c09-489e-b99d-a839f60cd2eb', '2025-04-05', '65f76722-3c77-4e1e-aa2f-ead3f1ff6d14', '967191a8-7052-4b4b-98ce-84a69a655402');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c6e111fb-dc20-46f5-be08-c0fd4ec6e071', '2024-04-29', '70ed5676-46c3-455d-89b7-3700c677d6d3', '967191a8-7052-4b4b-98ce-84a69a655402');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('39aa8044-2e9f-4c07-b0dc-9af12ce4317b', '2024-09-12', '70ed5676-46c3-455d-89b7-3700c677d6d3', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('14ac6f7d-153b-4645-a42d-ca3ce4dfd441', '2024-10-13', '70ed5676-46c3-455d-89b7-3700c677d6d3', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('ef5992cd-fa2b-4c21-b7af-11c0c0238577', '2024-08-27', '70ed5676-46c3-455d-89b7-3700c677d6d3', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('9ecac585-2b9d-458e-b061-2d31dffed214', '2024-10-24', '70ed5676-46c3-455d-89b7-3700c677d6d3', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f602148b-f682-458c-ad71-f591271ed9a4', '2025-03-11', '65f76722-3c77-4e1e-aa2f-ead3f1ff6d14', 'f081c105-9c0b-4f58-9f2c-f494616a463c');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('b9357b59-1707-481b-8d2c-b3dbe525b50f', '2025-02-19', '65f76722-3c77-4e1e-aa2f-ead3f1ff6d14', 'f081c105-9c0b-4f58-9f2c-f494616a463c');
