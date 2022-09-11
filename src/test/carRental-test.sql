CREATE DATABASE IF NOT EXISTS car_rental_test_db;

-- 创建 table_customer 数据表
DROP TABLE IF EXISTS `car_rental_test_db`.`table_customer`;

CREATE TABLE IF NOT EXISTS `car_rental_test_db`.`table_customer` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `first_name` varchar(64) NOT NULL DEFAULT '',
    `middle_name` varchar(64) DEFAULT '',
    `last_name` varchar(64) NOT NULL DEFAULT '',
    `email` varchar(64) NOT NULL DEFAULT '',
    `salt` varchar(10) NOT NULL DEFAULT '',
    `password` varchar(256) NOT NULL DEFAULT '' COMMENT 'hashed password',
    `create_time` datetime NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (`id`),
    KEY `idx_email` (`email`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='customer table';


insert into
    `car_rental_test_db`.`table_customer`(`id`,`first_name`,`middle_name`,`last_name`,`email`,`salt`,`password`)
values (1,"sven","eig","doglas", "sveneigdoglas@exmaple.com", "94c0d3","bea7577f575fa5fed776410679451f74");


DROP TABLE if exists  `car_rental_test_db`.`table_car_category` ;
CREATE TABLE IF NOT EXISTS `car_rental_test_db`.`table_car_category` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `category_name` varchar(64) NOT NULL DEFAULT '',
    `description` varchar(256) NOT NULL DEFAULT '' COMMENT 'car category description',
    `create_time` datetime NOT NULL DEFAULT current_timestamp ,
    PRIMARY KEY (`id`),
    KEY `idx_category_name` (`category_name`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='car category table';


insert into
    `car_rental_test_db`.`table_car_category`(`id`,`category_name`,`description`)
values (1,"Toyota Camry","Toyota Camry"),(2, "BMW 650", "BMW 650");


DROP TABLE if exists  `car_rental_test_db`.`table_car` ;
CREATE TABLE IF NOT EXISTS `car_rental_test_db`.`table_car` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `category_id` int(20) NOT NULL DEFAULT '0' COMMENT 'car category id',
    `create_time` datetime NOT NULL DEFAULT current_timestamp ,
    `plate` varchar(64) NOT NULL COMMENT 'car license plate number',
    `status` int(2) NOT NULL DEFAULT 0 COMMENT 'car status 0-not available, 1-ready, 2-inuse, 3-repair',
    PRIMARY KEY (`id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_status` (`status`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='car table';

insert into
    `car_rental_test_db`.`table_car`(`id`,`category_id`,`plate`,`status`)
values (1, 1, "TC7698", 1),(2, 1, "TC7998", 1),(3, 2, "BMW998", 1),(4, 2, "BMW654", 1);

DROP TABLE if exists  `car_rental_test_db`.`table_rental_order` ;
CREATE TABLE IF NOT EXISTS `car_rental_test_db`.`table_rental_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `car_id` int(20) NOT NULL DEFAULT '0' COMMENT 'car id',
    `category_id` int(20) NOT NULL DEFAULT '0' COMMENT 'car category id',
    `customer_id` int(20) NOT NULL DEFAULT '0' COMMENT 'customer id',
    `create_time` datetime NOT NULL DEFAULT current_timestamp ,
    `update_time` datetime NOT NULL DEFAULT current_timestamp ,
    `start_time` datetime NOT NULL COMMENT 'car rental order start time',
    `end_time` datetime NOT NULL COMMENT 'car rental order end time',
    `status` int(2) NOT NULL DEFAULT 1 COMMENT 'order status 1-normal, 0-cancelled',
    PRIMARY KEY (`id`),
    KEY `idx_car_id` (`car_id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_start_time` (`start_time`),
    KEY `idx_end_time` (`end_time`),
    KEY `idx_status` (`status`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='car rental order table';
