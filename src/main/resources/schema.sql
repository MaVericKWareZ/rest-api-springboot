DROP TABLE IF EXISTS CUSTOMERS;
CREATE TABLE CUSTOMERS (
  CUSTOMER_ID INT(10) NOT NULL,
  CUSTOMER_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL,
  ADDRESS VARCHAR(250),
  CURRENCY VARCHAR(20),
  PRIMARY KEY (CUSTOMER_ID)
);
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
  ORDER_ID INT(10) NOT NULL,
  DESCRIPTION VARCHAR(250),
  QUANTITY VARCHAR(250) NOT NULL,
  PRICE INT(10) NOT NULL,
  CUSTOMER_ID INT(10) NOT NULL,
  PRIMARY KEY (ORDER_ID)
);

ALTER TABLE ORDERS
ADD FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(CUSTOMER_ID);