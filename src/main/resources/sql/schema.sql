CREATE TABLE if not exists md_file
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_name   VARCHAR(100),
    file_content   CLOB,
    update_date timestamp
);
