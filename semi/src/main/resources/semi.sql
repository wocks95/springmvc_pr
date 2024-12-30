DROP DATABASE IF EXISTS db_semi;
CREATE DATABASE IF NOT EXISTS db_semi;
USE db_semi;

DROP TABLE IF EXISTS tbl_attach;
DROP TABLE IF EXISTS tbl_notice;


CREATE TABLE IF NOT EXISTS tbl_notice
(
    notice_id       INT NOT NULL AUTO_INCREMENT COMMENT '공지사항아이디',
    notice_title    VARCHAR(100) NOT NULL,
    notice_contents VARCHAR(1000) NULL,
    created_at       DATETIME NULL,
    CONSTRAINT pk_notice PRIMARY KEY (notice_id)
)ENGINE=INNODB COMMENT='공지사항';

CREATE TABLE IF NOT EXISTS tbl_attach
(
    attach_id INT NOT NULL AUTO_INCREMENT,
    notice_id INT,
    file_path VARCHAR(100),
    original_filename VARCHAR(100),
    filesystem_name VARCHAR(100),
    download_count INT,
    CONSTRAINT pk_attcah PRIMARY KEY (attach_id),
    CONSTRAINT fk_notice_attach FOREIGN KEY (notice_id)
        REFERENCES tbl_notice (notice_id) ON DELETE CASCADE
)ENGINE=INNODB COMMENT='첨부파일';

COMMIT;
