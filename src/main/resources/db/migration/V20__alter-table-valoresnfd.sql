-- 1. Remover a chave estrangeira existente
ALTER TABLE valoresnfd
DROP CONSTRAINT FK__valoresnf__motor__6EF57B66;

-- 2. Adicionar uma nova chave estrangeira para referenciar a tabela motorista
ALTER TABLE valoresnfd
    ADD CONSTRAINT FK__valoresnf__motorista_id
        FOREIGN KEY (motorista_id) REFERENCES motorista(motorista_id);
