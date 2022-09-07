/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.7.25-log : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_Name` varchar(10) NOT NULL,
  `admin_password` varchar(10) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`admin_Name`,`admin_password`,`level`) values 
(1,'123','123',1),
(2,'admin','admin',1);

/*Table structure for table `booklist` */

DROP TABLE IF EXISTS `booklist`;

CREATE TABLE `booklist` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) NOT NULL,
  `bookPrice` double(11,2) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookType` varchar(255) NOT NULL,
  `bookDetail` varchar(2550) NOT NULL,
  `bookIsbn` int(255) NOT NULL,
  `bookFile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `booklist` */

insert  into `booklist`(`bookid`,`bookName`,`bookPrice`,`bookAuthor`,`bookType`,`bookDetail`,`bookIsbn`,`bookFile`) values 
(1,'三国演义',100.00,'罗贯中','小说','《三国演义（套装上下册）》是罗贯中在民间传说和有关话本、戏曲的基础上改编而成的。作者通过集中描绘三国时代各封建统治集团之间的政治、军事、外交斗争，揭示了东汉末年社会的动荡和黑暗，谴责了封建统治者的暴虐，反映了民众的苦难和他们呼唤明君、呼唤安定的强烈愿望。 小说塑造了大量栩栩如生的人物，宽厚仁爱的刘备，残暴奸诈的曹操，一身正气的关羽，粗中有细的张飞，还有头戴纶巾、手摇羽扇的诸葛亮，以计谋见长的周瑜和司马懿。他们斗智斗勇的故事早已给人们留下了深刻的印象……',10120120,'1.jpg'),
(2,'西游记',100.00,'吴承恩','小说','　《西游记》主要描写的是孙悟空保唐僧西天取经，历经九九八十一难的故事。',10008248,'2.jpg'),
(3,'水浒传',100.00,'施耐庵','小说','《水浒传》是一部长篇英雄传奇，是中国古代长篇小说的代表作之一，是以宋江起义故事为线索创作出来的。',10008249,'3.jpg'),
(4,'红楼梦',100.00,'曹雪芹','小说','本书是一部具有高度思想性和高度艺术性的伟大作品，从本书反映的思想倾向来看，作者具有初步的民主主义思想，他对现实社会包括宫廷及官场的黑暗，封建贵族阶级及其家庭的腐朽，封建的科举制度、婚姻制度、奴婢制度、等级制度，以及与此相适应的社会统治思想即孔孟之道和程朱理学、社会道德观念等等，都进行了深刻的批判并且提出了朦胧的带有初步民主主义性质的理想和主张。这些理想和主张正是当时正在滋长的资本主义经济萌芽因素的曲折反映。',10008245,'4.jpg'),
(5,'威士忌学',168.88,'邱德夫','威士忌','这是一本翔实厚重的酒类研究及普及书。作者研究品鉴威士忌超2000种，终将博览群书、遍尝美酒所获的十余年心血结晶集结于这本品质尽显的书中。',978988699,'5.jpg');

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `bookPrice` double(11,2) NOT NULL,
  `number` int(11) NOT NULL,
  `bookFile` varchar(255) DEFAULT NULL,
  KEY `logid` (`userid`),
  KEY `goodsid` (`bookid`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`bookid`) REFERENCES `booklist` (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`userid`,`bookid`,`bookName`,`bookPrice`,`number`,`bookFile`) values 
(1,1,'三国演义',100.00,1,'1.jpg'),
(1,2,'西游记',100.00,1,'2.jpg'),
(1,3,'水浒传',100.00,1,'3.jpg'),
(1,5,'威士忌学',168.88,1,'5.jpg'),
(1,4,'红楼梦',100.00,1,'4.jpg');

/*Table structure for table `orderform` */

DROP TABLE IF EXISTS `orderform`;

CREATE TABLE `orderform` (
  `orderId` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `sum` double(11,2) NOT NULL,
  `state` varchar(20) NOT NULL DEFAULT '未发货',
  PRIMARY KEY (`orderId`),
  KEY `userId` (`userId`),
  CONSTRAINT `orderform_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderform` */

insert  into `orderform`(`orderId`,`userId`,`sum`,`state`) values 
('20211212200019',1,0.00,'已取消'),
('20211212224408',1,200.00,'未发货'),
('20211214173120',1,100.00,'未发货'),
('20211214201015',1,300.00,'未发货'),
('20211214201049',1,300.00,'未发货'),
('20211215173218',1,100.00,'未发货'),
('20211215174316',1,800.00,'未发货');

/*Table structure for table `orderlist` */

DROP TABLE IF EXISTS `orderlist`;

CREATE TABLE `orderlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `bookPrice` double(11,2) NOT NULL,
  `number` int(11) NOT NULL,
  `bookFile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `orderlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `orderlist_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orderform` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

/*Data for the table `orderlist` */

insert  into `orderlist`(`id`,`orderId`,`userId`,`bookName`,`bookPrice`,`number`,`bookFile`) values 
(66,'20211212224408',1,'水浒传',100.00,1,'3.jpg'),
(67,'20211212224408',1,'红楼梦',100.00,1,'4.jpg'),
(68,'20211214173120',1,'红楼梦',100.00,1,'4.jpg'),
(69,'20211214201015',1,'西游记',100.00,1,'2.jpg'),
(70,'20211214201015',1,'水浒传',100.00,1,'3.jpg'),
(71,'20211214201015',1,'红楼梦',100.00,1,'4.jpg'),
(72,'20211214201049',1,'西游记',100.00,2,'2.jpg'),
(73,'20211214201049',1,'水浒传',100.00,1,'3.jpg'),
(74,'20211215173218',1,'西游记',100.00,1,'2.jpg'),
(75,'20211215174316',1,'西游记',100.00,8,'2.jpg');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ban` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`password`,`ban`) values 
(1,'123','123',1),
(2,'2','2',1),
(3,'chan','123',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
