# create schema shoppingList;
#
# use shoppingList;
DROP TABLE IF EXISTS users, products, baskets, addresses, user_addresses, user_items, productList, product_list;

CREATE TABLE users
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    name     VARCHAR(20) NOT NULL,
    email    VARCHAR(50) UNIQUE,
    password VARCHAR(60) /*NOT NULL*/,
    phone    VARCHAR(50) UNIQUE,
    created  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    #TODO    user_permission_level INT DEFAULT 1,

    PRIMARY KEY (id),
    INDEX (name, email)
);

CREATE TABLE roles (
    id TINYINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    role_id TINYINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT UNIQUE uc_user_roles (user_id, role_id),
    INDEX (user_id)
);

CREATE TABLE products
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    sku         VARCHAR(15),
    name        VARCHAR(35) NOT NULL,
    price       DECIMAL     NOT NULL,
    discount    DECIMAL   DEFAULT 0,
    description VARCHAR(500),
    created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE (name),
    INDEX (name, sku)
);

CREATE TABLE user_items
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    user_id    BIGINT NOT NULL,
    product_id BIGINT,
    quantity   INT      DEFAULT 1,
    created    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT UNIQUE uc_user_items (user_id, product_id),
    INDEX (user_id)
);

/*CREATE TABLE baskets
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    promocode_id BIGINT,
    note VARCHAR(500),

    PRIMARY KEY (id),
    CONSTRAINT UNIQUE uc_baskets (id, user_id)
);*/

/*CREATE TABLE addresses(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    street      VARCHAR(40) NOT NULL,
    building_nr VARCHAR(20) NOT NULL,
    apt_nr      VARCHAR(20),
    state       VARCHAR(20),
    city        VARCHAR(20) NOT NULL ,
    zip_code    VARCHAR(20) NOT NULL ,
    country     VARCHAR(30) NOT NULL ,

    PRIMARY KEY (id),
    CONSTRAINT uc_addresses UNIQUE (street, building_nr, apt_nr, city, zip_code, country)
);*/

/*CREATE TABLE user_address(
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (address_id) REFERENCES addresses (id)
);*/

/*CREATE TABLE orders(
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    user_id          BIGINT    NOT NULL,
    date       DATE      NOT NULL,
    time       TIME      NOT NULL,
    user_session     TIMESTAMP NOT NULL, #todo
    address    BIGINT    NOT NULL,
    status     VARCHAR(10) DEFAULT 'paid',


    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);*/

/*CREATE TABLE order_products(
    id   BIGINT  NOT NULL AUTO_INCREMENT,
    order_id         BIGINT  NOT NULL,
    product_id       BIGINT  NOT NULL,
    product_price    DECIMAL NOT NULL,
    product_quantity INT  NOT NULL,
    user_session_id  VARCHAR(50),

    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (product_price) REFERENCES products (price),
#     FOREIGN KEY (user_session_id) REFERENCES user_sessions (id),
    CONSTRAINT uc_order_products UNIQUE (order_id, product_id)
);*/

/*CREATE TABLE collections(
    collection_id BIGINT NOT NULL AUTO_INCREMENT,
    collection_name VARCHAR(20) NOT NULL,

    PRIMARY KEY (collection_id)
);*/

/*CREATE TABLE product_collections(
    product_collection_id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (product_collection_id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);*/

INSERT INTO users (id, name, email, password)
VALUES (1, 'name1', 'email1', 'password1');
INSERT INTO users (id, name, email, password)
VALUES (2, 'name2', 'email2', 'password2');
INSERT INTO users (id, name, email, password)
VALUES (3, 'name3', 'email3', 'password3');
INSERT INTO users (id, name, email, password)
VALUES (4, 'name4', 'email4', 'password4');
INSERT INTO users (id, name, email, password)
VALUES (5, 'name5', 'email5', 'password5');
INSERT INTO users (id, name, email, password)
VALUES (6, 'name6', 'email6', 'password6');
INSERT INTO users (id, name, email, password)
VALUES (7, 'name7', 'email7', 'password7');
INSERT INTO users (id, name, email, password)
VALUES (8, 'name8', 'email8', 'password8');

/*INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_1', 'building_nr_1', 'city_1', 'zip_1', 'country_1');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_2', 'building_nr_2', 'city_2', 'zip_2', 'country_2');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_3', 'building_nr_3', 'city_3', 'zip_3', 'country_3');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_4', 'building_nr_4', 'city_4', 'zip_4', 'country_4');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_5', 'building_nr_5', 'city_5', 'zip_5', 'country_5');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_6', 'building_nr_6', 'city_6', 'zip_6', 'country_6');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_7', 'building_nr_7', 'city_7', 'zip_7', 'country_7');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_8', 'building_nr_8', 'city_8', 'zip_8', 'country_8');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_9', 'building_nr_9', 'city_9', 'zip_9', 'country_9');
INSERT INTO addresses (street, building_nr, city, zip_code, country) VALUES ('street_10', 'building_nr_10', 'city_10', 'zip_10', 'country_10');*/

/*INSERT INTO user_address (user_id, address_id) VALUES (1, 1);
INSERT INTO user_address (user_id, address_id) VALUES (2, 2);
INSERT INTO user_address (user_id, address_id) VALUES (3, 3);
INSERT INTO user_address (user_id, address_id) VALUES (4, 4);
INSERT INTO user_address (user_id, address_id) VALUES (5, 5);
INSERT INTO user_address (user_id, address_id) VALUES (6, 6);
INSERT INTO user_address (user_id, address_id) VALUES (7, 7);
INSERT INTO user_address (user_id, address_id) VALUES (8, 8);
INSERT INTO user_address (user_id, address_id) VALUES (9, 9);
INSERT INTO user_address (user_id, address_id) VALUES (10, 10);*/

INSERT INTO products (id, name, price, discount)
VALUES (1, 'name1', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (2, 'name2', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (3, 'name3', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (4, 'name4', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (5, 'name5', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (6, 'name6', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (7, 'name7', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (8, 'name8', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (9, 'name9', 100, 10);
INSERT INTO products (id, name, price, discount)
VALUES (10, 'name10', 100, 10);

INSERT INTO user_items (user_id, product_id, quantity)
VALUES (1, 1, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (1, 2, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (1, 3, 4);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (1, 8, 5);

INSERT INTO user_items (user_id, product_id, quantity)
VALUES (2, 1, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (2, 2, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (2, 3, 4);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (2, 8, 5);

INSERT INTO user_items (user_id, product_id, quantity)
VALUES (4, 1, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (4, 2, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (4, 3, 4);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (4, 8, 5);

INSERT INTO user_items (user_id, product_id, quantity)
VALUES (7, 1, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (7, 2, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (7, 3, 4);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (7, 8, 5);

INSERT INTO user_items (user_id, product_id, quantity)
VALUES (3, 1, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (5, 2, 1);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (6, 3, 4);
INSERT INTO user_items (user_id, product_id, quantity)
VALUES (8, 8, 5);

INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES (1,1);
INSERT INTO user_roles (user_id, role_id) VALUES (2,1);
INSERT INTO user_roles (user_id, role_id) VALUES (3,1);
INSERT INTO user_roles (user_id, role_id) VALUES (4,1);
INSERT INTO user_roles (user_id, role_id) VALUES (5,1);
INSERT INTO user_roles (user_id, role_id) VALUES (6,1);
INSERT INTO user_roles (user_id, role_id) VALUES (7,1);
INSERT INTO user_roles (user_id, role_id) VALUES (8,1);
INSERT INTO user_roles (user_id, role_id) VALUES (9,1);
INSERT INTO user_roles (user_id, role_id) VALUES (11,2);

ALTER TABLE users DROP COLUMN role;