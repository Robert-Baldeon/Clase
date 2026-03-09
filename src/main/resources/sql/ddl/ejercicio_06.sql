-- 1. Crea una base de datos BDTiendaONLINE

CREATE DATABASE IF NOT EXISTS BDTiendaONLINE;
USE BDTiendaONLINE;

-- 2. Crea la tabla Cliente con las columnas:
-- cliente_id (entero, clave primaria, autonumérico).
-- nombre (cadena de texto, no nulo).
-- email (cadena de texto, no nulo).
-- fecha_registro (fecha).

CREATE TABLE cliente (
  cliente_id INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  fecha_registro DATE
);

-- 3. Añade una restricción UNIQUE (UQ_Cliente_email) a la columna email en la tabla Cliente.

ALTER TABLE cliente ADD CONSTRAINT UQ_Cliente_email UNIQUE (email);

-- 4. Crear la tabla Producto con las columnas:
-- producto_id (entero, clave primaria, autonumérico).
-- nombre_producto (cadena de texto, no nulo).
-- precio (decimal, no nulo).
-- stock (entero, no nulo).

CREATE TABLE producto (
  producto_id INT PRIMARY KEY AUTO_INCREMENT,
  nombre_producto VARCHAR(50) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  stock INT NOT NULL
);

-- 5. Crea la tabla Pedido con las columnas:
-- pedido_id (entero, clave primaria, autonumérico).
-- cliente_id (entero, no nulo).
-- fecha_pedido (fecha, no nulo).
-- Añade una clave foránea que relacione cliente_id con la tabla Cliente.

CREATE TABLE pedido (
  pedido_id INT PRIMARY KEY AUTO_INCREMENT,
  cliente_id INT NOT NULL,
  fecha_pedido DATE NOT NULL,
  FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

-- 6. Crea la tabla DetallePedido con las columnas:
-- pedido_id (entero, no nulo).
-- producto_id (entero, no nulo).
-- cantidad (entero, no nulo).
-- Define la clave primaria compuesta por (pedido_id, producto_id).
-- Añadir claves foráneas que enlacen a Pedido y Producto.

CREATE TABLE (
  pedido_id INT NOT NULL,
  producto_id INT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (pedido_id, producto_id),
  FOREIGN KEY (pedido_id) REFERENCES pedido(pedido_id),
  FOREIGN KEY (producto_id) REFERENCES producto(producto_id)
);

-- 7. Añade la columna telefono (cadena de texto de 15 caracteres) a la tabla Cliente

ALTER TABLE cliente
ADD COLUMN telefono VARCHAR(15);

-- 8. Modifica el tipo de dato de la columna stock en la tabla Producto a tipo smallint

ALTER TABLE producto
MODIFY stock SMALLINT;

-- 9. Modifica la columna telefono en la tabla Cliente para que no pueda ser nula (NOT NULL).

ALTER TABLE cliente
MODIFY telefono NOT NULL;

-- 10. Cambia el nombre de la columna nombre a nombre_completo en la tabla Cliente.

ALTER TABLE cliente
RENAME nombre to nombre_completo;

-- 11. Elimina la columna fecha_registro de la tabla Cliente.

ALTER TABLE cliente
DROP COLUMN fecha_registro;

-- 12. Añade una columna metodo_pago (entero) a la tabla Pedido.
-- Crea una tabla MetodoPago (metodo_id PK, nombre_metodo).
-- Añade una clave foránea (FK_Pedidos_metodo_pago) a Pedido.metodo_pago que referencie a MetodoPago.metodo_id.

ALTER TABLE pedido
ADD COLUMN metodo_pago INT;

CREATE TABLE MetodoPago (
  metodo_id INT PRIMARY KEY AUTO_INCREMENT,
  nombre_metodo VARCHAR(50),
);

-- 13. Elimina la clave foránea FK_Pedidos_metodo_pago de la tabla Pedido.

ALTER TABLE pedido
DROP CONSTRAINT FK_Pedidos_metodo_pago;

-- 14. Elimina la restricción UNIQUE de la columna email en la tabla Cliente.

ALTER TABLE cliente
DROP CONSTRAINT UNIQUE;

-- 15. Elimina la tabla DetallePedido

DROP TABLE DetallePedido;
