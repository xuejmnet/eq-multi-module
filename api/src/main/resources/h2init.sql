CREATE TABLE IF NOT EXISTS `t_user` (
    `id` VARCHAR (100) PRIMARY KEY,
    `name` VARCHAR (100),
    `create_time` DATETIME
    );

CREATE TABLE IF NOT EXISTS `t_role` (
    `id` VARCHAR (100) PRIMARY KEY,
    `name` VARCHAR (100),
    `create_time` DATETIME
    );

CREATE TABLE IF NOT EXISTS `t_user_role` (
    `id` VARCHAR (100) PRIMARY KEY,
    `user_id` VARCHAR (100),
    `role_id` VARCHAR (100)
    );

CREATE TABLE IF NOT EXISTS `t_menu` (
    `id` VARCHAR (100) PRIMARY KEY,
    `name` VARCHAR (100),
    `route` VARCHAR (100),
    `icon` VARCHAR (100)
    );

CREATE TABLE IF NOT EXISTS `t_role_menu` (
    `id` VARCHAR (100) PRIMARY KEY,
    `role_id` VARCHAR (100),
    `menu_id` VARCHAR (100)
    );

CREATE TABLE IF NOT EXISTS `t_user_address` (
    `id` VARCHAR (100) PRIMARY KEY,
    `user_id` VARCHAR (100),
    `province` VARCHAR (100),
    `city` VARCHAR (100),
    `area` VARCHAR (100),
    `addr` VARCHAR (100)
    );
