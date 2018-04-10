/*
Navicat MySQL Data Transfer

Source Server         : freedom
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : projectone

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-09 15:26:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL COMMENT '学院id',
  `name` varchar(200) NOT NULL COMMENT '学院名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '计算机系');
INSERT INTO `college` VALUES ('2', '设计系');
INSERT INTO `college` VALUES ('3', '财经系');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseID` int(11) NOT NULL,
  `courseName` varchar(200) NOT NULL COMMENT '课程名称',
  `teacherID` int(11) NOT NULL,
  `courseTime` varchar(200) DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `collegeID` int(11) NOT NULL COMMENT '所属院系',
  `score` int(11) NOT NULL COMMENT '学分',
  PRIMARY KEY (`courseID`),
  KEY `collegeID` (`collegeID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'C语言程序设计', '1001', '周二', '科401', '18', '必修课', '1', '3');
INSERT INTO `course` VALUES ('2', 'Python爬虫技巧', '1001', '周四', 'X402', '18', '必修课', '1', '3');
INSERT INTO `course` VALUES ('3', '数据结构', '1001', '周四', '科401', '18', '必修课', '1', '2');
INSERT INTO `course` VALUES ('4', 'Java程序设计', '1002', '周五', '科401', '18', '必修课', '1', '2');
INSERT INTO `course` VALUES ('5', '英语', '1002', '周四', 'X302', '18', '必修课', '2', '2');
INSERT INTO `course` VALUES ('6', '服装设计', '1003', '周一', '科401', '18', '选修课', '2', '2');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', 'admin', null);
INSERT INTO `role` VALUES ('1', 'teacher', null);
INSERT INTO `role` VALUES ('2', 'student', null);

-- ----------------------------
-- Table structure for `selectedcourse`
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse` (
  `courseID` int(11) NOT NULL,
  `studentNum` int(11) NOT NULL COMMENT '学生学号',
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentNum`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES ('2', '10001', '12');
INSERT INTO `selectedcourse` VALUES ('1', '10001', '95');
INSERT INTO `selectedcourse` VALUES ('1', '10002', '66');
INSERT INTO `selectedcourse` VALUES ('1', '10003', null);
INSERT INTO `selectedcourse` VALUES ('2', '10003', '99');
INSERT INTO `selectedcourse` VALUES ('5', '10001', null);
INSERT INTO `selectedcourse` VALUES ('3', '10001', null);

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `number` int(11) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` varchar(200) NOT NULL COMMENT '姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `admissionDate` date DEFAULT NULL COMMENT '入学时间',
  `collegeId` int(11) NOT NULL COMMENT '院系id',
  PRIMARY KEY (`number`),
  KEY `collegeID` (`collegeId`),
  CONSTRAINT `student_college` FOREIGN KEY (`collegeId`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('10004', '小华', '男', '1996-09-02', '2015-09-02', '2');
INSERT INTO `student` VALUES ('10005', '小左', '女', '1996-09-02', '2015-09-02', '2');
INSERT INTO `student` VALUES ('10006', '小拉', '女', '1996-09-02', '2015-09-02', '1');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date NOT NULL,
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `grade` date DEFAULT NULL COMMENT '入职时间',
  `collegeID` int(11) NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1001', '刘老师', '女', '1990-03-08', '硕士', '副教授', '2015-09-02', '2');
INSERT INTO `teacher` VALUES ('1002', '张老师', '男', '1996-09-02', '本科', '普通教师', '2015-09-02', '1');
INSERT INTO `teacher` VALUES ('1003', '软老师', '男', '1996-09-02', '硕士', '助教', '2017-07-07', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`userID`),
  KEY `role` (`role`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '0');
INSERT INTO `user` VALUES ('8', '10001', '123', '2');
INSERT INTO `user` VALUES ('9', '10002', '123', '2');
INSERT INTO `user` VALUES ('10', '10003', '123', '2');
INSERT INTO `user` VALUES ('11', '10005', '123', '2');
INSERT INTO `user` VALUES ('12', '10004', '123', '2');
INSERT INTO `user` VALUES ('13', '10006', '123', '2');
INSERT INTO `user` VALUES ('14', '1001', '123', '1');
INSERT INTO `user` VALUES ('15', '1002', '123', '1');
INSERT INTO `user` VALUES ('16', '1003', '123', '1');
