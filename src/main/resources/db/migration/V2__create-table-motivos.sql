
CREATE TABLE motivos (
                                motivo_id INTEGER IDENTITY (1,1),
                                nome_motivo VARCHAR(255) NOT NULL,
                                descri_motivo VARCHAR(255) NOT NULL,
                                PRIMARY KEY (motivo_id)
);