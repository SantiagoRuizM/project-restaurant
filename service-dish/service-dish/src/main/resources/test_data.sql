use serviceDishes

select * from category_entity
insert into category_entity (name) values ('Comida de mar'),('Comida típica'),('Comida rápida'),('Comida de temporada'),('Fritos')

select * from campus_entity
insert into campus_entity (name) values ('Medellín'),('Sabaneta'),('Bello'),('Sabaneta'),('Envigado')

SELECT * FROM dish_entity
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Ceviche de camarones', 12000, 'Deliciosa combinación de camarones con limón y ají', 'https://example.com/ceviche_cam.jpg', 1, true, 1)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Arepa paisa', 6000, 'Arepa tradicional rellena de carne desmechada y aguacate', 'https://example.com/arepa_paisa.jpg', 2, true, 2)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Hamburguesa gourmet', 15000, 'Exquisita hamburguesa con carne angus y queso brie', 'https://example.com/hamburguesa_gourmet.jpg', 3, true, 1)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Ensalada de frutas frescas', 8000, 'Refrescante ensalada de frutas de temporada', 'https://example.com/ensalada_frutas.jpg', 4, true, 3)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Patacón con hogao', 5000, 'Patacón crocante acompañado de hogao colombiano', 'https://example.com/patacon_hogao.jpg', 5, false, 5)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Sushi fusión', 18000, 'Sushi innovador con toque de fusión asiática', 'https://example.com/sushi_fusion.jpg', 1, true, 2)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Parrillada argentina', 25000, 'Exquisita parrillada con cortes de carne premium', 'https://example.com/parrillada_arg.jpg', 2, true, 4)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Hotdog Tex-Mex', 10000, 'Hotdog al estilo tex-mex con jalapeños y guacamole', 'https://example.com/hotdog_texmex.jpg', 3, true, 1)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Crema de calabaza', 7000, 'Deliciosa crema de calabaza con toque de canela', 'https://example.com/crema_calabaza.jpg', 4, true, 3)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Arepas rellenas', 8000, 'Variedad de arepas rellenas con ingredientes a elección', 'https://example.com/arepas_rellenas.jpg', 5, false, 5)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Ceviche de pescado', 11000, 'Ceviche fresco de pescado con cítricos', 'https://example.com/ceviche_pescado.jpg', 1, true, 3)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Empanadas de carne', 5000, 'Empanadas caseras rellenas de carne molida', 'https://example.com/empanadas_carne.jpg', 2, true, 4)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Pizza vegetariana', 12000, 'Pizza con variedad de vegetales frescos', 'https://example.com/pizza_vegetariana.jpg', 3, true, 1)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Helado de fresa', 6000, 'Helado cremoso de fresa', 'https://example.com/helado_fresa.jpg', 4, true, 2)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Buñuelos colombianos', 4000, 'Buñuelos esponjosos de queso', 'https://example.com/bunuelos.jpg', 5, false, 3)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Sushi clásico', 16000, 'Sushi tradicional con salmón y aguacate', 'https://example.com/sushi_clasico.jpg', 1, true, 5)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Asado de tira', 22000, 'Asado de tira argentino a la parrilla', 'https://example.com/asado_tira.jpg', 2, true, 1)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Tacos al pastor', 9000, 'Tacos mexicanos al pastor con piña', 'https://example.com/tacos_pastor.jpg', 3, true, 4)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Sopa de lentejas', 7000, 'Sopa reconfortante de lentejas', 'https://example.com/sopa_lentejas.jpg', 4, true, 2)
INSERT INTO dish_entity (name, price, description, url_image, category, active, campus) VALUES ('Tostones con guacamole', 5500, 'Tostones crujientes con guacamole', 'https://example.com/tostones_guacamole.jpg', 5, false, 5)


