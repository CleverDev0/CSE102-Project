/*
 Navicat MySQL Data Transfer

 Source Server         : asd
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost
 Source Database       : cse_102_project_db

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : utf-8

 Date: 03/31/2019 12:43:04 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Announcements`
-- ----------------------------
DROP TABLE IF EXISTS `Announcements`;
CREATE TABLE `Announcements` (
  `AnnouncementId` int(11) NOT NULL AUTO_INCREMENT,
  `AnnpuncementDescription` varchar(300) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`AnnouncementId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- ----------------------------
--  Table structure for `Messages`
-- ----------------------------
DROP TABLE IF EXISTS `Messages`;
CREATE TABLE `Messages` (
  `MessageId` int(11) NOT NULL AUTO_INCREMENT,
  `SenderId` int(11) NOT NULL,
  `RecieverId` int(11) NOT NULL,
  `Message` varchar(300) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`MessageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- ----------------------------
--  Table structure for `transactions`
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `Description` varchar(100) COLLATE utf32_turkish_ci DEFAULT NULL,
  `Value` int(8) DEFAULT NULL,
  `IsExpense` tinyint(1) DEFAULT NULL,
  `TransactionType` varchar(12) COLLATE utf32_turkish_ci DEFAULT NULL,
  `ManagerCode` varchar(6) COLLATE utf32_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_turkish_ci;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `Surname` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `PhoneNumber` char(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `TCNumber` char(11) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `SerialNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `ApartmentNumber` varchar(3) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `IsAdmin` tinyint(1) NOT NULL,
  `floorNumber` varchar(4) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

SET FOREIGN_KEY_CHECKS = 1;
