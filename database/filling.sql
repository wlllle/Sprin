USE Firm;

DELETE FROM PositionsHistory;
DELETE FROM SubdivisionsPositions;
DELETE FROM Subdivision;
DELETE FROM Person;
DELETE FROM Position;

INSERT INTO Position VALUES (1, 'Директор', 'Работа 7 часов в сутки, 5 дней в неделю. Бумажная работа и контролирование фирмы на всех уровнях.');
INSERT INTO Position VALUES (2, 'Логистик', 'Работа 8 часов в сутки, 5 дней в неделю. Работа с поставщиками.');
INSERT INTO Position VALUES (3, 'Продавец', 'Работа 8 часов в сутки, 5 дней в неделю.');
INSERT INTO Position VALUES (4, 'Уборщик', 'Работа 8 часов в сутки, 5 дней в неделю. Поддержание чистоты и порядка в отделении.');
INSERT INTO Position VALUES (5, 'Работник склада', 'Работа 8 часов в сутки, 5 дней в неделю. Разгрузка, сортировка товара на складе.');
INSERT INTO Position VALUES (6, 'Водитель', 'Работа 8 часов в сутки, 5 дней в неделю. Доставка товара в магазины.');

INSERT INTO Person VALUES (1, 'Иван', 'Иванович', 'Иванов', 'Москва, ул. Ленина, д.29/2, кв.1', 'ВМК МГУ', '2017-01-01');
INSERT INTO Person VALUES (2, 'Илья', 'Иванович', 'Петров', 'Москва, Ломоносовский проспект, д.25, кв.355', 'МехМат МГУ', '2017-01-01');
INSERT INTO Person VALUES (3, 'Алексей', 'Константинович', 'Сидоров', 'Москва, ул. Гарибальди, д.21 к.6, кв. 23', 'Истфак МГОУ', '2017-01-09');
INSERT INTO Person VALUES (4, 'Василий', 'Андреевич', 'Конюхов', 'Москва, Новочерёмушкинская ул., д.40 к.1, кв. 11', 'Истфак МГОУ', '2017-01-11');
INSERT INTO Person VALUES (5, 'Александр', 'Дмитриевич', 'Семёнов', 'Москва, ул. Кржижанского, д.23 к.1, кв. 4', 'Истфак МГОУ', '2017-01-11');
INSERT INTO Person VALUES (6, 'Пётр', 'Петрович', 'Антонов', 'Котельники, мкр. Белая Дача, д.52 к.1, кв. 4', 'Водитель', '2017-01-15');
INSERT INTO Person VALUES (7, 'Михаил', 'Иванович', 'Кондрашин', 'Москва, Марии Ульяновой ул., д.6 к.5, кв. 6', 'Экономический факультет ГУ-ВШЭ', '2017-02-11');
INSERT INTO Person VALUES (8, 'Артём', 'Викторович', 'Лагин', 'Москва, ул. Светланова, д.13 к.1, кв. 11', 'Физфак МГУ', '2017-02-11');
INSERT INTO Person VALUES (9, 'Борис', 'Борисович', 'Борисов', 'Москва, ул. Светланова, д.13 к.1, кв. 11', 'Физфак МГУ', '2017-02-11');

INSERT INTO Subdivision VALUES (1, 'Головной офис', 1, NULL);
INSERT INTO Subdivision VALUES (2, 'Магазин м. Университет', 1, 1);
INSERT INTO Subdivision VALUES (3, 'Магазин м. Академическая', 1, 1);

INSERT INTO SubdivisionsPositions VALUES (1, 1, 1, 1);
INSERT INTO SubdivisionsPositions VALUES (2, 1, 2, 1);
INSERT INTO SubdivisionsPositions VALUES (3, 2, 1, 1);
INSERT INTO SubdivisionsPositions VALUES (4, 2, 3, 1);
INSERT INTO SubdivisionsPositions VALUES (5, 2, 4, 1);
INSERT INTO SubdivisionsPositions VALUES (6, 2, 5, 1);
INSERT INTO SubdivisionsPositions VALUES (7, 2, 6, 1);
INSERT INTO SubdivisionsPositions VALUES (8, 3, 1, 1);
INSERT INTO SubdivisionsPositions VALUES (9, 3, 3, 1);
INSERT INTO SubdivisionsPositions VALUES (10, 3, 4, 1);
INSERT INTO SubdivisionsPositions VALUES (11, 3, 5, 1);
INSERT INTO SubdivisionsPositions VALUES (12, 3, 6, 1);

INSERT INTO PositionsHistory VALUES (1, 1, 1, '2017-01-01', NULL);
INSERT INTO PositionsHistory VALUES (2, 2, 2, '2017-01-01', NULL);
INSERT INTO PositionsHistory VALUES (3, 1, 3, '2017-01-01', NULL);
INSERT INTO PositionsHistory VALUES (4, 3, 4, '2017-01-09', NULL);
INSERT INTO PositionsHistory VALUES (5, 4, 5, '2017-01-11', NULL);
INSERT INTO PositionsHistory VALUES (6, 5, 6, '2017-01-11', NULL);
INSERT INTO PositionsHistory VALUES (7, 6, 7, '2017-01-15', NULL);
INSERT INTO PositionsHistory VALUES (8, 1, 8, '2017-02-07', NULL);
INSERT INTO PositionsHistory VALUES (9, 7, 9, '2017-02-11', NULL);
INSERT INTO PositionsHistory VALUES (10, 8, 10, '2017-02-11', NULL);
INSERT INTO PositionsHistory VALUES (11, 9, 11, '2017-02-11', NULL);
INSERT INTO PositionsHistory VALUES (12, 6, 12, '2017-02-08', NULL);
