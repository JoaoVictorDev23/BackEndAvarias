CREATE TABLE cliente (
                         cliente_id INT IDENTITY(1,1) PRIMARY KEY,
                         cliente_nome VARCHAR(255),
                         cliente_cnpj VARCHAR(20),
                         valor_debitado FLOAT
);

CREATE TABLE valoresnfd (
                            valoresnfd_id INT IDENTITY(1,1) PRIMARY KEY,
                            valor_venda FLOAT,
                            valor_prejuizo FLOAT,
                            valor_armazem FLOAT,
                            situacao VARCHAR(255),
                            armazem_id INT,
                            motorista_id VARCHAR(255),
                            comprador_id VARCHAR(255),
                            cliente_id INT,
                            cadastrado_por VARCHAR(255),
                            atualizado_por VARCHAR(255),
                            valores_nfd VARCHAR(255),
                            FOREIGN KEY (armazem_id) REFERENCES armazem(armazem_id),
                            FOREIGN KEY (motorista_id) REFERENCES pessoa(pessoa_id),
                            FOREIGN KEY (comprador_id) REFERENCES pessoa(pessoa_id),
                            FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);
