USE marko_yaminsky;

DROP PROCEDURE IF EXISTS insertion_into_continent;
DELIMITER //
CREATE PROCEDURE insertion_into_continent(
    IN new_name VARCHAR(20),
    OUT generated_id INT
)
BEGIN
    INSERT INTO continent (name) VALUE (new_name);
    SELECT id INTO generated_id FROM continent WHERE name = new_name;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS insert_ten_records_into_client;
DELIMITER //
CREATE PROCEDURE insert_ten_records_into_client()
BEGIN
    DECLARE counter INT DEFAULT 1;
    WHILE counter < 11
        DO
            INSERT INTO client(first_name, last_name, country_id) VALUE (
                    CONCAT('Vitalik-', counter),
                    CONCAT('Dupa-', counter),
                    '1'
                );
            SET counter = counter + 1;
        END WHILE;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS maximum_balance;
DELIMITER //
CREATE FUNCTION maximum_balance()
    RETURNS DECIMAL DETERMINISTIC
BEGIN
    RETURN (SELECT MAX(balance) FROM bank_account);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS bank_account_maximum_balance;
DELIMITER //
CREATE PROCEDURE bank_account_maximum_balance(
    OUT res_maximum_balance INT)
BEGIN
    SELECT maximum_balance() INTO res_maximum_balance;
END //
DELIMITER ;

set @query = '';
DROP PROCEDURE IF EXISTS cursor_creation_of_tables_with_names_timestamp;
DELIMITER //
CREATE PROCEDURE cursor_creation_of_tables_with_names_timestamp()
begin
    DECLARE done INT DEFAULT FALSE;
    DECLARE limits INT;
    DECLARE bank_name VARCHAR(25);

    DECLARE random_cursor CURSOR FOR
        SELECT name FROM bank;

    DECLARE CONTINUE HANDLER FOR
        NOT FOUND SET done = TRUE;

    OPEN random_cursor;

    tableInitialize: LOOP
        FETCH random_cursor INTO bank_name;
        IF done = TRUE THEN
            leave tableInitialize;
        END IF;

        SET limits = FLOOR(RAND()*(9-1+1)+1);
        SET @query = CONCAT('CREATE TABLE ', bank_name, '_', CURRENT_TIMESTAMP() + 1, ' (');

        tableColumn: LOOP
            IF limits < 0 THEN
                LEAVE tableColumn;
            END IF;

            SET @query = CONCAT(@query, ' COLUMN', limits, ' VARCHAR(100) ');

            IF limits != 0 THEN
                SET @query = CONCAT(@query,',');
            END IF;

            SET limits = limits - 1;
        END LOOP tableColumn;

        SET @query = CONCAT(@query, ' )');
        PREPARE resultQuery FROM @query;
        EXECUTE resultQuery;
        DEALLOCATE PREPARE resultQuery;
    END LOOP tableInitialize;

    CLOSE random_cursor;
end //
DELIMITER ;