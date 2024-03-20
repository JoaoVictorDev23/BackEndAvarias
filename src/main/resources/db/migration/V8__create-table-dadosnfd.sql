CREATE TABLE dadosnfd (
                          dadosnfd_id INT PRIMARY KEY IDENTITY,
                          numero_nfd NVARCHAR(255),
                          numero_nfo NVARCHAR(255),
                          filial NVARCHAR(255),
                          serie NVARCHAR(255),
                          cte NVARCHAR(255),
                          observacao NVARCHAR(MAX),
                          motivo_id INT,
                          FOREIGN KEY (motivo_id) REFERENCES motivos(motivo_id)
);
