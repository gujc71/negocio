-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.1.13-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 negocio.com_code 구조 내보내기
CREATE TABLE IF NOT EXISTS `com_code` (
  `CLASSNO` int(11) NOT NULL,
  `CODECD` varchar(10) NOT NULL,
  `CODENM` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CLASSNO`,`CODECD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.com_code:~12 rows (대략적) 내보내기
/*!40000 ALTER TABLE `com_code` DISABLE KEYS */;
INSERT INTO `com_code` (`CLASSNO`, `CODECD`, `CODENM`) VALUES
	(1, 'A', '관리자'),
	(1, 'U', '사용자'),
	(2, '1', '임시저장'),
	(2, '2', '결재중'),
	(2, '3', '완료'),
	(2, '4', '반려'),
	(2, '5', '합의거부'),
	(3, 'N', '미결재'),
	(3, 'Y', '결재'),
	(4, '1', '결재'),
	(4, '2', '합의'),
	(5, '1', 'test');
/*!40000 ALTER TABLE `com_code` ENABLE KEYS */;

-- 테이블 negocio.sgn_doc 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_doc` (
  `SDNO` int(11) NOT NULL AUTO_INCREMENT,
  `SDTITLE` varchar(255) DEFAULT NULL,
  `SDCONTENTS` varchar(4000) DEFAULT NULL,
  `SDDATE` datetime DEFAULT NULL,
  `SDMODDATE` datetime DEFAULT NULL,
  `DELETEFLAG` char(1) DEFAULT NULL,
  `SDSTATE` char(1) DEFAULT NULL,
  `USERNO` int(11) DEFAULT NULL,
  `FRMNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`SDNO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_doc:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_doc` DISABLE KEYS */;
INSERT INTO `sgn_doc` (`SDNO`, `SDTITLE`, `SDCONTENTS`, `SDDATE`, `SDMODDATE`, `DELETEFLAG`, `SDSTATE`, `USERNO`, `FRMNO`) VALUES
	(1, '결재부탁드립니다', NULL, '2016-11-20 17:12:49', NULL, 'N', 'N', NULL, 1),
	(2, '휴가다녀오겠습니다', NULL, '2016-11-20 17:12:49', NULL, 'N', 'N', NULL, 4);
/*!40000 ALTER TABLE `sgn_doc` ENABLE KEYS */;

-- 테이블 negocio.sgn_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_file` (
  `FILENO` int(11) NOT NULL AUTO_INCREMENT,
  `FILENM` varchar(100) DEFAULT NULL,
  `REALFILENM` varchar(30) DEFAULT NULL,
  `FILESIZE` int(11) DEFAULT NULL,
  `SDNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`FILENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_file:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `sgn_file` ENABLE KEYS */;

-- 테이블 negocio.sgn_form 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_form` (
  `FRMNO` int(11) NOT NULL AUTO_INCREMENT,
  `FRMNAME` varchar(1000) DEFAULT NULL,
  `FRMDESC` varchar(100) DEFAULT NULL,
  `DELETEFLAG` char(1) DEFAULT NULL,
  `FRMCONTENTS` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`FRMNO`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_form:~17 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_form` DISABLE KEYS */;
INSERT INTO `sgn_form` (`FRMNO`, `FRMNAME`, `FRMDESC`, `DELETEFLAG`, `FRMCONTENTS`) VALUES
	(1, '업무일지', '업무일지를 작성하는 폼 입니다.', 'N', NULL),
	(2, '출장외근보고서', '출장외근보고서를 작성하는 폼 입니다.', 'N', NULL),
	(3, '물품구입품의서', '물품구입품의서를 작성하는 폼 입니다.', 'N', NULL),
	(4, '휴가', '휴가 신청 폼 입니다.', 'N', NULL),
	(7, 'ddd', 'ddd', 'Y', '<p>ddd</p>\r\n'),
	(8, '모르겠똬 ~ ', '설명을 해보자', 'Y', '<p>내용은 읍따&nbsp;</p>\r\n'),
	(9, NULL, NULL, 'Y', NULL),
	(10, NULL, NULL, 'Y', NULL),
	(11, '2017년 테스트', '수정 테스트 중입니다.', 'Y', '<p><strong>vvvvvvv가가가가가</strong></p>\r\n'),
	(12, 'test2', 'it is not my only test', 'N', '<p>bbbbbbbb</p>\r\n'),
	(13, 'test3', 'It is the modified test ', 'N', '<p>ccccccccccccccccc</p>\r\n'),
	(14, 'test4', 'It is the modified test 입니다', 'N', '<p>드디어 (?) 별로 한것은 없지만 눈을 붙일 수 있겠구나..</p>\r\n'),
	(15, '수정', 'ㅅㅅㅅㅅㅅㅅㅅ', 'N', '<p>ㅋㅋㅋㅋㅋㅋ</p>\r\n'),
	(16, '수정 테스트', '수정 설명', 'N', '<p>수정내용...</p>\r\n'),
	(17, 'the copy test', 'after copy things ', 'N', '<p>test</p>\r\n'),
	(18, '2017년 글 쓰기 테스트 입니다.', '2017년 1월8일', 'N', '<p>내용무</p>\r\n'),
	(19, '복사 후 테스트', 'first test after the copy things', 'N', '<p>aaa</p>\r\n');
/*!40000 ALTER TABLE `sgn_form` ENABLE KEYS */;

-- 테이블 negocio.sgn_line 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_line` (
  `SLNO` int(11) NOT NULL AUTO_INCREMENT,
  `SLTITLE` varchar(255) DEFAULT NULL,
  `SLORDER` int(11) DEFAULT NULL,
  `SLKIND` char(1) DEFAULT NULL,
  `DELETEFLAG` char(1) DEFAULT NULL,
  `USERNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`SLNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_line:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `sgn_line` ENABLE KEYS */;

-- 테이블 negocio.sgn_reference 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_reference` (
  `REFNO` int(11) NOT NULL AUTO_INCREMENT,
  `REFDATE` datetime DEFAULT NULL,
  `DELETEFLAG` char(1) DEFAULT NULL,
  `SDNO` int(11) DEFAULT NULL,
  `USERNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`REFNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_reference:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_reference` DISABLE KEYS */;
/*!40000 ALTER TABLE `sgn_reference` ENABLE KEYS */;

-- 테이블 negocio.sgn_sign 구조 내보내기
CREATE TABLE IF NOT EXISTS `sgn_sign` (
  `SGNNO` int(11) NOT NULL AUTO_INCREMENT,
  `SGNFLAG` char(1) DEFAULT NULL,
  `SGNDATE` datetime DEFAULT NULL,
  `SGNKIND` char(1) DEFAULT NULL,
  `SDNO` int(11) DEFAULT NULL,
  `USERNO` int(11) DEFAULT NULL,
  `DELETEFLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`SGNNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 negocio.sgn_sign:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `sgn_sign` DISABLE KEYS */;
/*!40000 ALTER TABLE `sgn_sign` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
