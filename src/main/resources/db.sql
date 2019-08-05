
CREATE SCHEMA `restapi` ;

 CREATE TABLE `restapi`.`books` (   `id` INT NOT NULL AUTO_INCREMENT,   `name` VARCHAR(100) NOT NULL,   `author` VARCHAR(100) NOT NULL,   `price` DOUBLE NOT NULL,   `year` INT NOT NULL,   `upload_url` VARCHAR(250) NOT NULL,   `created_on` DATETIME NULL,   `modified_on` DATETIME NOT NULL,   PRIMARY KEY (`id`)); 	

