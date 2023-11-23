USE `enjoytrip_doublejj`;

drop table if exists `attraction_kakao`;
drop table if exists `snap_file`;
drop table if exists `snapshot`;
drop table if exists `interest`;
drop table if exists `reply`;
drop table if exists `board_qna`;
DROP TABLE IF EXISTS `HOTPLACE`;
DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
                          `userId` varchar(16) NOT NULL,
                          `userName` varchar(20) NOT NULL,
                          `userPass` varchar(100) NOT NULL,
                          `userEmail` varchar(50) NOT NULL,
                          `refreshToken` varchar(256),
                          `joinDate` timestamp NULL DEFAULT current_timestamp,
                          PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE `board` (
                         `articleNo` int NOT NULL AUTO_INCREMENT,
                         `userId` varchar(16) NOT NULL,
                         `title` varchar(100) NOT NULL,
                         `content` varchar(2000) NOT NULL,
                         `hit` int DEFAULT '0',
                         `publishedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `visitedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `visitedArea` varchar(100) NULL,
                         PRIMARY KEY (`articleNo`),
                         KEY `board_to_members_user_id_fk` (`userId`),
                         CONSTRAINT `board_to_members_user_id_fk` FOREIGN KEY (`userId`) REFERENCES `member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE HOTPLACE (
                          placeNo INT AUTO_INCREMENT PRIMARY KEY,
                          placeName VARCHAR(100) NOT NULL,
                          visitedDate TIMESTAMP,
                          createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          detail VARCHAR(2000),
                          userId VARCHAR(16) NOT NULL,
                          FOREIGN KEY (userId)
                              REFERENCES Member(userId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

-- select * from member;
-- select * from board;
-- select * from hotplace;

-- #1110 #Q&A 게시판 테이블 추가
CREATE TABLE `board_qna` (
                         `articleNo` int NOT NULL AUTO_INCREMENT,
                         `userId` varchar(16) NOT NULL,
                         `title` varchar(100) NOT NULL,
                         `content` varchar(2000) NOT NULL,
                         `hit` int DEFAULT '0',
                         `publishedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`articleNo`),
                         KEY `board_qna_to_members_user_id_fk` (`userId`),
                         CONSTRAINT `board_qna_to_members_user_id_fk` FOREIGN KEY (`userId`) REFERENCES `member` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_cs;

CREATE TABLE `reply` (
	`replyNo` int NOT NULL auto_increment,
	`comment` varchar(2000) NOT NULL,
	`publishedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,

    `articleNo` int NOT NULL,
    `userId` varchar(16) NOT NULL,

    PRIMARY KEY(`replyNo`),

    KEY `reply_to_board_qna_fk`(`articleNo`),
    CONSTRAINT `reply_to_board_qna_fk` foreign key(`articleNo`) REFERENCES `board_qna`(`articleNo`),

    KEY  `reply_to_member_fk`(`userId`),
    CONSTRAINT `reply_to_member_fk` foreign key(`userId`) REFERENCES `member`(`userId`)
);

-- 사용자 관심 관광지 정보
CREATE TABLE `interest` (
	`userId` varchar(16) NOT NULL,
    `content_id` int NOT NULL,
    
    -- 복합 키
    PRIMARY KEY(`content_id`, `userId`),
    
    -- userID는 FK이며, `member` 내 존재하는 userId로만 만들 것!
    KEY `interest_to_member_fk`(`userId`),
    CONSTRAINT `interest_to_member_fk` foreign key(`userId`) REFERENCES `member`(`userId`)
    -- 현재 Dump 내 attraction 관련 constraint 확인 불가. 렉이 너무 걸림.
);

INSERT INTO `member`(userId, userName, userPass, userEmail, joinDate) VALUES
	('ssafy', '지준호','$2a$10$SeUCKp2Jq6FgwL6dieUmc.xE7pMBlX/I1deUpJNwny58vC3rtuFPq','ccamy@c.com','2023-10-06 07:43:06'),
    ('ssafy123','최준호','$2a$10$6/Z/NgVi2c5vIGkbhve6xOJuUYvxInw4YGo5SXZDXAaqKj8DnWe9u','ajchoi0928@gmail.com','2023-10-06 07:42:42'),
    ('taffy1234','김싸피','$2a$10$fQxtIPaG1aWQHMMUAdAUXO7z6M87rU39UD64L4qMwInHJhUFBem52','ssafy@ssafy.com','2023-10-06 07:43:28'),
    ('test','테스트','$2a$10$tli5kNFPlkuxFxHcOTlLkusAJZXUBeVhoeMi0n.rM3snQkIze/vny','test@test.com','2023-10-06 07:44:11'),
    ('zzafy','싸피','$2a$10$tzHJA7.kjQonCBSsDC0/0O/pOsD/gV71DeTAkcg8nl.9S8Ud3EFSW','ssafy@ssafy.com','2023-10-06 07:43:47');

INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES 
	('taffy1234', 'TEST 투어 제안서', '테스트 테스트', 5, '2023-10-01T02:41:05'),
    ('taffy1234', 'TEST 투어 제안서2', '테스트 테스트', 10, '2023-10-02T02:41:05'),
    ('test', 'TEST 투어 제안서3', '테스트 테스트', 15, '2023-10-03T02:41:05'),
    ('test', 'TEST 투어 제안서4', '테스트 테스트', 20, '2023-10-04T02:41:05'),
    ('ssafy', 'TEST 투어 제안서5', '테스트 테스트', 25, '2023-10-05T02:41:05'),
    ('ssafy', 'TEST 투어 제안서6', '테스트 테스트', 25, '2023-10-05T02:41:05'),
    ('zzafy', 'TEST 투어 제안서7', '테스트 테스트', 25, '2023-10-05T05:41:05'),
    ('ssafy123', 'TEST 투어 제안서8', '테스트 테스트', 25, '2023-10-05T06:41:05'),
    ('ssafy123', 'TEST 투어 제안서9', '테스트 테스트', 25, '2023-10-05T07:41:05');

INSERT INTO board_qna(`userId`,  `title`, `content`) VALUES
	('ssafy', 'title1', 'content1'), ('ssafy123', 'title2', 'content2'),
    ('taffy1234', 'title3', 'content3'), ('test', 'title4', 'content4'),
    ('zzafy', 'title5', 'content5'), ('ssafy', 'title6', 'content6'),
    ('ssafy123', 'title7', 'content7'), ('taffy1234', 'title8', 'content8'),
    ('test', 'title9', 'content9'), ('zzafy', 'title10', 'content10'),

    ('ssafy', 'title11', 'content11'), ('ssafy123', 'title12', 'content12'),
    ('taffy1234', 'title13', 'content13'), ('test', 'title14', 'content14'),
    ('zzafy', 'title15', 'content15'), ('ssafy', 'title16', 'content16'),
    ('ssafy123', 'title17', 'content17'), ('taffy1234', 'title18', 'content18'),
    ('test', 'title19', 'content19'), ('zzafy', 'title20', 'content20'),

    ('ssafy', 'title21', 'content21'), ('ssafy123', 'title22', 'content22'),
    ('taffy1234', 'title23', 'content23'), ('test', 'title24', 'content24'),
    ('zzafy', 'title25', 'content25'), ('ssafy', 'title26', 'content26'),
    ('ssafy123', 'title27', 'content27'), ('taffy1234', 'title28', 'content28'),
    ('test', 'title29', 'content29'), ('zzafy', 'title30', 'content30');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
	("댓글 1-1", 1, 'ssafy'), ("댓글 1-2", 1, 'ssafy123'), ("댓글 1-3", 1, 'taffy1234'), ("댓글 1-4", 1, 'test'), ("댓글 1-5", 1, 'zzafy'),
    ("댓글 1-6", 1, 'ssafy'), ("댓글 1-7", 1, 'ssafy123'), ("댓글 1-8", 1, 'taffy1234'), ("댓글 1-9", 1, 'test'), ("댓글 1-10", 1, 'zzafy'),
    ("댓글 2-1", 2, 'ssafy'), ("댓글 2-2", 2, 'ssafy123'), ("댓글 2-3", 2, 'taffy1234'), ("댓글 2-4", 2, 'test'), ("댓글 2-5", 2, 'zzafy'),
    ("댓글 2-6", 2, 'ssafy'), ("댓글 2-7", 2, 'ssafy123'), ("댓글 2-8", 2, 'taffy1234'), ("댓글 2-9", 2, 'test'), ("댓글 2-10", 2, 'zzafy'),
    ("댓글 3-1", 3, 'ssafy'), ("댓글 3-2", 3, 'ssafy123'), ("댓글 3-3", 3, 'taffy1234'), ("댓글 3-4", 3, 'test'), ("댓글 3-5", 3, 'zzafy'),
    ("댓글 3-6", 3, 'ssafy'), ("댓글 3-7", 3, 'ssafy123'), ("댓글 3-8", 3, 'taffy1234'), ("댓글 3-9", 3, 'test'), ("댓글 3-10", 3, 'zzafy');

INSERT INTO interest(`userId`, `content_id`) VALUES
	('ssafy', 125266), ('ssafy', 125407), ('ssafy', 125430), ('ssafy', 125565),
    ('ssafy123', 125266), ('ssafy123', 125576), ('ssafy123', 125577), ('ssafy123', 125578), ('ssafy123', 125579), 
    ('test', 125632), ('test', 125631), ('test', 125630), ('test', 125266), ('test', 125407);
    
CREATE TABLE `Snapshot`(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `userId` varchar(16) NOT NULL,
    `content_id` INT NOT NULL,
    `tag` VARCHAR(100),
    `start_date` TIMESTAMP,
    `end_date` TIMESTAMP,
    `registeredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    KEY `snapshot_and_members_fk` (`userId`),
                         CONSTRAINT `snapshot_and_members_fk` FOREIGN KEY (`userId`) REFERENCES `member` (`userId`),
    KEY `snapshot_and_attractionInfo_fk` (`content_id`),
                         CONSTRAINT `snapshot_and_attractionInfo_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`)
);

CREATE TABLE ATTRACTION_KAKAO(
     `content_id` INT AUTO_INCREMENT PRIMARY KEY,
     `title` VARCHAR(100),
     `addr1` VARCHAR(100), -- KAKAO API의 도로명 주소(road_address_name)
     `readcount` INT DEFAULT 0, 
     `latitude` DECIMAL(20,17), -- KAKAO API의 y좌표(위도)
     `longitude` DECIMAL(20,17), -- KAKAO API의 x좌표(경도)
     -- 카카오맵 API의 고유 정보
     `phone` VARCHAR(20),
     `category_group_code` VARCHAR(10),
     `category_group_name` VARCHAR(20),
     `category_name` VARCHAR(50),
     `place_url` VARCHAR(255)
);

CREATE TABLE snap_file (
    id INT PRIMARY KEY AUTO_INCREMENT,
    snap_id INT,
    original_filename VARCHAR(255),
    original_extension VARCHAR(50),
    stored_filename VARCHAR(255),
    store_path_prefix VARCHAR(255),
    size BIGINT,
    created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (snap_id) REFERENCES Snapshot(id)
);


select * from member;
select * from board_qna;
select * from reply;
select * from interest;
select * from snapshot;
select * from attraction_kakao;
select * from snap_file;
