-- sql/init.sql
-- Executed by H2 at DataSource creation time (via INIT= in the JDBC URL)
-- Creates tables and seeds data for Lab 3

CREATE TABLE IF NOT EXISTS products (
    id       VARCHAR(20)    PRIMARY KEY,
    name     VARCHAR(100)   NOT NULL,
    category VARCHAR(50)    NOT NULL,
    price    DECIMAL(10,2)  NOT NULL
);

CREATE TABLE IF NOT EXISTS audit_log (
    id         INT            AUTO_INCREMENT PRIMARY KEY,
    action     VARCHAR(20)    NOT NULL,
    entity_id  VARCHAR(50)    NOT NULL,
    created_at TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
);

-- Seed products
INSERT INTO products(id, name, category, price) VALUES
    ('P001', 'Clean Code',           'books',       39.99),
    ('P002', 'Effective Java',       'books',       49.99),
    ('P003', 'Java 11 Pocket Guide', 'books',       24.99),
    ('P004', 'Mechanical Keyboard',  'electronics', 129.99),
    ('P005', 'USB-C Hub',            'electronics',  34.99),
    ('P006', 'Old Java EE 5 Book',   'books',         9.99)
ON CONFLICT(id) DO NOTHING;
