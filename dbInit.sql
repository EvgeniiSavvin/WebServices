CREATE TABLE persons (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR (200),
    last_name VARCHAR (200),
    age INTEGER,
    height INTEGER,
    is_male BOOLEAN
);

INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Петр', 'Петров', 25, 178, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Владимир', 'Иванов', 26, 170, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Иван', 'Иванов', 27, 185, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Иммануил', 'Кант', 28, 157, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Джордж', 'Клуни', 29, 178, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Билл', 'Рубцов', 30, 165, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Марк', 'Марков', 31, 171, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Галина', 'Матвеева', 32, 164, false);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Святослав', 'Павлов', 33, 165, true);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Ольга', 'Бергольц', 34, 157, false);
INSERT INTO persons(first_name, last_name, age, height, is_male) values ('Лев', 'Рабинович', 35, 163, true);
