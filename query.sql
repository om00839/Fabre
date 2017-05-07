insert into web.product (name, description, image, price) values ('cartoon movie', 'funny movie', 'D:\\workspace\\lis3306\\web\\image2.jpg', 4.11);

select * from web.product where description like '%fresh%';
select * from web.product;

select * from web.user;

select * from web.user where user_id='tedd' AND password='tedd';

insert into web.user(user_id, password, first_name, last_name) values ('tedd', 'tedd', 'Tedd', 'Bodimer');

DROP TABLE IF EXISTS `web`.`product`;
CREATE TABLE  `web`.`product` (
  `product_id` int(15) NOT NULL AUTO_INCREMENT,
  `description` varchar(300) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;