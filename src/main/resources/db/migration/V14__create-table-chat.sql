CREATE TABLE chat (
                         id INT IDENTITY(1,1) PRIMARY KEY,
                         message VARCHAR(255),
                         nfd_vinculada VARCHAR(255),
                         user_vinculado VARCHAR(255)
);