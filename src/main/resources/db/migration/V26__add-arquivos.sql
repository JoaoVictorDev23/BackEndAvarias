CREATE TABLE Arquivos (
                          id INT IDENTITY(1,1) PRIMARY KEY,
                          nome_arquivo VARCHAR(255) NOT NULL,
                          numero_nota_fiscal VARCHAR(255),
                          FOREIGN KEY (numero_nota_fiscal) REFERENCES NotasFiscais(numero) ON DELETE CASCADE
);
