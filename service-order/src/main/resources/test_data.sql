use serviceOrders

SELECT * FROM user_entity ORDER BY id

UPDATE user_entity SET order_active = false WHERE id = 3


SELECT * FROM employee_entity ORDER BY id


SELECT * FROM order_entity ORDER BY id

UPDATE order_entity SET state = 'Pendiente' WHERE id = 2

INSERT INTO order_entity (id, campus, dishes, state, user_order_id)
VALUES
(1,1,'1 24 3 3 8 2 13 110','Pendiente',1), (2,2,'6 8 14 5','Pendiente',2)
