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
                                                         ('ssafy', '최고의 여행지 추천 부탁드려요!', '여행 중 가장 강렬한 추억을 만들 수 있는 최고의 여행지는 무엇인가요? 혹시 추천해주실 수 있나요?'),
                                                         ('ssafy123', '여행계획 세우는 데 도움이 필요합니다.', '처음 여행 계획을 세우는데 도움이 필요해요. 혹시 어떻게 시작해야 할지 조언을 주실 수 있나요?'),
                                                         ('taffy1234', '로맨틱한 여행지 어디가 좋을까요?', '로맨틱한 여행을 계획 중인데, 어떤 장소가 로맨틱한 분위기를 제공하는지 알려주세요!'),
                                                         ('test', '가족과 함께 즐길 수 있는 여행지 추천 부탁드려요!', '가족과 함께 즐길 수 있는 여행지를 찾고 있어요. 어떤 장소가 가족 모두에게 적합한지 알려주세요!'),
                                                         ('zzafy', '여행 중에 꼭 가봐야 할 명소는 어디인가요?', '여행 중에 꼭 가봐야 할 명소가 있다면 알려주세요! 혹시 지금까지 여행 중에 가장 인상 깊었던 곳이 무엇인가요?'),
                                                         ('ssafy', '백패킹 여행 초보자를 위한 조언을 구합니다.', '백패킹 여행을 처음 떠나려고 하는데, 초보자를 위한 조언이 필요해요. 어떤 준비를 해야 할까요?'),
                                                         ('ssafy123', '식도락 여행 추천 부탁드려요!', '여행 중에 맛집이나 특별한 음식을 즐기고 싶어요. 혹시 식도락 여행 추천해주실 수 있나요?'),
                                                         ('taffy1234', '여행 중에 안전한 숙소 찾는 법이 궁금합니다.', '여행 중에 안전하면서 편안한 숙소를 찾는 방법이 궁금해요. 어떤 점을 주의해야 할까요?'),
                                                         ('test', '자연 속에서 힐링할 수 있는 여행지 어디가 좋을까요?', '자연 속에서 마음을 힐링하고 싶어요. 어떤 여행지가 자연의 아름다움으로 가득 차 있나요?'),
                                                         ('zzafy', '문화 체험을 즐길 수 있는 여행지 추천 부탁드립니다.', '여행 중에 현지 문화를 진짜로 느낄 수 있는 장소를 찾고 있어요. 어떤 여행지가 문화 체험에 좋은지 알려주세요!'),

                                                         ('ssafy', '여행 중에 즐길 만한 특별한 활동이 있을까요?', '여행 중에 특별하고 재미있는 활동을 찾고 있어요. 어떤 특별한 활동이 여행에 도움이 될까요?'),
                                                         ('ssafy123', '숨겨진 보석 같은 여행지 알려주세요!', '여행 중에 많이 알려지지 않은 숨겨진 보석 같은 여행지를 찾고 싶어요. 어떤 장소가 그런 특별함을 갖고 있나요?'),
                                                         ('taffy1234', '여행 중에 먹을 만한 로컬 음식 추천 부탁드려요.', '여행 중에 현지의 로컬 음식을 맛보고 싶어요. 어떤 로컬 음식이 꼭 먹어봐야 하는지 알려주세요!'),
                                                         ('test', '독특한 숙소를 찾는 데 도움이 필요합니다.', '여행 중에 독특하고 기억에 남는 숙소를 찾고 싶어요. 어떤 독특한 숙소를 추천해주실 수 있나요?'),
                                                         ('zzafy', '여행 중에 사진 찍기 좋은 명소 어디가 좋을까요?', '여행 중에 사진 찍기 좋은 명소를 찾고 있어요. 어떤 장소가 사진 찍기에 최적인지 알려주세요!'),
                                                         ('ssafy', '바다를 배경으로 하는 휴양지 추천 부탁드립니다.', '바다를 배경으로 한 휴양지를 찾고 있어요. 어떤 곳이 푸른 바다와 함께 휴식하기에 이상적인지 알려주세요!'),
                                                         ('ssafy123', '여행 중에 즐길 수 있는 액티비티가 궁금합니다.', '여행 중에 다양한 액티비티를 즐기고 싶어요. 어떤 액티비티가 여행에서 즐길만한지 알려주세요!'),
                                                         ('taffy1234', '가을 여행에 어울리는 목적지 추천 부탁드려요!', '가을 여행을 계획 중인데, 어떤 여행지가 가을 분위기에 딱 어울릴까요?'),
                                                         ('test', '여행 가방에 꼭 넣어야 할 필수품은 뭐가 있을까요?', '여행 가방에 넣을 필수품이 뭔지 알려주세요. 어떤 아이템이 여행 중에 유용할까요?'),
                                                         ('zzafy', '배낭 여행을 위한 꿀팁을 알려주세요!', '배낭 여행을 떠날 예정인데, 꿀팁이 필요해요. 어떤 팁이 있으면 알려주세요!'),

                                                         ('ssafy', '여행 중에 빠질 수 없는 여행 앱은 뭐가 있나요?', '여행 중에 유용한 여행 앱을 알고 싶어요. 어떤 앱을 사용하면 좋을지 추천해주세요!'),
                                                         ('ssafy123', '여행 중에 지루할 틈이 없는 노하우가 필요해요!', '여행 중에 지루함을 느끼지 않을 노하우가 궁금해요. 어떤 방법으로 즐거운 여행을 만들 수 있을까요?'),
                                                         ('taffy1234', '비올 때도 즐거운 여행지 추천 부탁드립니다.', '여행 중에 비가 올 때도 즐길 수 있는 여행지를 찾고 있어요. 어떤 장소가 비올 때도 매력적인지 알려주세요!'),
                                                         ('test', '힐링을 원하는데 어떤 여행지가 좋을까요?', '힐링을 원하면서 여행을 계획 중인데, 어떤 여행지가 정말 힐링에 적합한지 알려주세요!'),
                                                         ('zzafy', '여행 가기 전에 꼭 체크해야 할 사항은 뭐가 있을까요?', '여행을 떠나기 전에 꼭 체크해야 할 중요한 사항이 뭔지 알려주세요!'),
                                                         ('ssafy', '여행 중에 만난 인상적인 사람 이야기 나누고 싶어요!', '여행 중에 만난 인상적인 사람들에 대한 이야기를 듣고 싶어요. 여러분이 만난 특별한 경험을 나눠주세요!'),
                                                         ('ssafy123', '테마파크를 즐길 수 있는 여행지 추천 부탁드립니다.', '테마파크를 즐기기 좋은 여행지를 알려주세요. 어떤 장소가 테마파크가 풍부하게 있는지 알려주세요!'),
                                                         ('taffy1234', '여행 중에 피로를 푸는 방법이 궁금합니다.', '여행 중에 피로를 푸는 방법이 궁금해요. 어떤 휴식이 여행 중에 효과적일까요?'),
                                                         ('test', '여행 중에 좋은 순간을 기록하는 팁을 알려주세요!', '여행 중에 좋은 순간을 기록하는데 도움이 될만한 팁이 있을까요?'),
                                                         ('zzafy', '여행 중에 현지인과 소통하는 방법이 필요합니다.', '여행 중에 현지인과 소통하는 방법이 궁금해요. 어떻게 하면 현지 문화를 더 잘 이해하고 소통할 수 있을까요?');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
                                                        ("제주도의 성산일출봉은 한국에서 가장 아름다운 일출을 감상할 수 있는 명소 중 하나입니다. 제주의 자연과 아름다움을 경험하세요!", 1, 'ssafy'),
                                                        ("경주의 안압지와 석굴암은 한국의 역사와 자연이 어우러진 곳으로, 역사적인 숨결과 아름다운 풍경을 느낄 수 있습니다.", 1, 'ssafy123'),
                                                        ("서울의 경복궁과 인사동은 한국의 전통과 현대가 공존하는 곳으로, 역사적인 장소와 유니크한 상점들을 동시에 즐길 수 있어요.", 2, 'ssafy'),
                                                        ("한국의 전통음식 체험도 추천합니다. 인사동이나 전주 한옥마을에서 전통 음식을 맛보면서 한국 문화를 체험할 수 있어요.", 2, 'ssafy123'),
                                                        ("남해의 따뜻한 해변과 여수의 여명의 도시는 로맨틱한 분위기가 가득한 곳입니다. 산책하며 바다의 야경을 즐기세요.", 3, 'ssafy'),
                                                        ("경주 첨성대와 야경이 아름다운 안압지는 조용하고 로맨틱한 분위기가 느껴지는 여행지로 추천됩니다.", 3, 'ssafy123'),
                                                        ("에버랜드와 롯데월드는 한국에서 대표적인 가족 여행지로, 다양한 놀이시설과 쇼를 통해 가족끼리 즐거운 시간을 보낼 수 있습니다.", 4, 'taffy1234'),
                                                        ("부산 아쿠아리움은 바다의 다양한 생물을 가족과 함께 관찰하고 체험할 수 있는 장소로 추천됩니다.", 4, 'test'),
                                                        ("경복궁과 창경궁은 서울에서 꼭 가봐야 할 대표적인 궁궐로, 한국의 역사와 전통을 체험할 수 있는 곳입니다.", 5, 'zzafy'),
                                                        ("남산타워는 서울의 전경을 한 눈에 볼 수 있는 명소로, 낮과 밤 모두 아름다운 풍경을 즐길 수 있습니다.", 5, 'ssafy'),
                                                        ("서울의 홍대나 이태원은 다양한 문화와 식도락을 경험할 수 있는 장소로, 가벼운 배낭으로 자유롭게 둘러보기 좋아요.", 6, 'ssafy123'),
                                                        ("경주의 안압지와 불국사는 역사적인 관광 명소로, 가벼운 신발과 편한 옷으로 자연과 역사를 함께 즐길 수 있습니다.", 6, 'taffy1234');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
                                                        ("서울의 강남역이나 신사동은 다양한 레스토랑과 카페가 밀집된 곳으로, 한국의 현대적인 음식문화를 체험할 수 있어요.", 7, 'test'),
                                                        ("전주 한옥마을의 한정식과 전주 비빔밥은 전통적인 맛을 느낄 수 있는 대표적인 음식으로 추천됩니다.", 7, 'zzafy'),
                                                        ("서울의 강남구나 종로구는 안전하면서도 다양한 숙박 옵션이 많은 지역으로, 숙소 선택에 어려움이 없을 것입니다.", 8, 'taffy1234'),
                                                        ("여수의 해안가에 위치한 호텔들은 바다 전망과 함께 안전하고 아늑한 분위기를 제공해요.", 8, 'test'),
                                                        ("강원도의 속초나 양양은 자연이 풍부한 곳으로, 해안을 따라 걷거나 소박한 펜션에서 힐링을 즐길 수 있습니다.", 9, 'zzafy'),
                                                        ("제주의 오름과 해안은 신선한 공기를 마시며 힐링하기에 최적인 자연 속 여행지로 추천됩니다.", 9, 'ssafy'),
                                                        ("경주의 경복당과 첨성대에서는 한국의 전통 의상을 입어보거나 전통놀이를 체험할 수 있어요.", 10, 'ssafy123');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
                                                        ("서울의 국립민속박물관이나 남산골한옥마을에서 한국의 전통문화와 민속을 체험할 수 있는 행사가 자주 열려요.", 11, 'taffy1234'),
                                                        ("제주의 성산일출봉에서 일출 트래킹은 특별한 순간을 즐길 수 있는 활동 중 하나입니다.", 12, 'test'),
                                                        ("남해의 몽돌해변에서는 조개잡이 체험이나 해안가 걷기 등 특별한 활동을 즐길 수 있어요.", 12, 'zzafy'),
                                                        ("강원도의 인제는 아름다운 자연경관과 함께 조용한 분위기로, 숨겨진 보석 같은 여행지로 알려져 있습니다.", 13, 'taffy1234');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
                                                        ("경기도의 파주에 위치한 안성목장은 평화로운 목장과 풍경이 아름다운 힐링 여행지로 손색이 없어요.", 13, 'test'),
                                                        ("대한민국의 김치찌개와 삼겹살은 지역마다 특색이 다르게 맛있어요. 강원도에서는 감자김치찌개를 즐겨보세요.", 14, 'zzafy'),
                                                        ("부산의 찜질방에서는 해물을 사용한 맛있는 해물찜을 맛볼 수 있어요.", 14, 'ssafy');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
    ("제주도의 돌담집 펜션은 독특한 디자인과 제주의 풍경을 동시에 즐길 수 있는 숙소로 유명합니다.", 15, 'test');

INSERT INTO reply(`comment`, `articleNo`, `userId`) VALUES
                                                        ("경주의 첨성대와 안압지는 역사적인 분위기와 아름다운 풍경이 어우러져 사진 찍기 좋은 명소로 손색이 없습니다.", 16, 'zzafy'),
                                                        ("서울의 남산공원은 도시의 전경과 타워를 배경으로 멋진 사진을 찍을 수 있는 명소 중 하나입니다.", 16, 'taffy1234'),
                                                        ("부산의 해운대는 길게 이어진 백사장과 여러 해변에서 휴양을 즐길 수 있는 대표적인 해변 지역 중 하나입니다.", 17, 'test'),
                                                        ("제주도의 성산일출봉과 주변 해안은 푸른 바다를 배경으로 휴양하기에 이상적인 곳입니다.", 18, 'zzafy'),
                                                        ("강원도의 양양에서는 서핑 체험이나 해안가에서의 바다 낚시 등 다양한 액티비티를 즐길 수 있습니다.", 18, 'ssafy'),
                                                        ("제주도의 성산일출봉에서는 일출 트래킹이나 제주 조복 만들기와 같은 다양한 활동이 가능합니다.", 19, 'ssafy123'),
                                                        ("강원도의 낙산해변과 단풍이 아름다운 오대산은 가을 여행에 어울리는 목적지로 추천됩니다.", 20, 'taffy1234'),
                                                        ("경주의 불국사와 대릉원은 가을 단풍이 아름다운 역사적인 명소로, 한적한 분위기에서 가을을 즐기기에 좋아요.", 20, 'test'),
                                                        ("여행용 충전기와 어댑터는 꼭 필요한 아이템으로, 전자기기를 사용하거나 충전해야 할 때 유용합니다.", 21, 'zzafy'),
                                                        ("편한 신발과 가벼운 외투는 긴 여행 동안 편안함을 유지하는 데 도움이 되는 필수품입니다.", 21, 'taffy1234'),
                                                        ("가볍고 튼튼한 배낭을 선택하세요. 불필요한 무게를 줄이고 필요한 물품만을 담아 다니면 편리합니다.", 22, 'test'),
                                                        ("현지인과 소통하는 데 도움이 될 수 있는 간단한 한국어 문장을 배우세요. 친절한 인사와 감사의 표현은 현지인들과의 소통을 원활하게 만듭니다.", 22, 'zzafy'),
                                                        ("서울의 홍대나 강남은 쇼핑, 먹거리, 예술 등 다양한 활동이 모여있어, 지루할 틈이 없는 도시 여행을 즐길 수 있습니다.", 23, 'ssafy'),
                                                        ("경주의 테마 공원과 문화 축제는 다채로운 활동으로 여행 일정을 풍성하게 채울 수 있는 노하우를 제공합니다.", 23, 'ssafy123'),
                                                        ("서울의 인사동과 명동은 지하 상가가 잘 발달되어 있어 비가 올 때도 쇼핑과 먹거리를 즐길 수 있는 안전한 장소입니다.", 24, 'taffy1234'),
                                                        ("전남의 완도는 비가 올 때에도 아름다운 바다 풍경을 감상할 수 있는 여행지로 추천됩니다.", 24, 'test'),
                                                        ("강원도의 정동진과 속초는 푸른 바다와 해안선이 조화로운 자연 풍경으로, 힐링을 원하는 이들에게 안성맞춤인 목적지입니다.", 25, 'zzafy'),
                                                        ("경주의 불국사와 대릉원은 조용한 분위기와 역사적 아름다움으로 힐링을 추구하는 여행객에게 좋은 선택지입니다.", 25, 'taffy1234'),
                                                        ("날씨와 옷차림을 미리 확인하고 적절한 준비를 하세요. 갑작스러운 날씨 변화에 대비하는 것이 중요합니다.", 26, 'test'),
                                                        ("여행지의 문화와 풍습에 대해 사전 조사하여 현지에서의 예의를 지키는 데 도움이 됩니다.", 27, 'zzafy'),
                                                        ("서울의 한복체험장에서 만난 선생님은 한국의 전통을 사랑하는 열정적인 분이었어요. 그녀의 이야기는 여행에 새로운 의미를 불어넣었습니다.", 27, 'ssafy'),
                                                        ("부산에서 만난 어부 할아버지는 지혜롭고 따뜻한 이야기로 여행에 감동을 더해주었습니다.", 28, 'ssafy123'),
                                                        ("에버랜드는 서울 근교에 위치한 대표적인 테마파크로 다양한 놀이기구와 쇼가 여행객을 기다리고 있습니다.", 28, 'taffy1234'),
                                                        ("대전의 대명더블유월드는 물놀이와 테마파크가 결합된 곳으로, 가족이나 친구와 함께 즐길 수 있는 장소입니다.", 29, 'test'),
                                                        ("강원도의 영월은 자연이 어우러진 휴양지로, 산책이나 힐링 체험을 통해 여행 중 피로를 풀 수 있는 좋은 방법을 제공합니다.", 29, 'zzafy'),
                                                        ("경주의 체험농장에서는 손으로 직접 농사를 짓는 등 농촌 체험을 통해 독특한 경험을 즐길 수 있습니다.", 30, 'taffy1234'),
                                                        ("전남의 완도에서는 해초 수확 체험이나 세마리 아이랑 바다에서 오징어 잡이를 하는 등 독특한 체험 활동을 즐길 수 있어요.", 30, 'test');


INSERT INTO interest(`userId`, `content_id`) VALUES
                                                 ('ssafy', 125266), ('ssafy', 125407), ('ssafy', 125430), ('ssafy', 125565),
                                                 ('ssafy123', 125266), ('ssafy123', 125576), ('ssafy123', 125577), ('ssafy123', 125578), ('ssafy123', 125579),
                                                 ('test', 125632), ('test', 125631), ('test', 125630), ('test', 125266), ('test', 125407);

CREATE TABLE `Snapshot`(
                           `id` INT AUTO_INCREMENT PRIMARY KEY,
                           `userId` varchar(16) NOT NULL,
                           `content_id` INT NOT NULL,
                           `content` VARCHAR(300),
                           `tag` VARCHAR(100),
                           `start_date` TIMESTAMP,
                           `end_date` TIMESTAMP,
                           `publishedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
                           stored_path_prefix VARCHAR(255),
                           size BIGINT,
                           created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (snap_id) REFERENCES `Snapshot`(id)
);


select * from member;
select * from board_qna;
select * from reply;
select * from interest;
select * from snapshot;
select * from attraction_kakao;
select * from snap_file;

desc attraction_info;
desc attraction_description;

-- 연습용 SQL
SELECT *
FROM snapshot as s
         INNER JOIN member as m
                    ON s.userId = m.userId
         INNER JOIN Attraction_Info as a
                    ON a.content_Id = s.content_id;

select *
from attraction_info
where addr1 LIKE '%서울%';

SELECT *
FROM attraction_info
WHERE addr1 LIKE CONCAT('%', REPLACE('서울 송파구 올림픽로 240', ' ', '%'), '%');

SELECT *
FROM attraction_info
WHERE addr1 LIKE CONCAT('%', REPLACE('서울 송파구 올림픽로 240', ' ', '%'), '%') AND title LIKE CONCAT('%', '롯데월드', '%')
    LIMIT 1;

SELECT *
FROM attraction_info
WHERE addr1 LIKE CONCAT('%', REPLACE('서울 종로구 청와대로 1', ' ', '%'), '%');

SELECT *
FROM snap_file;


SELECT *
FROM snap_file WHERE id IN (
    SELECT MIN(id) FROM snap_file GROUP BY snap_id
);

SELECT *
FROM snap_file WHERE snap_id = 16 AND id IN (
    SELECT MIN(id) FROM snap_file GROUP BY snap_id
);

SELECT *
FROM snap_file WHERE id IN (
    SELECT MIN(id) FROM snap_file GROUP BY snap_id
) AND snap_id = 16;

-- INSERT INTO `snapshot`(userId, content_id, content, tag, start_date, end_date)
-- 			VALUES ('ssafy', 126512, '즐거운 여행', '#행복 #즐거움 #기쁨', '2023-11-23', '2023-11-23');
-- INSERT INTO SNAP_FILE(snap_id, original_filename, original_extension, stored_filename, stored_path_prefix, size)
-- VALUES (1, '뀨뀨', '뀨뀨', '뀨뀨', '뀨뀨', 21);


