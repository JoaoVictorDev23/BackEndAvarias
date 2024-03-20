
-- Migration para adicionar a coluna "cadastrado_por" à tabela "motivos"

ALTER TABLE motivos
    ADD cadastrado_por VARCHAR(255) ;

ALTER table armazem
     ADD cadastrado_por VARCHAR(255) ;

ALTER table pessoa
     ADD  cadastrado_por VARCHAR(255);