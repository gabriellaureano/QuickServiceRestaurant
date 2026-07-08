CREATE TABLE tb_pedido_produto (
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,

    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES tb_pedido(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES tb_produto(id),

    PRIMARY KEY (pedido_id, produto_id)
);