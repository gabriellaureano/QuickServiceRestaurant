DROP TABLE IF EXISTS tb_pedido_produto;

CREATE TABLE tb_pedido_produto (
    pedido_id BIGINT NOT NULL REFERENCES tb_pedido(id),
    produto_id BIGINT NOT NULL REFERENCES tb_produto(id)
);