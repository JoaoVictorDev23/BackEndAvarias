
ALTER TABLE dadosnfd
    ADD CONSTRAINT UK_numero_nfd UNIQUE (numero_nfd);

ALTER TABLE produtos
ALTER COLUMN produto_nfd NVARCHAR(255);

ALTER TABLE produtos
    ADD FOREIGN KEY (produto_nfd) REFERENCES dadosnfd(numero_nfd);
