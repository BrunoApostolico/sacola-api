INSERT INTO client (id, nome, cep, complemento)
VALUES (1l, 'Cliente 1', '000001', 'Ap 01'),
       (2l, 'Cliente 2', '000002', 'Ap 03'),
       (3l, 'Cliente 3', '000003', 'Ap 03');

INSERT INTO restaurant (id, nome, cep, complemento)
VALUES (1l, 'Loja Sushi', '100001', 'Loja 01'),
       (2l, 'Loja Acai', '100002', 'Loja 02'),
       (3l, 'Loja Pastel', '100003', 'Loja 03');

INSERT INTO product (id, disponivel, nome, valor_unitario, restaurante_id)
VALUES (1l, 1, 'Carioca Sushi', 2.0, 1l),
       (2l, 1, 'Joy Joy Sushi', 3.0, 1l),
       (3l, 1, 'Cupuaçu na Tigela', 20.0, 2l),
       (4l, 1, 'Acai na Tigela', 25.0, 2l),
       (5l, 1, 'Pastel de Palmito', 2.0, 3l),
       (6l, 1, 'Pastel de Queijo', 2.0, 3l);

INSERT INTO bag (id, forma_pagamento, fechada, valor_total, cliente_id)
VALUES (1l, 0, false, 0.0, 1l);
