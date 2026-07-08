    CREATE TABLE tb_historico_fechamento(
        id BIGSERIAL PRIMARY KEY,
        mesa_id BIGINT NOT NULL,
        cliente_responsavel VARCHAR(255) NOT NULL,
        valor_total DECIMAL(10,2) NOT NULL,
        data_fechamento TIMESTAMP NOT NULL
    );