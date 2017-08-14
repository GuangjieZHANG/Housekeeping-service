-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2017 at 02:04 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `housework`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `accountId` varchar(10) NOT NULL,
  `name` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `roleId` smallint(6) NOT NULL,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountId`, `name`, `password`, `roleId`, `addTime`, `modifyTime`) VALUES
('010101', 'admin', '010101', 1, '2017-03-24 10:08:52', '2017-03-24 02:08:52'),
('123456', 'root', '123456', 1, '2017-03-09 00:00:00', '2017-03-08 08:34:27');

-- --------------------------------------------------------

--
-- Table structure for table `leaverecord`
--

CREATE TABLE `leaverecord` (
  `leaveId` varchar(32) NOT NULL,
  `workerId` varchar(32) NOT NULL,
  `beginTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `description` text NOT NULL,
  `addTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menuId` smallint(6) NOT NULL,
  `isLeaf` smallint(6) DEFAULT NULL,
  `name` text,
  `pageno` text,
  `parentno` smallint(6) DEFAULT NULL,
  `url` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menuId`, `isLeaf`, `name`, `pageno`, `parentno`, `url`) VALUES
(1, 0, '系统管理', '1', 0, '1'),
(2, 0, '信息管理', '1', 0, '1'),
(3, 0, '订单管理', '1', 0, '1'),
(101, 1, '密码修改', 'LOGINMANAGE', 1, 'Look_PWD'),
(102, 1, '管理员', 'ACCOUNTMANAGE', 1, 'accountmanage'),
(201, 1, '阿姨', 'WORKERMANAGE', 2, 'workermanage'),
(202, 1, '用户', 'USERMANAGE', 2, 'usermanage'),
(203, 1, '请假', 'LEAVEMANAGE', 2, 'leavemanage'),
(301, 1, '未分配订单', 'UNORDERMANAGE', 3, 'unordermanage'),
(302, 1, '进行中订单', 'ORDERINGMANAGE', 3, 'orderingmanage'),
(303, 1, '已完成订单', 'ORDEREDMANAGE', 3, 'orderedmanage'),
(304, 1, '已删除订单', 'REMOVEORDERMANAGE', 3, 'removeordermanage'),
(305, 1, '评价', 'REMARKMANAGE', 3, 'remarkmanage');

-- --------------------------------------------------------

--
-- Table structure for table `ordered`
--

CREATE TABLE `ordered` (
  `orderId` varchar(32) NOT NULL,
  `serviceTypeId` varchar(32) NOT NULL,
  `orderName` text,
  `workerId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `address` text NOT NULL,
  `longitude` double(10,6) NOT NULL,
  `latitude` double(10,6) NOT NULL,
  `predictTime` datetime NOT NULL,
  `startTime` datetime NOT NULL,
  `finishTime` datetime NOT NULL,
  `isPaid` tinyint(1) NOT NULL DEFAULT '0',
  `cost` double(22,2) NOT NULL DEFAULT '0.00',
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ordered`
--

INSERT INTO `ordered` (`orderId`, `serviceTypeId`, `orderName`, `workerId`, `userId`, `address`, `longitude`, `latitude`, `predictTime`, `startTime`, `finishTime`, `isPaid`, `cost`, `addTime`, `modifyTime`) VALUES
('97078851317792768', '97048432581017600', NULL, '8a8a82e05ad1b147015ad1b2ba300000', '8a00b3825a513224015a5132856b0000', '西安市友谊西路127号', 87.120000, 87.230000, '2017-04-05 00:00:00', '2017-04-05 04:00:00', '2017-04-05 19:00:00', 0, 0.00, '2017-04-05 21:23:35', '2017-04-05 13:25:08');

-- --------------------------------------------------------

--
-- Table structure for table `ordering`
--

CREATE TABLE `ordering` (
  `orderId` varchar(32) NOT NULL,
  `serviceTypeId` varchar(32) NOT NULL,
  `orderName` text,
  `workerId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `address` text NOT NULL,
  `longitude` double(10,6) NOT NULL,
  `latitude` double(10,6) NOT NULL,
  `predictTime` datetime NOT NULL,
  `startTime` datetime NOT NULL,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `remark`
--

CREATE TABLE `remark` (
  `remarkId` varchar(32) NOT NULL,
  `orderId` varchar(32) NOT NULL,
  `level` smallint(6) NOT NULL,
  `content` text NOT NULL,
  `sendId` varchar(32) NOT NULL,
  `receId` varchar(32) NOT NULL,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `remark`
--

INSERT INTO `remark` (`remarkId`, `orderId`, `level`, `content`, `sendId`, `receId`, `addTime`, `modifyTime`) VALUES
('97078851317792769', '97078851317792768', 3, 'great', '8a00b3825a513224015a5132856b0000', '', '2017-04-05 21:26:07', '2017-04-05 13:26:07');

-- --------------------------------------------------------

--
-- Table structure for table `removeorder`
--

CREATE TABLE `removeorder` (
  `orderId` varchar(32) NOT NULL,
  `serviceTypeId` varchar(32) NOT NULL,
  `orderName` text,
  `workerId` varchar(32) DEFAULT NULL,
  `userId` varchar(32) NOT NULL,
  `address` text NOT NULL,
  `longitude` double(10,6) NOT NULL,
  `latitude` double(10,6) NOT NULL,
  `predictTime` datetime NOT NULL,
  `startTime` datetime DEFAULT NULL,
  `removeTime` datetime NOT NULL,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleId` smallint(6) NOT NULL,
  `realName` text,
  `roleName` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleId`, `realName`, `roleName`) VALUES
(1, '超级管理员', '0'),
(2, '管理员', '0'),
(3, '实验室负责人', '0'),
(4, '成员', '0');

-- --------------------------------------------------------

--
-- Table structure for table `role_menu`
--

CREATE TABLE `role_menu` (
  `roleId` smallint(6) NOT NULL,
  `menuId` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role_menu`
--

INSERT INTO `role_menu` (`roleId`, `menuId`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 3),
(2, 3),
(1, 101),
(2, 101),
(1, 102),
(1, 201),
(2, 201),
(1, 202),
(2, 202),
(1, 203),
(2, 203),
(1, 301),
(2, 301),
(1, 302),
(2, 302),
(1, 303),
(2, 303),
(1, 304),
(2, 304),
(1, 305),
(2, 305);

-- --------------------------------------------------------

--
-- Table structure for table `servicetype`
--

CREATE TABLE `servicetype` (
  `serviceTypeId` varchar(32) NOT NULL,
  `serviceTypeName` text NOT NULL,
  `description` text NOT NULL,
  `userPrice` double(22,2) NOT NULL,
  `workerPrice` double(22,2) NOT NULL,
  `addtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `servicetype`
--

INSERT INTO `servicetype` (`serviceTypeId`, `serviceTypeName`, `description`, `userPrice`, `workerPrice`, `addtime`, `modifyTime`) VALUES
('97048432581017600', '日常保洁', '日常保洁是指在保洁服务中，每日需要清洁的项目内容。日常保洁工作由扫、擦、洗等各种作业组成，通过日常保洁可以减少物业装饰材料的自然损坏和人为磨损，延长物业的使用年限，保持物业美观的效果。', 100.00, 100.00, '2017-03-15 21:09:02', '2017-04-04 02:21:43'),
('97048432581017601', '大扫除', '本公司提供的大扫除服务旨在为业主创造一个干净卫生的生活环境。通过一次彻底的大扫除，您的家中死角会被打扫干净，还要擦玻璃等等', 100.00, 100.00, '2017-03-15 21:09:02', '2017-04-04 02:22:46'),
('97048432581017602', '真皮沙发保养', '真皮沙发保养是我公司提供的一项沙发保养服务，是用来……', 100.00, 100.00, '2017-03-15 21:10:32', '2017-04-04 02:22:53'),
('97048432581017603', '地板打蜡', '地板打蜡是我公司推出的一项服务项目，通过打蜡可以使您的地板更光亮，延长地板使用寿命。', 100.00, 100.00, '2017-03-15 21:10:32', '2017-04-04 02:22:37');

-- --------------------------------------------------------

--
-- Table structure for table `unorder`
--

CREATE TABLE `unorder` (
  `orderId` varchar(32) NOT NULL,
  `workerId` varchar(32) DEFAULT NULL,
  `serviceTypeId` varchar(32) NOT NULL,
  `orderName` text,
  `userId` varchar(32) NOT NULL,
  `address` text NOT NULL,
  `longitude` double(10,6) NOT NULL,
  `latitude` double(10,6) NOT NULL,
  `isReced` tinyint(1) NOT NULL DEFAULT '0',
  `predictTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timer` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unorder`
--

INSERT INTO `unorder` (`orderId`, `workerId`, `serviceTypeId`, `orderName`, `userId`, `address`, `longitude`, `latitude`, `isReced`, `predictTime`, `timer`, `addTime`, `modifyTime`) VALUES
('40283f815b056a08015b0570466f0000', '8a8a819c5afa5477015afa5618630000', '97048432581017600', NULL, '8a8a82c85a50b460015a50b488f70000', '陕西省西安市碑林区教学南路', 108.919309, 34.248220, 0, '2017-03-25 20:27:56', '2017-03-25 20:28:15', '2017-03-25 20:28:15', '2017-03-25 12:28:15'),
('402881e65aecbc96015aecc6e6340001', '8a8a82e05ad1b147015ad1b2ba300000', '97048432581017603', NULL, '8a8a82c85a50b460015a50b488f70000', '陕西省西安市碑林区劳动南路17号二层', 108.916971, 34.250063, 0, '2017-03-18 01:57:55', '2017-03-21 01:58:01', '2017-03-21 01:58:01', '2017-03-23 11:08:24'),
('402881e65aecc9ff015aeccb18200000', '8a8a82c05abc6686015abc67be7c0000', '97048432581017602', NULL, '8a8a82c05abc732d015abcf551820000', '陕西省西安市碑林区劳动南路35号', 108.917060, 34.250429, 0, '2017-03-26 20:48:42', '2017-03-25 20:48:48', '2017-03-25 20:48:48', '2017-03-25 12:48:49'),
('402881e65afc3ad2015afc3c0fc60000', '8a8a819c5afa5477015afa5618630000', '97048432581017600', NULL, '402881e65aecc9ff015aecd9e88a0003', '陕西省西安市莲湖区青年路103号', 108.953098, 34.277800, 0, '2017-03-24 01:34:35', '2017-03-24 01:34:39', '2017-03-24 01:34:39', '2017-03-23 17:34:39'),
('8a8a819a5b033aa1015b034928990000', '8a8a819c5afa5477015afa5618630000', '97048432581017600', NULL, '8a00b3825a513224015a5132856b0000', '陕西省宝鸡市金台区滨河北路', 107.170645, 34.364081, 0, '2017-03-25 10:26:03', '2017-03-25 10:26:17', '2017-03-25 10:26:17', '2017-03-25 02:26:17'),
('8a8a819a5b033aa1015b034970460001', '8a8a819c5afa5477015afa5618630000', '97048432581017601', NULL, '8a00b3825a513224015a5132856b0000', '陕西省宝鸡市金台区滨河北路', 107.170645, 34.364081, 0, '2017-03-25 10:26:32', '2017-03-25 10:26:36', '2017-03-25 10:26:36', '2017-03-25 02:26:36');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` varchar(32) NOT NULL,
  `userName` text NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` text,
  `photo` longblob,
  `longitude` double(10,6) DEFAULT NULL,
  `latitude` double(10,6) DEFAULT NULL,
  `addTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `longtitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userName`, `phoneNumber`, `password`, `address`, `photo`, `longitude`, `latitude`, `addTime`, `modifyTime`, `longtitude`) VALUES
('402881e65aecc9ff015aecd9e88a0003', 'user6', '18888443365', '18888443366', '西安市友谊西路127号', NULL, NULL, NULL, NULL, '2017-03-20 09:53:19', NULL),
('8a00b3825a513224015a5132856b0000', 'user1', '18181818181', '123456', '西安市友谊西路127号', NULL, 87.120000, 87.230000, '2017-03-01 00:00:00', '2017-03-25 11:53:18', NULL),
('8a8a82c05abc3362015abc36a7860001', 'user3', '15929800187', '15929800187', '西安市友谊西路127号', NULL, NULL, NULL, '2017-03-11 15:13:02', '2017-03-10 08:19:53', NULL),
('8a8a82c05abc732d015abcf551820000', 'user5', '13456795555', '13456795555', '西安市友谊西路127号西安市友谊西路127号', NULL, NULL, NULL, '2017-03-11 18:41:18', '2017-03-10 10:41:18', NULL),
('8a8a82c85a50b460015a50b488f70000', 'user2', '15929800186', '123456', '西安市友谊西路127号', NULL, 87.120000, 87.230000, '2017-03-01 00:00:00', '2017-03-25 12:05:48', NULL),
('8a8a82e05ad1b147015ad1b3c0fc0001', 'user4', '13611111111', '13611111111', '友谊校区地址：西安市友谊西路127号', NULL, NULL, NULL, '2017-03-15 19:21:42', '2017-03-14 19:21:42', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `workerId` varchar(32) NOT NULL,
  `number` varchar(10) DEFAULT NULL,
  `workerName` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `cardID` varchar(18) NOT NULL,
  `age` smallint(6) NOT NULL,
  `sex` text NOT NULL,
  `brief` text NOT NULL,
  `address` text,
  `photo` longblob,
  `longitude` double(10,6) DEFAULT NULL,
  `latitude` double(10,6) DEFAULT NULL,
  `addtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `latitudde` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`workerId`, `number`, `workerName`, `password`, `phoneNumber`, `cardID`, `age`, `sex`, `brief`, `address`, `photo`, `longitude`, `latitude`, `addtime`, `modifyTime`, `latitudde`) VALUES
('40283f815b056a08015b058221e80001', NULL, 'worker10', '000000', '18881814699', '513030199507000000', 31, '男', '陕西省西安市碑林区教学北路', '陕西省西安市碑林区教学北路', NULL, 108.921357, 34.249433, '2017-03-25 20:47:46', '2017-03-25 12:47:46', NULL),
('8a8a819c5afa5477015afa5618630000', NULL, 'worker1', '233333', '18222222222', '513030198001233333', 37, '女', '陕西省宝鸡市金台区滨河北路', '陕西省宝鸡市金台区滨河北路', NULL, 107.170645, 34.364081, '2017-03-23 16:43:50', '2017-03-23 08:43:50', NULL),
('8a8a819c5afa5477015afa57d0320001', NULL, 'worker2', '123456', '15929800186', '513030198501110011', 32, '女', '宝鸡市金台区跃进铁路小区', '宝鸡市金台区跃进铁路小区', NULL, 107.180184, 34.367570, '2017-03-23 16:45:43', '2017-03-25 12:12:40', NULL),
('8a8a82c05abc6686015abc67be7c0000', NULL, 'worker4', '111222', '13222222222', '513030199008080000', 27, '男', '友谊校区地址：西安市友谊西路127号', '陕西省西安市碑林区求实路', NULL, 108.922283, 34.249858, '2017-03-01 00:00:00', '2017-03-23 09:02:53', NULL),
('8a8a82e05ad1b147015ad1b2ba300000', NULL, 'worker5', '111111', '13211111115', '513030198701110011', 30, '男', '友谊校区地址：西安市友谊西路127号', '陕西省西安市碑林区教学北路', NULL, 108.922216, 34.248924, '2017-03-08 08:00:00', '2017-03-23 09:02:58', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `workerservice`
--

CREATE TABLE `workerservice` (
  `workerId` varchar(32) NOT NULL,
  `serviceTypeId` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workerservice`
--

INSERT INTO `workerservice` (`workerId`, `serviceTypeId`) VALUES
('40283f815b056a08015b058221e80001', '97048432581017600'),
('8a8a819c5afa5477015afa5618630000', '97048432581017600'),
('8a8a819c5afa5477015afa57d0320001', '97048432581017600'),
('8a8a819c5afa5477015afa5618630000', '97048432581017601'),
('40283f815b056a08015b058221e80001', '97048432581017602'),
('8a8a82c05abc6686015abc67be7c0000', '97048432581017602'),
('8a8a819c5afa5477015afa57d0320001', '97048432581017603'),
('8a8a82c05abc6686015abc67be7c0000', '97048432581017603'),
('8a8a82e05ad1b147015ad1b2ba300000', '97048432581017603');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accountId`),
  ADD KEY `FK_AccountLinkRole` (`roleId`);

--
-- Indexes for table `leaverecord`
--
ALTER TABLE `leaverecord`
  ADD PRIMARY KEY (`leaveId`),
  ADD KEY `FK_leaveLinkworker` (`workerId`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menuId`);

--
-- Indexes for table `ordered`
--
ALTER TABLE `ordered`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `FK_orderedLinkService` (`serviceTypeId`),
  ADD KEY `FK_orderedLinkUser` (`userId`),
  ADD KEY `FK_orderedLinkWorker` (`workerId`);

--
-- Indexes for table `ordering`
--
ALTER TABLE `ordering`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `FK_orderingLinkService` (`serviceTypeId`),
  ADD KEY `FK_orderingLinkUser` (`userId`),
  ADD KEY `FK_orderingLinkWorker` (`workerId`);

--
-- Indexes for table `remark`
--
ALTER TABLE `remark`
  ADD PRIMARY KEY (`remarkId`),
  ADD KEY `FK_remarkLinkOrdered` (`orderId`);

--
-- Indexes for table `removeorder`
--
ALTER TABLE `removeorder`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `FK_removeOrderLinkService` (`serviceTypeId`),
  ADD KEY `FK_removeOrderLinkUser` (`userId`),
  ADD KEY `FK_removeOrderLinkWorker` (`workerId`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `role_menu`
--
ALTER TABLE `role_menu`
  ADD PRIMARY KEY (`roleId`,`menuId`),
  ADD KEY `FK_LinkMenu` (`menuId`);

--
-- Indexes for table `servicetype`
--
ALTER TABLE `servicetype`
  ADD PRIMARY KEY (`serviceTypeId`);

--
-- Indexes for table `unorder`
--
ALTER TABLE `unorder`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `FK_unorderLinkService` (`serviceTypeId`),
  ADD KEY `FK_unorderLinkUser` (`userId`),
  ADD KEY `FK_unorderLinkWorker` (`workerId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`workerId`);

--
-- Indexes for table `workerservice`
--
ALTER TABLE `workerservice`
  ADD PRIMARY KEY (`workerId`,`serviceTypeId`),
  ADD KEY `FK_Reference_19` (`serviceTypeId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_AccountLinkRole` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`);

--
-- Constraints for table `leaverecord`
--
ALTER TABLE `leaverecord`
  ADD CONSTRAINT `FK_leaveLinkworker` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`);

--
-- Constraints for table `ordered`
--
ALTER TABLE `ordered`
  ADD CONSTRAINT `FK_orderedLinkService` FOREIGN KEY (`serviceTypeId`) REFERENCES `servicetype` (`serviceTypeId`),
  ADD CONSTRAINT `FK_orderedLinkUser` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FK_orderedLinkWorker` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`);

--
-- Constraints for table `ordering`
--
ALTER TABLE `ordering`
  ADD CONSTRAINT `FK_orderingLinkService` FOREIGN KEY (`serviceTypeId`) REFERENCES `servicetype` (`serviceTypeId`),
  ADD CONSTRAINT `FK_orderingLinkUser` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FK_orderingLinkWorker` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`);

--
-- Constraints for table `remark`
--
ALTER TABLE `remark`
  ADD CONSTRAINT `FK_remarkLinkOrdered` FOREIGN KEY (`orderId`) REFERENCES `ordered` (`orderId`);

--
-- Constraints for table `removeorder`
--
ALTER TABLE `removeorder`
  ADD CONSTRAINT `FK_removeOrderLinkService` FOREIGN KEY (`serviceTypeId`) REFERENCES `servicetype` (`serviceTypeId`),
  ADD CONSTRAINT `FK_removeOrderLinkUser` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FK_removeOrderLinkWorker` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`);

--
-- Constraints for table `role_menu`
--
ALTER TABLE `role_menu`
  ADD CONSTRAINT `FK_LinkMenu` FOREIGN KEY (`menuId`) REFERENCES `menu` (`menuId`),
  ADD CONSTRAINT `FK_LinkRole` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`);

--
-- Constraints for table `unorder`
--
ALTER TABLE `unorder`
  ADD CONSTRAINT `FK_unorderLinkService` FOREIGN KEY (`serviceTypeId`) REFERENCES `servicetype` (`serviceTypeId`),
  ADD CONSTRAINT `FK_unorderLinkUser` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FK_unorderLinkWorker` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`);

--
-- Constraints for table `workerservice`
--
ALTER TABLE `workerservice`
  ADD CONSTRAINT `FK_Reference_18` FOREIGN KEY (`workerId`) REFERENCES `worker` (`workerId`),
  ADD CONSTRAINT `FK_Reference_19` FOREIGN KEY (`serviceTypeId`) REFERENCES `servicetype` (`serviceTypeId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
