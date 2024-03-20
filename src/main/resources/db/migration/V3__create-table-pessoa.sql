CREATE TABLE pessoa (
                          pessoa_id VARCHAR(255) PRIMARY KEY,
                          pessoa_nome VARCHAR(255) NOT NULL,
                          pessoa_cpf VARCHAR(255) NOT NULL,
                          pessoa_email VARCHAR(255) NOT NULL UNIQUE
                    );

CREATE TABLE papelpessoa (
                                pessoa_id VARCHAR(255) REFERENCES pessoa(pessoa_id),
                                papel_pessoa VARCHAR(255) NOT NULL,
                                PRIMARY KEY (pessoa_id, papel_pessoa)
);