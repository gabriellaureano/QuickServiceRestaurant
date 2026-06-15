CREATE TABLE tb_pedido (
    id BIGSERIAL PRIMARY KEY,
    mesa_id BIGINT NOT NULL REFERENCES tb_mesa(id),
    observacao VARCHAR,
    status_pedido VARCHAR(20) NOT NULL CHECK(status_pedido IN ('ANDAMENTO','PRONTO'))
);