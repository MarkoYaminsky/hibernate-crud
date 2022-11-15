USE marko_yaminsky;

DROP TRIGGER IF EXISTS before_country_insert;
DELIMITER //
CREATE TRIGGER before_country_insert
    BEFORE INSERT
    ON country
    FOR EACH ROW
BEGIN
    DECLARE match_row_for_id INT;
    SELECT COUNT(*) INTO match_row_for_id FROM continent WHERE id = NEW.continent_id;
    IF match_row_for_id = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'A foreign key constrain fails for continent_id';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS before_country_update;
DELIMITER //
CREATE TRIGGER before_country_update
    BEFORE UPDATE
    ON country
    FOR EACH ROW
BEGIN
    DECLARE match_row_for_id INT;
    IF OLD.continent_id != NEW.continent_id then
        SELECT COUNT(*) INTO match_row_for_id FROM continent WHERE id = NEW.continent_id;
        IF match_row_for_id = 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'A foreign key constrain fails for continent_id';
        END IF;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS before_continent_update;
DELIMITER //
CREATE TRIGGER before_continent_update
    BEFORE UPDATE
    ON continent
    FOR EACH ROW
BEGIN
    DECLARE count_used_for_id INT;
    IF OLD.id != NEW.id THEN
        SELECT COUNT(*) INTO count_used_for_id FROM country WHERE continent_id = NEW.id;
        IF count_used_for_id > 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'A foreign key constrain fails for continent_id';
        END IF;
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS before_country_delete;
DELIMITER //
CREATE TRIGGER before_country_delete
    BEFORE DELETE
    ON continent
    FOR EACH ROW
BEGIN
    DECLARE count_used_for_id INT;
    SELECT COUNT(*) INTO count_used_for_id FROM country WHERE id = OLD.id;
    IF count_used_for_id > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'A foreign key constrain fails for continent_id';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS before_client_insert;
DELIMITER //
CREATE TRIGGER before_client_insert
    BEFORE INSERT
    ON client
    FOR EACH ROW
BEGIN
    IF NEW.first_name = 'Vitalik' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot add Vitalik as a client';
    END IF;
END //

DROP TRIGGER IF EXISTS before_client_update;
DELIMITER //
CREATE TRIGGER before_client_update
    BEFORE UPDATE
    ON client
    FOR EACH ROW
BEGIN
    IF NEW.first_name = 'Vitalik' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot make a client Vitalik';
    END IF;
END //

DROP TRIGGER IF EXISTS after_client_insert;
DELIMITER //
CREATE TRIGGER after_client_insert
    BEFORE UPDATE
    ON client
    FOR EACH ROW
BEGIN
    CREATE TABLE IF NOT EXISTS logs
    (
        time  VARCHAR(30) PRIMARY KEY,
        event VARCHAR(10) PRIMARY KEY
    );
    INSERT INTO logs (time, event) VALUE (CONCAT(CURDATE(), ' ', CURRENT_TIME()), 'INSERT');
END //

DROP TRIGGER IF EXISTS after_client_update;
DELIMITER //
CREATE TRIGGER after_client_update
    BEFORE UPDATE
    ON client
    FOR EACH ROW
BEGIN
    CREATE TABLE IF NOT EXISTS logs
    (
        time  VARCHAR(30) PRIMARY KEY,
        event VARCHAR(10) PRIMARY KEY
    );
    INSERT INTO logs (time, event) VALUE (CONCAT(CURDATE(), ' ', CURRENT_TIME()), 'UPDATE');
END //

INSERT INTO continent (id, name)
VALUES ('1', 'Europe'),
       ('2', 'North America'),
       ('3', 'South America'),
       ('4', 'Asia'),
       ('5', 'Australia'),
       ('6', 'Africa');

INSERT INTO country (id, name, continent_id)
VALUES ('1', 'Ukraine', '1'),
       ('2', 'USA', '2'),
       ('3', 'England', '1'),
       ('4', 'Poland', '1'),
       ('5', 'Turkey', '4'),
       ('6', 'Germany', '1'),
       ('7', 'Italy', '1'),
       ('8', 'India', '2'),
       ('9', 'Sweden', '1'),
       ('10', 'Norway', '1');

INSERT INTO bank (id, name, country_id)
VALUES ('1', 'PrivatBank', '1'),
       ('2', 'OshchadBank', '1'),
       ('3', 'RaffeizenBank', '1'),
       ('4', 'Monobank', '1'),
       ('5', 'JP Morgan Chase', '2'),
       ('6', 'Bank of America', '2'),
       ('7', 'HSBC Holdings', '3'),
       ('8', 'Barclays', '3'),
       ('9', 'PKO BP', '4'),
       ('10', 'Bank of India', '8');

INSERT INTO client (id, first_name, last_name, country_id)
VALUES ('1', 'Marko', 'Yaminsky', '1'),
       ('2', 'Yulia', 'Shvets', '1'),
       ('3', 'Ruslan', 'Hursky', '1'),
       ('4', 'Iryna', 'Pistun', '1'),
       ('5', 'Vitaliy', 'Pashynsky', '1'),
       ('6', 'Bob', 'Marley', '2'),
       ('7', 'Ari', 'Gibson', '2'),
       ('8', 'Pedro', 'Russo', '7'),
       ('9', 'Serkan', 'Bolat', '5'),
       ('10', 'Paul', 'Schmidt', '6');

INSERT INTO bank_account (id, requisites, client_id, person_type, bank_id, balance)
VALUES ('1', 'UA372361532832619153846389844', '1', 'physical', '1', '1'),
       ('2', 'UA194713461914711744776763462', '2', 'physical', '1', '123'),
       ('3', 'UA172783384913549965251733334', '2', 'legal', '1', '1234'),
       ('4', 'UA694177987269867959249732816', '3', 'physical', '1', '12984.2'),
       ('5', 'UA429281496999898529547224158', '1', 'legal', '1', '128945.2'),
       ('6', 'GB49BARC20038452236829', '5', 'legal', '7', '99999999'),
       ('7', 'GB60BARC20039539929738', '6', 'legal', '7', '12'),
       ('8', 'PL87109024024187646958327632', '9', 'physical', '9', '0.2'),
       ('9', 'UA722525449199265968315424929', '1', 'physical', '4', '1203'),
       ('10', 'GB44BARC20035351972971', '6', 'legal', '8', '0.23');

