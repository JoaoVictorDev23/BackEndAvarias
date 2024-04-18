CREATE TABLE Arquivos (
                          id INT IDENTITY(1,1) PRIMARY KEY,
                          nome_arquivo VARCHAR(255) NOT NULL,
                          numero_nota_fiscal NVARCHAR(255),
                          FOREIGN KEY (numero_nota_fiscal) REFERENCES dadosnfd(numero_nfd)
);
