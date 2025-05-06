
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
VALUES ('bdbc45ec-df96-4b8b-a41c-b3b97edeb28b', 'jimmy@mail.ru', 'Ivan Ivanov', '2006-05-15');

INSERT INTO users (id, email, fio, registration_date)
VALUES ('33f278fb-c0ba-460b-9762-cc88fe31e650', 'billy@mail.ru', 'Anna Smirnova', '2006-08-20');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('8dafaa57-236b-4885-ae32-39468e1ff890', '2025-01-01', 'random_text', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO posts (id, publication_date, text, "user")
VALUES ('fa8cc372-0582-4893-9471-44c888a6355f', '2025-01-02', 'random_string', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('caf8b5ff-6abb-485e-8d68-85984f80726c', '2024-05-06', 'fa8cc372-0582-4893-9471-44c888a6355f', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('48731a29-b3e7-4805-b47b-0fa521950036', '2024-10-30', '8dafaa57-236b-4885-ae32-39468e1ff890', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('21dd085f-39b6-4015-9237-3d3f39995ed9', '2024-06-05', '8dafaa57-236b-4885-ae32-39468e1ff890', '33f278fb-c0ba-460b-9762-cc88fe31e650');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('60431df1-37c7-4c24-8ef7-d2ce822b5594', '2024-09-29', '8dafaa57-236b-4885-ae32-39468e1ff890', '33f278fb-c0ba-460b-9762-cc88fe31e650');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('1a085e85-cf42-468e-8a0f-114d4054854b', '2024-10-24', 'fa8cc372-0582-4893-9471-44c888a6355f', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('f630e336-ef37-407c-a30c-92881f3382b1', '2024-08-08', 'fa8cc372-0582-4893-9471-44c888a6355f', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('c7032fb6-dd92-43d7-a705-5cf84d0bb649', '2024-12-05', 'fa8cc372-0582-4893-9471-44c888a6355f', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('eee7329f-3568-4ccd-a05e-8c0f2db90d99', '2024-06-13', 'fa8cc372-0582-4893-9471-44c888a6355f', '33f278fb-c0ba-460b-9762-cc88fe31e650');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('9de518e1-07e9-4043-96d1-06e0381499a0', '2024-07-14', '8dafaa57-236b-4885-ae32-39468e1ff890', 'bdbc45ec-df96-4b8b-a41c-b3b97edeb28b');

INSERT INTO likes (id, publication_date, owner_id, user_id)
VALUES ('8e28026f-1976-4e65-9e89-77b84fc7d1dd', '2024-07-07', '8dafaa57-236b-4885-ae32-39468e1ff890', '33f278fb-c0ba-460b-9762-cc88fe31e650');
