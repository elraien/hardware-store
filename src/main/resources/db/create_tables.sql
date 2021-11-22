DROP DATABASE IF EXISTS `hardware_store`;
CREATE DATABASE `hardware_store`;
USE `hardware_store`;

SET NAMES utf8;
SET character_set_client = utf8mb4;



create TABLE users
(
    user_id   INT PRIMARY KEY auto_increment NOT NULL,
    user_name VARCHAR(30)                    NOT NULL,
    email     VARCHAR(30)                    NOT NULL,
    role      VARCHAR(30)                    NOT NULL
);

insert into users (user_id, user_name, email, role)
values (1, 'user1', 'user1@gmail.com', 'ADMIN'),
       (2, 'user2', 'user2@gmail.com', 'CUSTOMER');

create TABLE news
(
    id          INT PRIMARY KEY auto_increment,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL
);

create TABLE products
(
    id          INT PRIMARY KEY auto_increment,
    name        VARCHAR(100)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255)
);

insert into products (id, name, price, description)
values (1, 'Motherboard', 2500.45, null),
       (2, 'RAM', 3500.78, null),
       (3, 'HDD', 550.23, null);

create TABLE carts
(
    cart_id    INT PRIMARY KEY auto_increment,
    total_sum  DECIMAL(10, 2),
    user_id_fk int,
    FOREIGN KEY (user_id_fk) REFERENCES users (user_id) ON DELETE CASCADE
);

insert into carts (cart_id, total_sum, user_id_fk)
values (1, 2500.45, 1),
       (2, 3500.78, 2),
       (3, 550.23, 1);

create TABLE order_items
(
    id                     INT PRIMARY KEY auto_increment,
    product_id_fk          INT,
    quantity               INT,
    product_price          DECIMAL(10, 2),
    total_order_item_price DECIMAL(10, 2),
    cart_id_fk             INT,
    FOREIGN KEY (cart_id_fk) REFERENCES carts (cart_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id_fk) REFERENCES products (id) ON DELETE CASCADE
);

insert into order_items (id, product_id_fk, quantity, product_price, total_order_item_price, cart_id_fk)
values (DEFAULT, 1, 2, 1000, quantity*product_price, 1),
       (DEFAULT, 2, 5, 500, quantity*product_price, 2),
       (DEFAULT, 3, 4,  550.23, quantity*product_price, 1);

create TABLE orders
(
    order_id   INT PRIMARY KEY auto_increment,
    orderDate  timestamp NOT NULL DEFAULT NOW(),
    user_id_fk int,
    cart_id_fk int,
    FOREIGN KEY (user_id_fk) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (cart_id_fk) REFERENCES carts (cart_id) ON DELETE CASCADE
);

insert into orders (order_id, orderDate, user_id_fk, cart_id_fk)
values (DEFAULT, DEFAULT, 2, 2),
       (DEFAULT, DEFAULT, 1, 1),
       (DEFAULT, DEFAULT, 1, 1);
