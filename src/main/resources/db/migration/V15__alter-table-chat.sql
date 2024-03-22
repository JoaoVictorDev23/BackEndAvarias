-- Adicionar coluna numeronfd
ALTER TABLE chat
    ADD numeronfd NVARCHAR(255);

-- Adicionar restrição de chave estrangeira
ALTER TABLE chat
    ADD CONSTRAINT fk_chat_dadosnfd
        FOREIGN KEY (numeronfd) REFERENCES dadosnfd(numero_nfd);