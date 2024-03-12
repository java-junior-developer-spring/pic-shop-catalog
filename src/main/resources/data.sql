INSERT INTO pic_catalog_genres (id, genre_name, url) VALUES (1, 'Портрет', 'portrait');
INSERT INTO pic_catalog_genres (id, genre_name, url) VALUES (2, 'Натюрморт', 'still-life');
INSERT INTO pic_catalog_genres (id, genre_name, url) VALUES (3, 'Пейзаж', 'landscape');
INSERT INTO pic_catalog_genres (id, genre_name, url) VALUES (4, 'Историческая живопись', 'history-painting');
INSERT INTO pic_catalog_genres (id, genre_name, url) VALUES (5, 'Графика', 'graphics');

INSERT INTO pic_catalog_pictures (id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id)
VALUES (1, 'Дама в шляпе', 'Реалистичность модели', 'women', 'women01.png', 90000, '2022-12-10', false, 1);
INSERT INTO pic_catalog_pictures (id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id)
VALUES (2, 'Брюнетка', 'Красота модели', 'women', 'women02.png', 130000, '2018-04-11', false, 1);
INSERT INTO pic_catalog_pictures (id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id)
VALUES (3, 'Дедушка', 'Реалистичность модели', 'old_man', 'old_man01.png', 210000, '2019-09-02', false, 1);

INSERT INTO pic_catalog_pictures (id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id)
VALUES (4, 'Восход солнца', 'Яркость красок', 'sun', 'sun01.png', 57000, '2019-02-22', false, 3);
INSERT INTO pic_catalog_pictures (id, picture_name, picture_description, tag, img_path, price, created_at, in_archive, genre_id)
VALUES (5, 'Закат', 'Необычность восприятия', 'sun', 'sun02.png', 99000, '2021-06-19', false, 3);