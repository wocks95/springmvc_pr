DROP DATABASE IF EXISTS db_semi;
CREATE DATABASE IF NOT EXISTS db_semi;
USE db_semi;

DROP TABLE IF EXISTS tbl_attach;
DROP TABLE IF EXISTS tbl_notice;
DROP TABLE IF EXISTS tbl_user;

CREATE TABLE IF NOT EXISTS tbl_user
(
    user_id INT NOT NULL AUTO_INCREMENT COMMENT '유저아이디',
    user_pw VARCHAR(100) NULL COMMENT '유저비밀번호',
    user_email VARCHAR(100) NULL COMMENT '유저이메일',
    user_name VARCHAR(100) NULL COMMENT '유저이름',
    profile_img VARCHAR(1000),
    CONSTRAINT pk_user PRIMARY KEY (user_id)
)ENGINE=INNODB COMMENT '유저';

CREATE TABLE IF NOT EXISTS tbl_notice
(
    notice_id INT NOT NULL AUTO_INCREMENT COMMENT '공지사항아이디',
    user_id INT,
    notice_title VARCHAR(100) NOT NULL,
    notice_contents VARCHAR(1000) NULL,
    modify_dt DATETIME NULL,
    create_at DATETIME NULL,
    CONSTRAINT pk_notice PRIMARY KEY (notice_id),
    CONSTRAINT fk_user_notice FOREIGN KEY (user_id)
        REFERENCES tbl_user (user_id) ON DELETE SET NULL -- 공지사항 작성자가 없어지면 해당 작성자 정보를 NULL 처리한다.
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

INSERT INTO tbl_user VALUES (NULL, 'chan@naver.com', SHA2('chan', 256), '관리자', NULL);
COMMIT;
