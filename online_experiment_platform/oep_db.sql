/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 8.0.29 : Database - online_experiment_platform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`online_experiment_platform` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `online_experiment_platform`;

/*Table structure for table `assign_submit` */

DROP TABLE IF EXISTS `assign_submit`;

CREATE TABLE `assign_submit` (
  `assign_submit_id` bigint NOT NULL AUTO_INCREMENT,
  `assign_id` bigint DEFAULT NULL,
  `uuid` bigint DEFAULT NULL,
  `content` blob,
  `score` float DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`assign_submit_id`),
  KEY `fk_assign_submit_assign_id` (`assign_id`),
  KEY `fk_assign_submit_uuid` (`uuid`),
  CONSTRAINT `fk_assign_submit_assign_id` FOREIGN KEY (`assign_id`) REFERENCES `assignment` (`assign_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_assign_submit_uuid` FOREIGN KEY (`uuid`) REFERENCES `user_info` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `assign_submit` */

/*Table structure for table `assignment` */

DROP TABLE IF EXISTS `assignment`;

CREATE TABLE `assignment` (
  `assign_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ä½œä¸šid',
  `assign_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ä½œä¸šåç§°',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'å¼€å§‹æ—¶é—´',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ç»“æŸæ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  `course_id` bigint DEFAULT NULL COMMENT 'è¯¾ç¨‹id',
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æ ‡é¢˜',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æè¿°',
  `content` blob,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  KEY `assign_id` (`assign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ä½œä¸š;';

/*Data for the table `assignment` */

insert  into `assignment`(`assign_id`,`assign_name`,`start_time`,`end_time`,`create_time`,`last_modified_time`,`course_id`,`title`,`description`,`content`,`filename`) values (1,'assignment1','2022-04-01 00:00:00','2022-04-30 23:59:59','2023-03-17 12:12:49','2023-03-17 12:12:49',1,NULL,'This is assignment1.','PK\0\0\0\0£/ViQíšþ\0\0\0\0\0\0\0experiment3.1.txt}UÁnÛ0½È?ø2TÆÍI×a+ Ó¶îÒ¶[ŠM\'BeÉd,Ù×’,Çi±h%‘ï‘2éÈ~0ÖÞØæ¸^ÉtÒc?œá\n=Ì¶ˆ`ZG³žÍ³u£n¼4Z¨\0yX¯:kúì½TŽµÂ‹\"~ÁƒOÃúhD–þíŒ{V ¬Ž±hu9¾ÏPï•Ù»9—^øA¯äž\rç°‰\n™&.Îo3•‡£×à\\pvZxaà¾ \'ºv¢8¾©ªŠêºáG‹ç-m@{°Ž¿§\Znkç[þT±[Z±íô·ÙQ+tkzt\nüc¹^aRÌ5Âc	ÒO÷´ÚÑi‡\rO©Ð^Øg°üÆÜä¨£ùMÊœ2O·Š«ãK‹l%kÆVTÄ„ÌŸ 	œìAáÓ}#.©½fKö_ÆÅåç£Ñ‡¥R×\'žŠúTU»l;Oñ³ßJ¢å>›fÜdÌñS\'ð«n!“4Ê‰äà(I“Ìœ¬Š]È/\rIrô•0Ýßk\'ÿ\0¿ÝRw»NÿiGÈªÿaZ$µ$Ú|xI´^5J8Wô¦EpÀ¾›vTPÞ¯W>-tE]K-}]ªËŽð¸q@Í’Í€ráC,;Ê¶½áHû(5ÙÒ»— 3úàŽÞÆ¬²xgìoaÛ¨MOKõ_jS¹t=0j|aÌrWf8gº8QW—.Ê8Ww:äöÙâþ«öÖçGÜ·r\'ãNöìÇ·/ãÙ ¬è!,)©²¼bÕ&V„•0˜æXH]à¬€„YÏ%·¤éÛ¥ÀÉ\0‹ƒL–MsU>=Çf¾X-Ç®ÊUð©ô<ßox0yö¬©V´äE Û‹æ9^y_¡Ì<²#±°7XçÕ2ËÁJíIàŠèø5t¼ÂÛ}ðÇ°ûgá—ÏŒ×5¿ªwbOËÛùË;•ýTí&Ü¤–W¸òi³Ë!ùs¹<¥WÞ‹SDÇßº	!ôæ—¾‰E&`Ê‚&šNïÒ	QPK\0\0\0\0\0£/ViQíšþ\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\0\0\0experiment3.1.txtPK\0\0\0\0\0\0?\0\0\0-\0\0\0\0','test1.zip'),(2,'assignment2','2022-04-01 00:00:00','2022-04-30 23:59:59','2023-03-17 12:15:02','2023-03-17 12:15:02',1,NULL,'This is assignment2.','This is a test content',NULL);

/*Table structure for table `code_submission` */

DROP TABLE IF EXISTS `code_submission`;

CREATE TABLE `code_submission` (
  `submission_id` bigint NOT NULL AUTO_INCREMENT,
  `source_code` text COLLATE utf8mb4_general_ci,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `problem_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `language` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code_length` int DEFAULT NULL,
  `submit_num` int DEFAULT NULL,
  `status` int DEFAULT '0',
  PRIMARY KEY (`submission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `code_submission` */

insert  into `code_submission`(`submission_id`,`source_code`,`create_time`,`last_modified_time`,`problem_id`,`user_id`,`language`,`code_length`,`submit_num`,`status`) values (9,'#include <iostream>\nusing namespace std;\nint main(){\nint a,b;\ncin>>a>>b;\ncout<<a+b<<endl;\nreturn 0;\n}','2023-06-04 12:58:14',NULL,1,1,'c++',101,1,0);

/*Table structure for table `code_template` */

DROP TABLE IF EXISTS `code_template`;

CREATE TABLE `code_template` (
  `cid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'æ¨¡æ¿id',
  `lid` int NOT NULL COMMENT 'è¯­è¨€id',
  `problem_id` int NOT NULL COMMENT 'é¢˜ç›®id',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ä»£ç æ¨¡æ¿',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ä»£ç æ¨¡æ¿;';

/*Data for the table `code_template` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT 'è¯¾ç¨‹id',
  `course_name` varchar(90) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'å§“å',
  `create_time` datetime DEFAULT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `start_time` datetime DEFAULT NULL COMMENT 'è¯¾ç¨‹å¼€å§‹æ—¶é—´',
  `end_time` datetime DEFAULT NULL COMMENT 'è¯¾ç¨‹ç»“æŸæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  `teacher_id1` int DEFAULT NULL COMMENT 'æ•™å¸ˆid',
  `teacher_id2` int DEFAULT NULL COMMENT 'æ•™å¸ˆid',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='è¯¾ç¨‹;';

/*Data for the table `course` */

insert  into `course`(`course_id`,`course_name`,`create_time`,`start_time`,`end_time`,`last_modified_time`,`teacher_id1`,`teacher_id2`) values (1,'é«˜è½¯','2023-03-17 10:18:09',NULL,NULL,'2023-03-17 10:18:08',123456,NULL),(2,'é«˜è½¯2','2023-03-17 10:35:54',NULL,NULL,'2023-03-17 10:35:54',123456,NULL);

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `file_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'æ–‡ä»¶',
  `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æ–‡ä»¶è·¯å¾„',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'å¼€å§‹æ—¶é—´',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ç»“æŸæ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='æ–‡ä»¶;';

/*Data for the table `file` */

/*Table structure for table `language` */

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `lid` int NOT NULL COMMENT 'ä¸»é”®id',
  `content_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'è¯­è¨€å¯¹åº”çš„ä»£ç æ¨¡æ¿',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'è¯­è¨€åå­—',
  `compile_command` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ç¼–è¯‘æŒ‡ä»¤',
  `code_template` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'è¯­è¨€å¯¹åº”çš„ä»£ç æ¨¡æ¿',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ç¼–ç¨‹è¯­è¨€;';

/*Data for the table `language` */

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `problem_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `time_limit` int DEFAULT NULL,
  `memory_limit` int DEFAULT NULL,
  `difficulty` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sample_input` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sample_output` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test_point_num` int DEFAULT '0',
  `test_point_input` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `test_point_output` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `tips` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `problem` */

insert  into `problem`(`problem_id`,`title`,`description`,`time_limit`,`memory_limit`,`difficulty`,`sample_input`,`sample_output`,`test_point_num`,`test_point_input`,`test_point_output`,`tips`,`create_time`,`last_modified_time`,`status`) values (1,'ä¸¤æ•°ä¹‹å’Œ','è¿”å›žä¸¤ä¸ªæ•°çš„å’Œ',9999999,9999999,'ç®€å•','è¾“å…¥ 10 10','20',8,'1 1,2 2','2,4','è¿™çœŸæ˜¯å¤ªç®€å•äº†',NULL,NULL,0),(2,'æœ€é•¿å…¬å…±å‰ç¼€','ç¼–å†™ä¸€ä¸ªå‡½æ•°æ¥æŸ¥æ‰¾å­—ç¬¦ä¸²æ•°ç»„ä¸­çš„æœ€é•¿å…¬å…±å‰ç¼€ã€‚å¦‚æžœä¸å­˜åœ¨å…¬å…±å‰ç¼€ï¼Œè¿”å›žç©ºå­—ç¬¦ä¸² ã€‚',9999999,9999999,'ç®€å•','3 flower flow flight','fl',1,NULL,NULL,'å­—ç¬¦ä¸²ä»…ç”±å°å†™è‹±æ–‡å­—æ¯ç»„æˆ','2023-06-01 17:14:02',NULL,0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ç”¨æˆ·è§’è‰²id',
  `role_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'admin|student|teacher',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ç”¨æˆ·è§’è‰²æè¿°',
  `auth` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æƒé™çº§åˆ«',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ç”¨æˆ·ç±»åž‹;';

/*Data for the table `role` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT 'èœå•å',
  `path` varchar(200) DEFAULT NULL COMMENT 'è·¯ç”±åœ°å€',
  `component` varchar(255) DEFAULT NULL COMMENT 'ç»„ä»¶è·¯å¾„',
  `visible` char(1) DEFAULT '0' COMMENT 'èœå•çŠ¶æ€ï¼ˆ0æ˜¾ç¤º 1éšè—ï¼‰',
  `status` char(1) DEFAULT '0' COMMENT 'èœå•çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰',
  `perms` varchar(100) DEFAULT NULL COMMENT 'æƒé™æ ‡è¯†',
  `icon` varchar(100) DEFAULT '#' COMMENT 'èœå•å›¾æ ‡',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT 'æ˜¯å¦åˆ é™¤ï¼ˆ0æœªåˆ é™¤ 1å·²åˆ é™¤ï¼‰',
  `remark` varchar(500) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='èœå•è¡¨';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`path`,`component`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`,`remark`) values (1,'ç™»å½•',NULL,NULL,'0','0','sys:user:login','#',NULL,NULL,NULL,NULL,0,NULL),(2,'ä¸‹è½½',NULL,NULL,'0','0','sys:assign:download','#',NULL,NULL,NULL,NULL,0,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `role_key` varchar(100) DEFAULT NULL COMMENT 'è§’è‰²æƒé™å­—ç¬¦ä¸²',
  `status` char(1) DEFAULT '0' COMMENT 'è§’è‰²çŠ¶æ€ï¼ˆ0æ­£å¸¸ 1åœç”¨ï¼‰',
  `del_flag` int DEFAULT '0' COMMENT 'del_flag',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='è§’è‰²è¡¨';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`role_key`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'user','user','0',0,NULL,NULL,NULL,NULL,NULL),(2,'admin','admin','0',0,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'è§’è‰²ID',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT 'èœå•id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ç”¨æˆ·id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT 'è§’è‰²id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(1,2);

/*Table structure for table `take` */

DROP TABLE IF EXISTS `take`;

CREATE TABLE `take` (
  `take_id` int NOT NULL AUTO_INCREMENT COMMENT 'é€‰è¯¾id',
  `uid` int DEFAULT NULL COMMENT 'é€‰è¯¾å­¦ç”Ÿid',
  `course_id` int DEFAULT NULL COMMENT 'è¯¾ç¨‹id',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'å¼€å§‹æ—¶é—´',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ç»“æŸæ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`take_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='é€‰è¯¾;';

/*Data for the table `take` */

insert  into `take`(`take_id`,`uid`,`course_id`,`start_time`,`end_time`,`create_time`,`last_modified_time`) values (1,1,1,'2023-03-17 10:46:19','2023-03-17 10:46:19','2023-03-17 10:46:19','2023-03-17 10:46:19');

/*Table structure for table `test_point` */

DROP TABLE IF EXISTS `test_point`;

CREATE TABLE `test_point` (
  `test_point_id` int NOT NULL AUTO_INCREMENT,
  `problem_id` int NOT NULL,
  `test_point_input` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test_point_output` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`test_point_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `test_point` */

insert  into `test_point`(`test_point_id`,`problem_id`,`test_point_input`,`test_point_output`) values (1,1,'1 1','2'),(2,1,'2 2','4'),(3,1,'3 3','6'),(5,1,'4 3','7'),(6,1,'4 3','7'),(7,1,'4 3','7'),(8,1,'4 3','7'),(10,2,'4 3','7');

/*Table structure for table `usecase` */

DROP TABLE IF EXISTS `usecase`;

CREATE TABLE `usecase` (
  `case_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ä¸»é”®',
  `problem_id` int NOT NULL COMMENT 'é¢˜ç›®id',
  `input` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'è¾“å…¥',
  `output` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'è¾“å‡º',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='é¢˜ç›®ç”¨ä¾‹;';

/*Data for the table `usecase` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `uuid` bigint NOT NULL AUTO_INCREMENT COMMENT 'uuç”¨æˆ·id',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'å§“å',
  `gender` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æ€§åˆ«',
  `phone_number` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'æ‰‹æœºå·',
  `birthday` datetime DEFAULT NULL COMMENT 'å‡ºç”Ÿæ—¥æœŸ',
  `email_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ç”µå­é‚®ç®±åœ°å€',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ç™»å½•å¯†ç ',
  `school` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'å­¦æ ¡',
  `major` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ä¸“ä¸š',
  `school_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'å­¦å·/èŒå·¥å·',
  `avater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'å¤´åƒå›¾ç‰‡åœ°å€',
  `signature` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ä¸ªæ€§ç­¾å',
  `nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ç”¨æˆ·æ˜µç§°',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `last_modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¸Šæ¬¡ä¿®æ”¹æ—¶é—´',
  `rid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT '1ä¸ºåˆ é™¤',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ç”¨æˆ·ä¿¡æ¯;';

/*Data for the table `user_info` */

insert  into `user_info`(`uuid`,`name`,`gender`,`phone_number`,`birthday`,`email_address`,`password`,`school`,`major`,`school_id`,`avater`,`signature`,`nickname`,`create_time`,`last_modified_time`,`rid`,`status`) values (1,'yjh','ç”·','18482061353','2023-03-16 22:02:57','940844742@qq.com','$2a$10$B4D0tXIU/zsGZ.MibPx/KuPSw8UHFKD509xvTLJYGslUyertjWe/C',NULL,NULL,NULL,NULL,NULL,NULL,'2023-03-16 22:03:29','2023-03-16 22:03:29',NULL,0),(7,NULL,NULL,NULL,NULL,'9408447421@qq.com','$2a$10$dYl8C0yPeIqfrs1eqBVDX.GWgadeljQALZmXYFhGiKJIlRjndxoji',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-28 16:30:00','2023-05-28 16:30:00',NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
