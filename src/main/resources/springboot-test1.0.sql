/*
Navicat MySQL Data Transfer

Source Server         : connect
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : springboot-test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-07-25 14:58:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ingredient`
-- ----------------------------
DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE `ingredient` (
  `id` varchar(4) NOT NULL,
  `name` varchar(25) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ingredient
-- ----------------------------
INSERT INTO `ingredient` VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
INSERT INTO `ingredient` VALUES ('COTO', 'Corn Tortilla', 'WRAP');
INSERT INTO `ingredient` VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
INSERT INTO `ingredient` VALUES ('CARN', 'Carnitas', 'PROTEIN');
INSERT INTO `ingredient` VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
INSERT INTO `ingredient` VALUES ('LETC', 'Lettuce', 'VEGGIES');
INSERT INTO `ingredient` VALUES ('CHED', 'Cheddar', 'CHEESE');
INSERT INTO `ingredient` VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
INSERT INTO `ingredient` VALUES ('SLSA', 'Salsa', 'SAUCE');
INSERT INTO `ingredient` VALUES ('SRCR', 'Sour Cream', 'SAUCE');

-- ----------------------------
-- Table structure for `taco`
-- ----------------------------
DROP TABLE IF EXISTS `taco`;
CREATE TABLE `taco` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taco
-- ----------------------------
INSERT INTO `taco` VALUES ('1', '动态界面设计与开发（JQuery）', '2022-07-22 15:41:45');
INSERT INTO `taco` VALUES ('2', 'Nodejs全栈开发', '2022-07-22 15:43:33');
INSERT INTO `taco` VALUES ('3', 'Nodejs全栈开发', '2022-07-22 15:58:20');
INSERT INTO `taco` VALUES ('4', 'Nodejs全栈开发', '2022-07-22 16:12:05');
INSERT INTO `taco` VALUES ('5', 'Nodejs全栈开发', '2022-07-22 16:16:38');
INSERT INTO `taco` VALUES ('6', 'Nodejs全栈开发1', '2022-07-22 16:20:22');
INSERT INTO `taco` VALUES ('7', 'Nodejs全栈开发1', '2022-07-22 16:21:12');
INSERT INTO `taco` VALUES ('8', 'Nodejs全栈开发1', '2022-07-22 16:22:12');
INSERT INTO `taco` VALUES ('9', 'Nodejs全栈开发1', '2022-07-22 16:24:32');
INSERT INTO `taco` VALUES ('10', 'Nodejs全栈开发1', '2022-07-22 16:26:08');
INSERT INTO `taco` VALUES ('11', 'Nodejs全栈开发1', '2022-07-22 16:26:49');
INSERT INTO `taco` VALUES ('12', 'Nodejs全栈开发1', '2022-07-22 16:35:57');
INSERT INTO `taco` VALUES ('13', '涛涛涛涛涛涛涛涛涛涛涛涛涛涛涛涛', '2022-07-22 16:38:05');
INSERT INTO `taco` VALUES ('14', 'MLMLMLMLMLML', '2022-07-22 16:42:36');
INSERT INTO `taco` VALUES ('15', 'MLMLMLMLMLML', '2022-07-22 16:43:45');
INSERT INTO `taco` VALUES ('16', '高级语言程序设计', '2022-07-25 14:42:03');
INSERT INTO `taco` VALUES ('17', '高级语言程序设计', '2022-07-25 14:43:12');
INSERT INTO `taco` VALUES ('18', '高级语言程序设计', '2022-07-25 14:44:11');
INSERT INTO `taco` VALUES ('19', '高级语言程序设计', '2022-07-25 14:47:50');
INSERT INTO `taco` VALUES ('20', '高级语言程序设计', '2022-07-25 14:49:30');
INSERT INTO `taco` VALUES ('21', '高级语言程序设计', '2022-07-25 14:56:02');

-- ----------------------------
-- Table structure for `taco_ingredients`
-- ----------------------------
DROP TABLE IF EXISTS `taco_ingredients`;
CREATE TABLE `taco_ingredients` (
  `taco` bigint(20) NOT NULL,
  `ingredient` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taco_ingredients
-- ----------------------------
INSERT INTO `taco_ingredients` VALUES ('3', 'FLTO');
INSERT INTO `taco_ingredients` VALUES ('3', 'COTO');
INSERT INTO `taco_ingredients` VALUES ('3', 'SLSA');
INSERT INTO `taco_ingredients` VALUES ('3', 'SRCR');
INSERT INTO `taco_ingredients` VALUES ('12', 'FLTO');
INSERT INTO `taco_ingredients` VALUES ('12', 'COTO');
INSERT INTO `taco_ingredients` VALUES ('12', 'SLSA');
INSERT INTO `taco_ingredients` VALUES ('12', 'SRCR');
INSERT INTO `taco_ingredients` VALUES ('13', 'COTO');
INSERT INTO `taco_ingredients` VALUES ('13', 'CARN');
INSERT INTO `taco_ingredients` VALUES ('13', 'CHED');
INSERT INTO `taco_ingredients` VALUES ('14', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('14', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('15', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('15', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('16', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('16', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('17', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('17', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('18', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('18', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('19', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('19', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('20', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('20', 'LETC');
INSERT INTO `taco_ingredients` VALUES ('21', 'JACK');
INSERT INTO `taco_ingredients` VALUES ('21', 'LETC');

-- ----------------------------
-- Table structure for `taco_order`
-- ----------------------------
DROP TABLE IF EXISTS `taco_order`;
CREATE TABLE `taco_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip` varchar(10) NOT NULL,
  `ccNumber` varchar(16) NOT NULL,
  `ccExpiration` varchar(5) NOT NULL,
  `ccCVV` varchar(3) NOT NULL,
  `placedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taco_order
-- ----------------------------
INSERT INTO `taco_order` VALUES ('1', '高级语言程序设计', '123', '13123', '12', '31231231', '123123', '12312', '444', '2022-07-25 14:57:22');

-- ----------------------------
-- Table structure for `taco_order_tacos`
-- ----------------------------
DROP TABLE IF EXISTS `taco_order_tacos`;
CREATE TABLE `taco_order_tacos` (
  `tacoOrder` bigint(20) NOT NULL,
  `taco` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taco_order_tacos
-- ----------------------------
INSERT INTO `taco_order_tacos` VALUES ('1', '21');
