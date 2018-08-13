/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.0.27-community-nt : Database - mytally
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mytally` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mytally`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(1000) default '',
  `address1` varchar(10000) default '',
  `address2` varchar(10000) default '',
  `city` varchar(1000) default '',
  `stateCode` varchar(1000) default '',
  `GSTNo` varchar(100) default '',
  `salesInvoiceNo` varchar(10000) default '',
  `mobileNo` varchar(30) default '',
  `email` varchar(1000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(1000) default '',
  `srno` varchar(10000) default '',
  `description` varchar(1000) default '',
  `HSN` varchar(1000) default '',
  `GST` int(2) default '0',
  `qty` int(255) default '0',
  `purchasePrice` double default '0',
  `salesPrice` double default '0',
  `per` varchar(1000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `item` */

/*Table structure for table `purchasebill` */

DROP TABLE IF EXISTS `purchasebill`;

CREATE TABLE `purchasebill` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `invoiceNo` varchar(20) default '0',
  `supplierName` varchar(1000) default '',
  `supplierGSTNo` varchar(1000) default '',
  `invoiceDate` date default '0000-00-00',
  `totalAmount` double default '0',
  `GST14` double default '0',
  `GST9` double default '0',
  `totalAmountGST` double default '0',
  `totalRoundOffAmount` int(255) default '0',
  `IGST` varchar(45) default 'NO',
  `narration` varchar(10000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchasebill` */

/*Table structure for table `purchaseitem` */

DROP TABLE IF EXISTS `purchaseitem`;

CREATE TABLE `purchaseitem` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `purchaseInvoiceNo` varchar(20) default '0',
  `salesInvoiceNo` int(20) default '0',
  `name` varchar(1000) default '',
  `srno` varchar(10000) default '',
  `description` varchar(1000) default '',
  `HSN` varchar(1000) default '',
  `GST` int(2) default '0',
  `qty` int(10) default '0',
  `unitPrice` double default '0',
  `discount` double default '0',
  `totalAmount` double default '0',
  `per` varchar(100) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `purchaseitem` */

/*Table structure for table `salesbill` */

DROP TABLE IF EXISTS `salesbill`;

CREATE TABLE `salesbill` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `invoiceNo` varchar(1000) default '',
  `customerGSTNo` varchar(1000) default '',
  `customerName` varchar(1000) default '',
  `invoiceDate` date default '0000-00-00',
  `totalAmount` double default '0',
  `GST14` double default '0',
  `GST9` double default '0',
  `totalAmountGST` double default '0',
  `totalRoundOffAmount` int(255) default '0',
  `vehical` varchar(1000) default '',
  `vehicalNo` varchar(1000) default '',
  `IGST` varchar(45) default 'NO',
  `challanNumber` varchar(1000) default '',
  `paymentMode` varchar(1000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `salesbill` */

/*Table structure for table `salesitem` */

DROP TABLE IF EXISTS `salesitem`;

CREATE TABLE `salesitem` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `purchaseInvoiceNo` int(10) default '0',
  `salesInvoiceNo` varchar(1000) default '',
  `name` varchar(1000) default '',
  `srno` varchar(10000) default '',
  `description` varchar(1000) default '',
  `HSN` varchar(1000) default '',
  `GST` int(2) default '0',
  `qty` int(255) default '0',
  `unitPrice` double default '0',
  `discount` double default '0',
  `totalAmount` double default '0',
  `per` varchar(100) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `salesitem` */

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(1000) default '',
  `address1` varchar(10000) default '',
  `address2` varchar(10000) default '',
  `city` varchar(1000) default '',
  `stateCode` varchar(1000) default '',
  `GSTNo` varchar(1000) default '',
  `purchaseInvoiceNo` varchar(1000) default '',
  `mobileNo` varchar(30) default '0',
  `email` varchar(1000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `supplier` */

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(1000) default '',
  `password` varchar(1000) default '',
  `add1` varchar(1000) default '',
  `add2` varchar(1000) default '',
  `add3` varchar(1000) default '',
  `add4` varchar(1000) default '',
  `GSTNo` varchar(1000) default '',
  `mobileNo` varchar(1000) default '',
  `email` varchar(1000) default '',
  `bankName` varchar(1000) default '',
  `bankBranch` varchar(1000) default '',
  `IFSCNo` varchar(1000) default '',
  `ActNo` varchar(1000) default '',
  `tnc1` varchar(1000) default '',
  `tnc2` varchar(1000) default '',
  `tnc3` varchar(1000) default '',
  `tnc4` varchar(1000) default '',
  `tnc5` varchar(1000) default '',
  `tnc6` varchar(1000) default '',
  `businessName` varchar(1000) default '',
  `subTo` varchar(1000) default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`username`,`password`,`add1`,`add2`,`add3`,`add4`,`GSTNo`,`mobileNo`,`email`,`bankName`,`bankBranch`,`IFSCNo`,`ActNo`,`tnc1`,`tnc2`,`tnc3`,`tnc4`,`tnc5`,`tnc6`,`businessName`,`subTo`) values (1,'demo','demo','UAccounting','','','','','','','','','','','','','','','','','','SUBJECT TO AHMEDABAD JURIDICTION');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
