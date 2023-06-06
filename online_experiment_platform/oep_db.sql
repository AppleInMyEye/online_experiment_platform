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
  `assign_id` bigint NOT NULL AUTO_INCREMENT COMMENT '作业id',
  `assign_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作业名称',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  `course_id` bigint DEFAULT NULL COMMENT '课程id',
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `content` blob,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  KEY `assign_id` (`assign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='作业;';

/*Data for the table `assignment` */

insert  into `assignment`(`assign_id`,`assign_name`,`start_time`,`end_time`,`create_time`,`last_modified_time`,`course_id`,`title`,`description`,`content`,`filename`) values (1,'assignment1','2022-04-01 00:00:00','2022-04-30 23:59:59','2023-03-17 12:12:49','2023-03-17 12:12:49',1,NULL,'This is assignment1.','PK\0\0\0\0�/ViQ��\0\0\0\0\0\0\0experiment3.1.txt}U�n�0��?�2T��I�a+�Ӷ�ҝ�[�M\'Beɐd,�׏�,�i�h%���2��~0����^�t�c?��\n=̶�`ZG��ͳ�u�n�4Z�\0yX�:k���T��\"~��O��hD��팝�{V ���hu9��P�ٻ9�^�A��\r��\n�&.�o3������\\pvZxa�� \'�v�8������G��-m@{�����\Znk�[�T�[Z�����Q+tkzt\n�c�^aR�5�c	�O����i�\rO��^�g����䨣�Mʜ2O����K�l%k�VTĄ̟ 	��A��}#.��fK�_����ч�R�\'���TU�l;O��J��>�f�d��S\'�n!��4ʉ��(I�̜��]�/\rIr��0��k\'�\0��Rw�N�iGȪ�aZ$�$�|xI�^5J8W��Ep���vTPޯW>-tE]K-}]�ˎ�q@͒̀r�C,;ʶ��H�(5�һ� 3����Ƭ�xg�oaۨMOK�_j�S�t=0j|a�rWf8g�8Q�W�.�8Ww:���������G��r\'�N��Ƿ/�� ��!,)���b�&V��0��XH]���Y�%���ۥ��\0��L�MsU>=�f�X-���U��<�ox0y���V��E ۋ�9^y�_���<�#��7X��2��J�I����5t���}�ǰ�g�ό�5��wbO����;��T�&ܤ��W��i��!�s�<�WދSD���	!�旾�E&`ʂ&�N��	QPK\0\0\0\0\0�/ViQ��\0\0\0\0\0\0\0\0\0\0\0\0 \0\0\0\0\0\0\0experiment3.1.txtPK\0\0\0\0\0\0?\0\0\0-\0\0\0\0','test1.zip'),(2,'assignment2','2022-04-01 00:00:00','2022-04-30 23:59:59','2023-03-17 12:15:02','2023-03-17 12:15:02',1,NULL,'This is assignment2.','This is a test content',NULL);

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
  `cid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板id',
  `lid` int NOT NULL COMMENT '语言id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '代码模板',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码模板;';

/*Data for the table `code_template` */

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` varchar(90) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '课程开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '课程结束时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  `teacher_id1` int DEFAULT NULL COMMENT '教师id',
  `teacher_id2` int DEFAULT NULL COMMENT '教师id',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程;';

/*Data for the table `course` */

insert  into `course`(`course_id`,`course_name`,`create_time`,`start_time`,`end_time`,`last_modified_time`,`teacher_id1`,`teacher_id2`) values (1,'高软','2023-03-17 10:18:09',NULL,NULL,'2023-03-17 10:18:08',123456,NULL),(2,'高软2','2023-03-17 10:35:54',NULL,NULL,'2023-03-17 10:35:54',123456,NULL);

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `file_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件',
  `url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='文件;';

/*Data for the table `file` */

/*Table structure for table `language` */

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `lid` int NOT NULL COMMENT '主键id',
  `content_type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '语言对应的代码模板',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '语言名字',
  `compile_command` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编译指令',
  `code_template` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '语言对应的代码模板',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='编程语言;';

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

insert  into `problem`(`problem_id`,`title`,`description`,`time_limit`,`memory_limit`,`difficulty`,`sample_input`,`sample_output`,`test_point_num`,`test_point_input`,`test_point_output`,`tips`,`create_time`,`last_modified_time`,`status`) values (1,'两数之和','返回两个数的和',9999999,9999999,'简单','输入 10 10','20',8,'1 1,2 2','2,4','这真是太简单了',NULL,NULL,0),(2,'最长公共前缀','编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 。',9999999,9999999,'简单','3 flower flow flight','fl',1,NULL,NULL,'字符串仅由小写英文字母组成','2023-06-01 17:14:02',NULL,0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户角色id',
  `role_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'admin|student|teacher',
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户角色描述',
  `auth` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限级别',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户类型;';

/*Data for the table `role` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`path`,`component`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`,`remark`) values (1,'登录',NULL,NULL,'0','0','sys:user:login','#',NULL,NULL,NULL,NULL,0,NULL),(2,'下载',NULL,NULL,'0','0','sys:assign:download','#',NULL,NULL,NULL,NULL,0,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int DEFAULT '0' COMMENT 'del_flag',
  `create_by` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`role_key`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'user','user','0',0,NULL,NULL,NULL,NULL,NULL),(2,'admin','admin','0',0,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(1,2);

/*Table structure for table `take` */

DROP TABLE IF EXISTS `take`;

CREATE TABLE `take` (
  `take_id` int NOT NULL AUTO_INCREMENT COMMENT '选课id',
  `uid` int DEFAULT NULL COMMENT '选课学生id',
  `course_id` int DEFAULT NULL COMMENT '课程id',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  PRIMARY KEY (`take_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='选课;';

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
  `case_id` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `problem_id` int NOT NULL COMMENT '题目id',
  `input` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '输入',
  `output` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '输出',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题目用例;';

/*Data for the table `usecase` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `uuid` bigint NOT NULL AUTO_INCREMENT COMMENT 'uu用户id',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '姓名',
  `gender` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `phone_number` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `email_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电子邮箱地址',
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `school` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校',
  `major` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  `school_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学号/职工号',
  `avater` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像图片地址',
  `signature` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个性签名',
  `nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_modified_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  `rid` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT '1为删除',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息;';

/*Data for the table `user_info` */

insert  into `user_info`(`uuid`,`name`,`gender`,`phone_number`,`birthday`,`email_address`,`password`,`school`,`major`,`school_id`,`avater`,`signature`,`nickname`,`create_time`,`last_modified_time`,`rid`,`status`) values (1,'yjh','男','18482061353','2023-03-16 22:02:57','940844742@qq.com','$2a$10$B4D0tXIU/zsGZ.MibPx/KuPSw8UHFKD509xvTLJYGslUyertjWe/C',NULL,NULL,NULL,NULL,NULL,NULL,'2023-03-16 22:03:29','2023-03-16 22:03:29',NULL,0),(7,NULL,NULL,NULL,NULL,'9408447421@qq.com','$2a$10$dYl8C0yPeIqfrs1eqBVDX.GWgadeljQALZmXYFhGiKJIlRjndxoji',NULL,NULL,NULL,NULL,NULL,NULL,'2023-05-28 16:30:00','2023-05-28 16:30:00',NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
