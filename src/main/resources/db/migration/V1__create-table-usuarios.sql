-- Versão da Migração: 1
CREATE TABLE usuarios (
                          id VARCHAR(255) PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          cpf VARCHAR(20) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfis (
                                usuario_id VARCHAR(255) REFERENCES usuarios(id),
                                perfil VARCHAR(255) NOT NULL,
                                PRIMARY KEY (usuario_id, perfil)
);


