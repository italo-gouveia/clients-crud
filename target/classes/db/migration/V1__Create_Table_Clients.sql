CREATE TABLE IF NOT EXISTS `clients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `phone` varchar(17) NOT NULL,
  `address` varchar(100) NOT NULL,
  `house_number` varchar(10) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `postal_code` varchar(8) NOT NULL,
  PRIMARY KEY (`id`)
)