CREATE TABLE tb_mesa (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL CHECK (status IN ('OCUPADA', 'LIVRE'))
);

INSERT INTO tb_mesa (id,status) VALUES (1, 'OCUPADA'), (2, 'OCUPADA'), (3, 'OCUPADA'), (4, 'OCUPADA'), (5, 'OCUPADA');
