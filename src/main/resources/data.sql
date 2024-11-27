INSERT INTO clients (id, email, name, last_name) VALUES(1, 'client1@challengeapi.com', 'client 1 name', 'client 1 last name')
INSERT INTO clients (id, email, name, last_name) VALUES(2, 'client2@challengeapi.com', 'client 2 name', 'client 2 last name')
INSERT INTO clients (id, email, name, last_name) VALUES(3, 'client3@challengeapi.com', 'client 3 name', 'client 3 last name')
INSERT INTO clients (id, email, name, last_name) VALUES(4, 'client4@challengeapi.com', 'client 4 name', 'client 4 last name')
INSERT INTO clients (id, email, name, last_name) VALUES(5, 'client5@challengeapi.com', 'client 5 name', 'client 5 last name')

INSERT INTO users (id, role, email, username, password) VALUES(1, 1, 'user1@challengeapi.com', 'user1', '5f4dcc3b5aa765d61d8327deb882cf99')

INSERT INTO products (id, price, stock, code, name) VALUES(1, 10.0, 100, 'COKEZ', 'Coca cola zero')
INSERT INTO products (id, price, stock, code, name) VALUES(2, 10.0, 100, 'COKEL', 'Coca cola light')
INSERT INTO products (id, price, stock, code, name) VALUES(3, 10.0, 100, 'COKER', 'Coca cola regular')

INSERT INTO orders (id, status, client_id, user_id) VALUES(1, 0, 1, 1)
INSERT INTO orders (id, status, client_id, user_id) VALUES(2, 0, 2, 1)

INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES(1, 1, 1, 10, 10.0)
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES(2, 1, 2, 5, 10.0)
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES(3, 1, 3, 8, 10.0)
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES(4, 2, 1, 7, 10.0)
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES(5, 2, 2, 6, 10.0)