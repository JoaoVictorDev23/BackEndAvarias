
-- Remover a coluna numeronfd
ALTER TABLE chat DROP COLUMN numeronfd;

-- Adicionar a nova coluna datahora
ALTER TABLE chat ADD datahora DATETIME;
