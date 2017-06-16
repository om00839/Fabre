BEGIN TRANSACTION;
CREATE TABLE user(u_email char(100) primary key, u_nickname char(100) not null,u_password char(100) not null);
INSERT INTO `user` VALUES ('sparrow_a1@naver.com','Liver','123123');
INSERT INTO `user` VALUES ('om00839@naver.com','JaeHoJang','123123');
INSERT INTO `user` VALUES ('asdf@naver.com','456','456');
INSERT INTO `user` VALUES ('asdfg@naver.com','sdafasdf','123');
INSERT INTO `user` VALUES ('cascascascasd@naver.com','12das','123123');
INSERT INTO `user` VALUES ('sparrow_a21312312311@naver.com','123123','111111');
INSERT INTO `user` VALUES ('qqqq1234@naver.com','qqqq1234','12345');
CREATE TABLE uc_setting ( u_email char(100), c_id char(100), uc_favorite boolean, uc_keywords char(100));
INSERT INTO `uc_setting` VALUES ('sparrow_a1@naver.com','1',NULL,NULL);
INSERT INTO `uc_setting` VALUES ('sparrow_a1@naver.com','2',NULL,NULL);
INSERT INTO `uc_setting` VALUES ('qqqq1234@naver.com','2',NULL,NULL);
CREATE TABLE crawler(c_id char(100) primary key, c_url char(100) not null,c_name char(100) not null);
INSERT INTO `crawler` VALUES ('1','https://recruit.imbc.com/company/recruit/rnotice/list.aspx','MBC');
INSERT INTO `crawler` VALUES ('2','http://www.yonsei.ac.kr/sc/support/notice.jsp','yonsei');
CREATE TABLE article(a_id char(100) primary key, c_id char(100) references crawler(c_id),a_url char(100) not null,a_title char(100) not null,a_date char(100) not null);
INSERT INTO `article` VALUES ('1','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152783&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','2017-2학기 전공신청 및 전공진입 확인 기간 안내','2017.06.01');
INSERT INTO `article` VALUES ('2','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152783&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','2017-2학기 전공신청 및 전공진입 확인 기간 안내','2017.06.01');
INSERT INTO `article` VALUES ('3','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152783&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','2017-2학기 전공신청 및 전공진입 확인 기간 안내','2017.06.01');
INSERT INTO `article` VALUES ('4','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152691&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','2017년도 여름 계절제수업 등록안내','2017.05.29');
INSERT INTO `article` VALUES ('5','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152673&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','2017학년도 제1학기 신촌캠퍼스 공통기초, 필수/선택교양 기말시험 시간표','2017.05.26');
INSERT INTO `article` VALUES ('6','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=151493&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','신촌&#40;국제&#41; 캠퍼스 2017 여름 계절제 수업 안내','2017.03.29');
INSERT INTO `article` VALUES ('7','2','http://www.yonsei.ac.kr/sc/support/notice.jsp?mode=view&article_no=152832&board_wrapper=%2Fsc%2Fsupport%2Fnotice.jsp&pager.offset=0&board_no=15','유무선 네트워크 서비스 중지 안내&#40;언더우드관 외 4개 건물&#41;','2017.06.05');
INSERT INTO `article` VALUES ('8','1','https://recruit.imbc.com/company/recruit/rnotice/view.aspx?RID=175','예능PD 사원 공개채용','2017.05.10');
INSERT INTO `article` VALUES ('9','1','https://recruit.imbc.com/company/recruit/rnotice/view.aspx?RID=174','2017 MBC 부문별 사원 공개채용','2017.02.18');
INSERT INTO `article` VALUES ('11','1','https://recruit.imbc.com/company/recruit/rnotice/view.aspx?RID=173','MBC 드라마 조연출(계약직) 모집','2017.02.18');
CREATE VIEW display as select u_email,c_url, c_name, S.c_id,a_title,a_id,a_url,a_date, uc_favorite, uc_keywords from UC_Setting S inner Join article A on S.c_id=A.c_id inner Join crawler C on s.c_id=C.c_id;
COMMIT;
