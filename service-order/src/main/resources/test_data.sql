use serviceOrders

SELECT * FROM user_entity ORDER BY id

INSERT INTO user_entity (name, order_active) VALUES ('Santiago', false), ('Luis', false), ('Sara', false), ('Pepe', false), ('Rodolfo', false)

UPDATE user_entity SET order_active = false


SELECT * FROM employee_entity ORDER BY id

INSERT INTO employee_entity (name) VALUES ('Jorge'), ('Luisa'), ('Mauricio'), ('Daniel'), ('Alicia')


SELECT * FROM order_entity ORDER BY id

INSERT INTO order_entity (campus, delivery_id, end_order, start_order, state, employee_order_id, user_order_id) VALUES
(1, 'adfagagagagagag', '2023-08-02 08:58', '2023-08-02 08:48', 'Entregado', 1, 3),
(2, 'fagatgaaeghag', '2023-08-02 08:58', '2023-08-02 08:51', 'Entregado', 1, 1),
(3, 'opdhodjhoidfjngsg', '2023-08-02 08:58', '2023-08-02 08:30', 'Entregado', 4, 5)

UPDATE order_entity SET state = 'Pendiente' WHERE id = 2
