INSERT INTO com_code (CLASSNO, CODECD, CODENM) VALUES
	(2, '1', '임시저장'),
	(2, '2', '결재중'),
    (2, '3', '완료'),
    (2, '4', '반려'),
    (2, '5', '합의거부'),
    (3, 'Y', '결재'),
    (3, 'N', '미결재'),
    (4, '1', '결재'),
    (4, '2', '합의');
       
-- DROP TABLE SGN_SIGN;

CREATE TABLE SGN_SIGN ( 
	SGNNO INT(11) NOT NULL AUTO_INCREMENT,		-- 결재번호
	SGNFLAG CHAR(1),							-- 결재여부
	SGNDATE DATETIME,							-- 결재일자
	SGNKIND CHAR(1),							-- 결재종류
	SDNO INT(11),								-- 결재문서번호
	USERNO INT,									-- 사용자번호
    DELETEFLAG CHAR(1),							-- 삭제여부
	PRIMARY KEY (SGNNO)
) ;

-- DROP TABLE SGN_LINE;

CREATE TABLE SGN_LINE (
	SLNO INT(11) NOT NULL AUTO_INCREMENT,		-- 결재선번호
	SLTITLE VARCHAR(255),						-- 결재선제목
	SLORDER INT,								-- 결재선순서
	SLKIND CHAR(1),								-- 결재선종류
	DELETEFLAG CHAR(1),							-- 삭제여부
	USERNO INT,									-- 사용자번호
	PRIMARY KEY (SLNO)	
) ;

-- DROP TABLE SGN_DOC

CREATE TABLE SGN_DOC (
	SDNO INT(11) NOT NULL AUTO_INCREMENT,		-- 결재문서번호
	SDTITLE VARCHAR(255),						-- 결재문서제목
	SDCONTENTS VARCHAR(4000),					-- 결재문서내용
	SDDATE DATETIME,							-- 결재문서기안일
	SDMODDATE DATETIME,							-- 결재문서수정일
	DELETEFLAG CHAR(1),							-- 삭제여부
	SDSTATE CHAR(1),							-- 결재문서상태
	USERNO INT,									-- 사용자번호
	FRMNO INT(11),								-- 양식번호
	PRIMARY KEY (SDNO)
) ;

-- DROP TABLE SGN_FORM

CREATE TABLE SGN_FORM (
	FRMNO INT(11) NOT NULL AUTO_INCREMENT,		-- 양식번호
	FRMNM VARCHAR(200),							-- 양식이름
	FRMCONTENTS VARCHAR(4000),					-- 양식내용
    DELETEFLAG CHAR(1),							-- 삭제여부
	PRIMARY KEY (FRMNO)
) ;

-- DROP TABLE SGN_FILE

CREATE TABLE SGN_FILE (
	FILENO INT(11) NOT NULL AUTO_INCREMENT,		-- 파일번호
	FILENM VARCHAR(100),						-- 파일이름
	REALFILENM VARCHAR(30),						-- 실제파일이름
	FILESIZE INT,								-- 파일크기
	SDNO INT(11),								-- 결재문서번호
	PRIMARY KEY (FILENO)
) ;

-- DROP TABLE SGN_REFERENCE

CREATE TABLE SGN_REFERENCE (
	REFNO INT(11) NOT NULL AUTO_INCREMENT,		-- 참조번호
	REFDATE DATETIME,							-- 참조일
    DELETEFLAG CHAR(1),							-- 삭제여부
	SDNO INT(11),								-- 결재문서번호
	USERNO INT(11),								-- 사용자번호    
	PRIMARY KEY (REFNO)
) ;