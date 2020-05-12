/*
SQLyog Professional v12.08 (32 bit)
MySQL - 5.5.62 : Database - epidemic
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`epidemic` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `epidemic`;

/*Table structure for table `illness` */

DROP TABLE IF EXISTS `illness`;

CREATE TABLE `illness` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(255) DEFAULT NULL,
  `now_confirm` int(11) DEFAULT NULL,
  `confirm` int(11) DEFAULT NULL,
  `dead` int(11) DEFAULT NULL,
  `heal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1259797475842072579 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
