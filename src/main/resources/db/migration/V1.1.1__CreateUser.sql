CREATE TABLE user(
    id SERIAL AUTO_INCREMENT PRIMARY KEY,
    age INT,
    name VARCHAR(255),
    email VARCHAR(255),
    pass_hash VARCHAR(60),
    phone_number VARCHAR(20),
    role VARCHAR(20)
);