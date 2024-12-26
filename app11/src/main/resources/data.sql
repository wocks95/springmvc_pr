DROP DATABASE IF EXISTS db_app11;
CREATE DATABASE IF NOT EXISTS db_app11;
USE db_app11;

DROP TABLE IF EXISTS tbl_board;
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE IF NOT EXISTS tbl_user
(
    usr_id      INT AUTO_INCREMENT,
    usr_email   VARCHAR(100) NOT NULL UNIQUE,
    usr_name    VARCHAR(100),
    CONSTRAINT pk_user PRIMARY KEY (usr_id)
)ENGINE=InnoDB COMMENT = '사용자';

CREATE TABLE IF NOT EXISTS tbl_board
(
    board_id  INT AUTO_INCREMENT,
    title     VARCHAR(100) NOT NULL,
    contents  TEXT,
    create_dt DATETIME,
    usr_id    INT,
    CONSTRAINT pk_board PRIMARY KEY (board_id),
    CONSTRAINT fk_user_board FOREIGN KEY (usr_id) REFERENCES tbl_user (usr_id) ON DELETE CASCADE
)ENGINE=InnoDB COMMENT = '게시판';

INSERT INTO tbl_user VALUES (NULL, 'james@gmail.com', 'james');
INSERT INTO tbl_user VALUES (NULL, 'helena@gmail.com', 'helena');
INSERT INTO tbl_user VALUES (NULL, 'harry@gmail.com', 'harry');

INSERT INTO tbl_board VALUES (NULL, '20241209_식단','김치찌개, 된장찌개, 순두부찌개', '2024-12-05 10:00:00', 1);
INSERT INTO tbl_board VALUES (NULL, '20241210_식단','삼겹살, 목살, 항정살', '2024-12-06 10:00:00', 1);
INSERT INTO tbl_board VALUES (NULL, '20241211_식단','초밥, 대방어, 육회', '2024-12-07 10:00:00', 2);
COMMIT;