
CREATE SCHEMA `TOOLING`;
USE `TOOLING`;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `emp_id` bigint NOT NULL,
                         `user_name` varchar(100) NOT NULL,
                         `password` varchar(100) DEFAULT NULL,
                         `full_name` varchar(255) DEFAULT NULL,
                         `phone` varchar(255) DEFAULT NULL,
                         `role` int DEFAULT NULL,
                         `active` int DEFAULT NULL,
                         `creation_date` varchar(100) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `emp_id_UNIQUE` (`emp_id`),
                         UNIQUE KEY `user_name_UNIQUE` (`user_name`),
                         UNIQUE KEY `index2` (`phone`),
                         UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;