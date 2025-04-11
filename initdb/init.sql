
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
VALUES ('9f20e269-bae7-453a-92ff-77134f2b3f96', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('8a5f1d2d-44f9-425e-a732-22671c25938f', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('d28ab187-4814-4443-9204-6109487561c8', '2025-01-01', 'random_text', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('3480ad14-7a51-496b-bbd1-b5846657997e', '2025-01-02', 'random_string', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('02973ce3-9266-423b-b05a-19912028dc5d', '2024-08-15', 'd28ab187-4814-4443-9204-6109487561c8', '8a5f1d2d-44f9-425e-a732-22671c25938f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f442e5b2-c78b-47fa-8478-a2468cb1af69', '2024-11-26', 'd28ab187-4814-4443-9204-6109487561c8', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f04fd7d2-254d-493f-b4a6-7d57ce476aa9', '2024-07-15', 'd28ab187-4814-4443-9204-6109487561c8', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('3c1ac65f-d44a-4ff1-9ca2-266c650a7e51', '2025-04-08', '3480ad14-7a51-496b-bbd1-b5846657997e', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('31c3df9f-c99c-4237-81b9-f1cf7765bde4', '2024-10-21', 'd28ab187-4814-4443-9204-6109487561c8', '8a5f1d2d-44f9-425e-a732-22671c25938f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('977d67f5-f8ab-403a-80f3-c38949451168', '2024-06-28', 'd28ab187-4814-4443-9204-6109487561c8', '8a5f1d2d-44f9-425e-a732-22671c25938f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('154cd0dd-4d0e-4d1e-b00b-fdea5392ca4a', '2025-03-24', 'd28ab187-4814-4443-9204-6109487561c8', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('b443c074-e957-40e0-af5d-a237dd90fc88', '2025-03-28', 'd28ab187-4814-4443-9204-6109487561c8', '8a5f1d2d-44f9-425e-a732-22671c25938f');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('26c5ca67-dedc-453d-8594-a8620f66bdc8', '2025-04-03', '3480ad14-7a51-496b-bbd1-b5846657997e', '9f20e269-bae7-453a-92ff-77134f2b3f96');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('47ca1758-93e1-4d79-859a-20ab4350c0b2', '2024-11-24', '3480ad14-7a51-496b-bbd1-b5846657997e', '8a5f1d2d-44f9-425e-a732-22671c25938f');
