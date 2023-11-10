USE `enjoytrip_doublejj`;

DROP TABLE IF EXISTS `HOTPLACE`;
DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
                          `userId` varchar(16) NOT NULL,
                          `userName` varchar(20) NOT NULL,
                          `userPass` varchar(100) NOT NULL,
                          `userEmail` varchar(50) NOT NULL,
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

INSERT INTO `member` VALUES ('aasfy444','유현지','$2a$10$SeUCKp2Jq6FgwL6dieUmc.xE7pMBlX/I1deUpJNwny58vC3rtuFPq','ccamy@c.com','2023-10-06 07:43:06'),
                            ('ssafy123','최준호','$2a$10$6/Z/NgVi2c5vIGkbhve6xOJuUYvxInw4YGo5SXZDXAaqKj8DnWe9u','ajchoi0928@gmail.com','2023-10-06 07:42:42'),
                            ('taffy1234','김싸피','$2a$10$fQxtIPaG1aWQHMMUAdAUXO7z6M87rU39UD64L4qMwInHJhUFBem52','ssafy@ssafy.com','2023-10-06 07:43:28'),
                            ('test','테스트','$2a$10$tli5kNFPlkuxFxHcOTlLkusAJZXUBeVhoeMi0n.rM3snQkIze/vny','test@test.com','2023-10-06 07:44:11'),
                            ('zzafy','싸피','$2a$10$tzHJA7.kjQonCBSsDC0/0O/pOsD/gV71DeTAkcg8nl.9S8Ud3EFSW','ssafy@ssafy.com','2023-10-06 07:43:47');

INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('taffy1234', 'TEST 투어 제안서', '테스트 테스트', 5, '2023-10-01T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('taffy1234', 'TEST 투어 제안서2', '테스트 테스트', 10, '2023-10-02T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('test', 'TEST 투어 제안서3', '테스트 테스트', 15, '2023-10-03T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('test', 'TEST 투어 제안서4', '테스트 테스트', 20, '2023-10-04T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('aasfy444', 'TEST 투어 제안서5', '테스트 테스트', 25, '2023-10-05T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('aasfy444', 'TEST 투어 제안서6', '테스트 테스트', 25, '2023-10-05T02:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('zzafy', 'TEST 투어 제안서7', '테스트 테스트', 25, '2023-10-05T05:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('ssafy123', 'TEST 투어 제안서8', '테스트 테스트', 25, '2023-10-05T06:41:05');
INSERT INTO BOARD(`userId`, `title`, `content`, `hit`, `publishedDate`) VALUES ('ssafy123', 'TEST 투어 제안서9', '테스트 테스트', 25, '2023-10-05T07:41:05');

-- #1110 #Q&A 게시판 테이블 추가
drop table if exists reply;
drop table if exists board_qna;


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

INSERT INTO board_qna(`userId`,  `title`, `content`) VALUES('ssafy123', '해당 여행지 어떻게 가나요', 'content');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES 
	("정말 좋았어요", 1, 'taffy1234'),
	("정말 좋았어요2", 1, 'test'),
    ("근데 배고파요", 1, 'test');

select * from member;
select * from board_qna;
select * from reply;