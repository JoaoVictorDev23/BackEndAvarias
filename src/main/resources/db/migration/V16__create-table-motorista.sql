DROP TABLE IF EXISTS papeispessoa;
CREATE TABLE motorista (
                        motorista_id VARCHAR(255) PRIMARY KEY,
                        motorista_nome VARCHAR(255) NOT NULL,
                        motorista_cpf VARCHAR(255) NOT NULL,
                        motorista_email VARCHAR(255) NOT NULL UNIQUE
);
