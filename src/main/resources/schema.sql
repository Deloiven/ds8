create table if not exists Note(
                                   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   title VARCHAR(50),
    content VARCHAR(400) NOT NULL,
    created_Date TIMESTAMP
    );