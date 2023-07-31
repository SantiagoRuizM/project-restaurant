use serviceOrders

SELECT * FROM user_entity ORDER BY id

UPDATE user_entity SET order_active = false


SELECT * FROM employee_entity ORDER BY id


SELECT * FROM order_entity ORDER BY id

UPDATE order_entity SET state = 'Pendiente' WHERE id = 2
