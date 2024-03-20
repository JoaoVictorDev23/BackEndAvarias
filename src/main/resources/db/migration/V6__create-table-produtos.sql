CREATE TABLE produtos (
                        produto_id INTEGER PRIMARY KEY IDENTITY(1,1),
                        produto_nome VARCHAR(255) NOT NULL,
                        produto_valor FLOAT NOT NULL,
                        produto_quantidade FLOAT NOT NULL,
                        produto_situacao VARCHAR(255) NOT NULL,
                        produto_armazem VARCHAR(255) NOT NULL,
                        produto_nfd VARCHAR (255) NOT NULL

);