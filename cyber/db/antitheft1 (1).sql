/*
SQLyog Ultimate v8.55 
MySQL - 5.1.57-community : Database - antitheft
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`antitheft` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `antitheft`;

/*Table structure for table `callinfo` */

DROP TABLE IF EXISTS `callinfo`;

CREATE TABLE `callinfo` (
  `callid` int(200) NOT NULL AUTO_INCREMENT,
  `number` varchar(256) NOT NULL,
  `type` varchar(256) NOT NULL,
  `duration` varchar(256) NOT NULL,
  `dateinfo` varchar(256) NOT NULL,
  `loginid` varchar(256) NOT NULL,
  PRIMARY KEY (`callid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `callinfo` */

insert  into `callinfo`(`callid`,`number`,`type`,`duration`,`dateinfo`,`loginid`) values (3,'+919567842009','Missed','0','2015-09-16 00:13:55','1'),(4,'+918547430454','Missed','104','2015-09-16 13:22:17','asd'),(5,'+919048786826','Missed','0','2015-09-16 21:14:14','ponnu'),(6,'+918547430454','Incoming','72','2015-09-16 21:18:44','ponnu'),(7,'+919567842009','Missed','0','2015-09-17 10:50:45','asd'),(8,'+919567842009','Missed','0','2015-09-17 22:30:49','prej'),(9,'+918281628302','Outgoing','5','2015-09-17 22:31:26','prej'),(10,'+918281432126','Missed','0','2015-09-17 23:12:56','prej'),(11,'+918547430454','Missed','0','2015-09-18 08:08:40','mm'),(12,'+918547430454','Missed','0','2015-09-18 08:31:35','mm'),(13,'+919567842009','Incoming','95','2015-09-18 08:53:30','mm'),(14,'+918281432126','Missed','35','2015-09-18 10:28:07','ddd'),(15,'+918547430454','Missed','0','2015-09-18 11:41:12','zz'),(16,'+918547430454','Missed','0','2015-09-18 12:03:25','hhh'),(17,'+918547430454','Missed','0','2015-09-18 12:35:07','ppp'),(18,'+917403539742','Missed','0','2015-09-18 13:21:22','pa');

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) DEFAULT NULL,
  `image` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*Data for the table `image` */

insert  into `image`(`id`,`userid`,`image`) values (1,'1','img.jpg'),(2,'login','img.jpg'),(3,'login','img.jpg'),(4,'login','img.jpg'),(5,'login','img.jpg'),(6,'login','img.jpg'),(7,'login','img.jpg'),(8,'login','img.jpg'),(9,'login','img.jpg'),(10,'login','img.jpg'),(11,'login','img.jpg'),(12,'login','img.jpg'),(13,'login','img.jpg'),(14,'login','img.jpg'),(15,'login','img.jpg'),(16,'login','img.jpg'),(17,'login','img.jpg'),(18,'login','img.jpg'),(19,'login','img.jpg'),(20,'login','img.jpg'),(21,'login','img.jpg'),(22,'login','img.jpg'),(23,'login','img.jpg'),(24,'login','img.jpg'),(25,'login','img.jpg'),(26,'login','img.jpg'),(27,'login','img.jpg'),(28,'login','img.jpg'),(30,'9','img.jpg'),(31,'10','img.jpg'),(32,'10','img.jpg'),(33,'12','img.jpg');

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `userid` varchar(500) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `location` */

insert  into `location`(`id`,`userid`,`lat`,`lng`) values (1,'1',9.9961234,76.2905751),(2,'2',9.9961335,76.2905613),(3,'5',10.1457701,76.2622121),(4,'4',10.1477591,76.2651417),(5,'6',10.1477161,76.2651338),(6,'7',10.147748,76.2651388),(7,'8',10.1477308,76.2651399),(8,'9',10.1477425,76.265131),(9,'10',10.1477429,76.2651445),(10,'11',10.1479264,76.2620041),(11,'12',10.1457728,76.2622042),(12,'13',10.1479264,76.2620041),(13,'14',10.1457728,76.2622042);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`) values (1,'asd','asd'),(2,'ponnu','ponnu'),(3,'devu','devu'),(4,'prej','prej'),(5,'aaa','aaa'),(6,'kannan','kannan'),(7,'mm','mm'),(8,'kk','kk'),(9,'ddd','ddd'),(10,'kathu','kathu'),(11,'zz','zz'),(12,'hhh','hhh'),(13,'ppp','ppp'),(14,'pa','pa'),(15,'','');

/*Table structure for table `reg` */

DROP TABLE IF EXISTS `reg`;

CREATE TABLE `reg` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `pno` varchar(200) DEFAULT NULL,
  `sno` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `reg` */

insert  into `reg`(`id`,`name`,`pno`,`sno`,`email`,`username`,`password`) values (1,'asd','null','null','malusarasappan@gmail.com','asd','asd'),(2,'Ponnus','8281432126','9048786826','malusarasappan@gmail.com','ponnu','ponnu'),(3,'devu','8281432126','8086840653','malusarasappan@gmail.com','devu','devu'),(4,'prej','7403539742','8086840653','tmprajesha@gmail.com','prej','prej'),(5,'aaa','8281432126','8086840653','devikababu1911@gmail.com','aaa','aaa'),(6,'kannan','8086840653','8281432126','devikababu1911@gmail.com','kannan','kannan'),(7,'mmm','8086840653','8281432126','devikababu1911@gmail.com','mm','mm'),(8,'kk','8086840653','8281432126','devikababu1911@gmail.com','kk','kk'),(9,'devi','8086840653','8281432126','devikababu1911@gmail.com','ddd','ddd'),(10,'kathu','8086840653','8547430454','devikababu1911@gmail.com','kathu','kathu'),(11,'qqq','8281432126','8086840653','malusarasappan@gmail.com','zz','zz'),(12,'hhh','9400996401','8086840653','malusarasappan@gmail.com','hhh','hhh'),(13,'sss','8547430454','8281432126','malusarasappan@gmail.com','ppp','ppp'),(14,'pa','7403539742','8281432126','malusarasappan@gmail.com','pa','pa'),(15,'aa','9287595485','9048786826','','','');

/*Table structure for table `sim` */

DROP TABLE IF EXISTS `sim`;

CREATE TABLE `sim` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `userid` varchar(200) DEFAULT NULL,
  `deviceId` varchar(300) DEFAULT NULL,
  `simop` varchar(300) DEFAULT NULL,
  `simserial` varchar(300) DEFAULT NULL,
  `status` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `sim` */

insert  into `sim`(`id`,`userid`,`deviceId`,`simop`,`simserial`,`status`) values (1,'1','asus asus - K00Z','','','1'),(2,'1','asus asus - K00Z','','8991197120988506540','2'),(3,'1','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(4,'1','samsung samsung - GT-I8190','BSNL MOBILE','89917290134811129258','2'),(5,'1','samsung samsung - SM-J500F','BSNL MOBILE','89917290934849502255','2'),(6,'1','samsung samsung - GT-I8190','Vodafone IN','8991462160647814131','2'),(7,'2','samsung samsung - GT-I8190','Vodafone IN','8991462160278550749','2'),(8,'2','samsung samsung - GT-I8190','Vodafone IN','8991462160647814131','2'),(9,'4','samsung samsung - GT-I8190','Vodafone IN','8991462160647814131','2'),(10,'4','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(11,'6','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(12,'6','samsung samsung - GT-I8190','BSNL MOBILE','89917290134811129258','2'),(13,'7','samsung samsung - GT-I8190','BSNL MOBILE','89917290134811129258','2'),(14,'7','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(15,'8','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(16,'9','samsung samsung - GT-I8190','BSNL MOBILE','89917290134811129258','2'),(17,'10','samsung samsung - GT-I8190','BSNL MOBILE','89917290134811129258','2'),(18,'10','samsung samsung - GT-I8190','International','8991462160647814131','2'),(19,'10','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(20,'11','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(21,'12','samsung samsung - GT-I8190','Vodafone IN','8991462160647814131','2'),(22,'12','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(23,'13','samsung samsung - GT-I8190','BSNL MOBILE','89917290934841001819','2'),(24,'14','samsung samsung - GT-I8190','Vodafone IN','8991462160647814131','2');

/*Table structure for table `smsinfo` */

DROP TABLE IF EXISTS `smsinfo`;

CREATE TABLE `smsinfo` (
  `smsid` int(200) NOT NULL AUTO_INCREMENT,
  `phoneno` varchar(256) NOT NULL,
  `message` varchar(1024) NOT NULL,
  `dateinfo` varchar(256) NOT NULL,
  `type` varchar(256) NOT NULL,
  `loginid` varchar(256) NOT NULL,
  PRIMARY KEY (`smsid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `smsinfo` */

insert  into `smsinfo`(`smsid`,`phoneno`,`message`,`dateinfo`,`type`,`loginid`) values (1,'+919567842009','Hi','2015-09-15 19:48:50','','1'),(2,'+919567842009','data deleteall','2015-09-16 00:37:04','recieved','1'),(3,'+919567842009','data deleteall','2015-09-16 00:41:30','recieved','1'),(4,'+919567842009','data deleteall','2015-09-16 00:43:32','recieved','1'),(5,'+919567842009','data deleteall','2015-09-16 00:51:36','recieved','1'),(6,'+919567842009','data deleteall','2015-09-16 01:09:04','recieved','1'),(7,'+919567842009','data deleteall','2015-09-16 01:11:26','recieved','1'),(8,'+918547430454','hw r u','2015-09-16 13:11:02','recieved','asd'),(9,'+918547430454','data deleteall','2015-09-16 13:24:05','recieved','asd'),(10,'+918547430454','hello','2015-09-16 14:19:00','recieved','asd'),(11,'+918547430454','data deleteall','2015-09-16 14:23:49','recieved','asd'),(12,'+919567842009','data deleteall','2015-09-16 14:32:11','recieved','asd'),(13,'+919048786826','Hai malu chechi','2015-09-16 21:15:31','recieved','ponnu'),(14,'+918547430454','Hi','2015-09-17 22:18:44','recieved','prej'),(15,'+918281432126','Hi','2015-09-17 23:13:10','recieved','prej'),(16,'+918281432126','Hello','2015-09-17 23:14:24','recieved','prej'),(17,'+918547430454','mmm','2015-09-18 08:32:37','recieved','mm'),(18,'+918281432126','heellloo','2015-09-18 10:30:59','recieved','ddd'),(19,'+918547430454','data deleteall','2015-09-18 11:07:27','recieved','kathu'),(20,'+918547430454','New Sim Detected, Serial is :89917290134811129258','2015-09-18 11:41:44','recieved','zz'),(21,'+918281432126','New Sim Detected, Serial is :89917290934841001819','2015-09-18 11:41:51','recieved','zz'),(22,'+918281432126','New Sim Detected, Serial is :89917290934841001819','2015-09-18 11:42:02','recieved','zz'),(23,'+918547430454','hi','2015-09-18 11:42:13','recieved','zz'),(24,'+918547430454','data deleteall','2015-09-18 12:03:21','recieved','hhh'),(25,'+918547430454','data deleteall','2015-09-18 12:04:27','recieved','hhh'),(26,'+918547430454','data deleteall','2015-09-18 12:37:57','recieved','ppp'),(27,'+918547430454','data deleteall','2015-09-18 13:06:49','recieved','pa');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
