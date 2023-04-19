DROP TABLE IF EXISTS `ecom`.`customers`;
DROP TABLE IF EXISTS `ecom`.`products`;
DROP TABLE IF EXISTS `ecom`.`orders`;
DROP TABLE IF EXISTS `ecom`.`order_product`;

CREATE TABLE `ecom`.`customers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `phone` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE products (
  id BIGINT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   price DOUBLE NOT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE ecom.order_product (
   order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL
);

CREATE TABLE ecom.`orders` (
  id BIGINT AUTO_INCREMENT NOT NULL,
  orderDate date NULL,
  totalPrice DOUBLE NOT NULL,
  customer_id BIGINT NOT NULL ,
  CONSTRAINT pk_orders PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES ecom.`customers`(id)
);

ALTER TABLE ecom.order_product ADD CONSTRAINT fk_ordpro_on_order FOREIGN KEY (order_id) REFERENCES ecom.orders (id);

ALTER TABLE ecom.order_product ADD CONSTRAINT fk_ordpro_on_product FOREIGN KEY (product_id) REFERENCES ecom.products (id);
