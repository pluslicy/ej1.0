/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 134.175.154.93:3306
 Source Schema         : ej1.0

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 09/09/2019 08:01:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ej_address
-- ----------------------------
DROP TABLE IF EXISTS `ej_address`;
CREATE TABLE `ej_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `ej_address_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `ej_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_category
-- ----------------------------
DROP TABLE IF EXISTS `ej_category`;
CREATE TABLE `ej_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `ej_category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `ej_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2934 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_comment
-- ----------------------------
DROP TABLE IF EXISTS `ej_comment`;
CREATE TABLE `ej_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `comment_time` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `ej_comment_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `ej_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=500270 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_customer
-- ----------------------------
DROP TABLE IF EXISTS `ej_customer`;
CREATE TABLE `ej_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1215124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_order
-- ----------------------------
DROP TABLE IF EXISTS `ej_order`;
CREATE TABLE `ej_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_time` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `waiter_id` bigint(20) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `waiter_id` (`waiter_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `ej_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `ej_customer` (`id`),
  CONSTRAINT `ej_order_ibfk_2` FOREIGN KEY (`waiter_id`) REFERENCES `ej_waiter` (`id`),
  CONSTRAINT `ej_order_ibfk_3` FOREIGN KEY (`address_id`) REFERENCES `ej_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_order_line
-- ----------------------------
DROP TABLE IF EXISTS `ej_order_line`;
CREATE TABLE `ej_order_line` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `ej_order_line_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `ej_product` (`id`),
  CONSTRAINT `ej_order_line_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `ej_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_product
-- ----------------------------
DROP TABLE IF EXISTS `ej_product`;
CREATE TABLE `ej_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `ej_product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `ej_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3637 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ej_waiter
-- ----------------------------
DROP TABLE IF EXISTS `ej_waiter`;
CREATE TABLE `ej_waiter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2480 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
