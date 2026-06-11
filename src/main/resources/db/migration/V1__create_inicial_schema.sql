CREATE TABLE tb_mesa (
    id INT PRIMARY KEY,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT chk_status_mesa CHECK (status IN ('OCUPADA', 'LIVRE'))
);

INSERT INTO tb_mesa (id, status) VALUES (1, 'OCUPADA');
INSERT INTO tb_mesa (id, status) VALUES (2, 'OCUPADA');
INSERT INTO tb_mesa (id, status) VALUES (3, 'OCUPADA');
INSERT INTO tb_mesa (id, status) VALUES (4, 'OCUPADA');
INSERT INTO tb_mesa (id, status) VALUES (5, 'OCUPADA');

