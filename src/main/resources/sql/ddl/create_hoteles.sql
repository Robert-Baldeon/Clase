/* =============================================================================
BASE DE DATOS: BDHOTELES
Descripción: Gestión de una cadena de hoteles, personal y reservas.
============================================================================= */

CREATE DATABASE IF NOT EXISTS BDHOTELES;
USE BDHOTELES;

-- ---------------------------------------------------------
-- 1. TABLA: cliente
-- Almacena los datos personales de los huéspedes.
-- ---------------------------------------------------------
CREATE TABLE cliente (
  idCliente int(11) NOT NULL, -- ID único (se define autoincremental luego)
  NIF varchar(10) NOT NULL,    -- Documento de identidad (será UNIQUE)
  nombreCliente` varchar(25) NOT NULL,
  apellidosCliente` varchar(35) NOT NULL,
  poblacionCliente` varchar(35) NOT NULL
);

-- Inserción de datos de clientes (Jugadores de fútbol como ejemplo)
INSERT INTO cliente (idCliente, NIF, nombreCliente, apellidosCliente, poblacionCliente) VALUES
(1, '123456789A', 'Lionel', 'Messi', 'Barcelona'),
(2, '89121023T', 'Andrés', 'Iniesta', 'Albacete'),
(3, '123456780B', 'Pepe', 'González', 'Sueca'),
(4, '79123024X', 'Cristiano', 'Ronaldo', 'Madrid'),
(5, '89123123X', 'José Luis', 'Gayá', 'Valencia'),
(6, '12312333P', 'Carlos', 'Soler', 'Mislata'),
(7, '88333222K', 'Javi', 'Gracia', 'Alfafar');

-- ---------------------------------------------------------
-- 2. TABLA: hotel
-- Información sobre los establecimientos disponibles.
-- ---------------------------------------------------------
CREATE TABLE hotel (
  idHotel int(11) NOT NULL,
  nombreHotel varchar(25) NOT NULL,
  direccionHotel varchar(25) NOT NULL,
  poblacionHotel varchar(25) NOT NULL,
  telefonoHotel varchar(15) NOT NULL,
  categoria enum('1','2','3','4','5','6','7') DEFAULT NULL -- Estrellas del hotel
);

-- Inserción de hoteles de prueba
INSERT INTO hotel (idHotel, nombreHotel, direccionHotel, poblacionHotel, telefonoHotel, categoria) VALUES
(1, 'Cullera Beach', 'Cullera playa', 'Cullera', '961720000', '4'),
(2, 'Alzira Palace', 'Alzira centro', 'Alzira', '962410000', '3'),
(3, 'Valencia Center', 'Av. Francia', 'Valencia', '963330000', '5'),
(4, 'Sidi Saler', 'El Saler', 'Valencia', '961810000', '5'),
(5, 'Cullera Playa', 'Cullera marenyet', 'Cullera', '961730000', '4'),
(6, 'Hotel Mislata', 'C/ Mayor', 'Mislata', '963130000', '2');

-- ---------------------------------------------------------
-- 3. TABLA: habitacion
-- Detalle de las estancias de cada hotel.
-- La clave primaria será compuesta (idHotel + idHabitacion).
-- ---------------------------------------------------------
CREATE TABLE habitacion (
  idHotel int(11) NOT NULL,      -- A qué hotel pertenece
  idHabitacion int(11) NOT NULL, -- Número de habitación dentro de ese hotel
  m2 int(11) DEFAULT NULL,       -- Metros cuadrados
  precio float DEFAULT NULL,     -- Precio por noche
  camas int(11) DEFAULT NULL     -- Número de camas
);

-- Inserción de habitaciones (ejemplo para hotel 1)
INSERT INTO habitacion (idHotel, idHabitacion, m2, precio, camas) VALUES
(1, 1, 20, 50, 2),
(1, 2, 25, 60, 2),
(1, 3, 30, 80, 3),
(1, 4, 15, 40, 1);
-- (Siguen más inserciones para otros hoteles...)

-- ---------------------------------------------------------
-- 4. TABLA: empleado
-- Personal que trabaja en los hoteles.
-- ---------------------------------------------------------
CREATE TABLE empleado (
  idEmp int(11) NOT NULL,
  nombreEmp varchar(25) NOT NULL,
  ApellidoEmp varchar(25) NOT NULL,
  oficioEmp varchar(25) NOT NULL,
  sueldoEmp float DEFAULT NULL,
  idHotel int(11) DEFAULT NULL -- Hotel donde trabaja (Clave Ajena)
);

INSERT INTO empleado (idEmp, nombreEmp, ApellidoEmp, oficioEmp, sueldoEmp, idHotel) VALUES
(1, 'Paco', 'Sánchez', 'Conserje', 1200, 1),
(2, 'Luis', 'Sanz', 'Pintor', 1500, 1),
(3, 'María', 'López', 'Directiva', 3000, 1);

-- ---------------------------------------------------------
-- 5. TABLA: reserva
-- Vincula clientes con habitaciones en un periodo de tiempo.
-- ---------------------------------------------------------
CREATE TABLE reserva (
  idReserva int(11) NOT NULL,
  idCliente int(11) NOT NULL,
  idHotel int(11) NOT NULL,
  idHabitacion int(11) NOT NULL,
  fechaInicio date NOT NULL,
  fechaFin date NOT NULL
);

-- Inserción de reservas de ejemplo
INSERT INTO reserva (idReserva, idCliente, idHotel, idHabitacion, fechaInicio, fechaFin) VALUES
(1, 1, 1, 1, '2021-01-01', '2021-01-05');

-- ---------------------------------------------------------
-- 6. RESTRICCIONES (PRIMARY KEYS, UNIQUE e INDEX)
-- Se aplican al final para asegurar la estructura.
-- ---------------------------------------------------------

-- Definir claves primarias
ALTER TABLE cliente ADD PRIMARY KEY (idCliente), ADD UNIQUE KEY nif (NIF);
ALTER TABLE hotel ADD PRIMARY KEY (idHotel);
ALTER TABLE habitacion ADD PRIMARY KEY (idHotel, idHabitacion); -- Clave compuesta
ALTER TABLE empleado ADD PRIMARY KEY (idEmp);
ALTER TABLE reserva ADD PRIMARY KEY (idReserva);

-- Configurar AUTO_INCREMENT para los IDs
ALTER TABLE cliente MODIFY idCliente int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
ALTER TABLE hotel MODIFY idHotel int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
ALTER TABLE empleado MODIFY idEmp int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE reserva MODIFY idReserva int(11) NOT NULL AUTO_INCREMENT;

-- ---------------------------------------------------------
-- 7. RESTRICCIONES DE INTEGRIDAD (FOREIGN KEYS)
-- ---------------------------------------------------------

-- Un empleado pertenece a un hotel existente
ALTER TABLE empleado
ADD CONSTRAINT empleado_hotel FOREIGN KEY (idHotel) REFERENCES hotel (idHotel) 
ON UPDATE CASCADE;

-- Una habitación pertenece a un hotel existente
ALTER TABLE habitacion
ADD CONSTRAINT habitacion_hotel FOREIGN KEY (idHotel) REFERENCES hotel (idHotel) 
ON UPDATE CASCADE;

-- La reserva debe apuntar a un cliente y a una habitación válida (doble referencia)
ALTER TABLE reserva
ADD CONSTRAINT reserva_cliente FOREIGN KEY (idCliente) REFERENCES cliente (idCliente) ON UPDATE CASCADE,
ADD CONSTRAINT reserva_habitacion FOREIGN KEY (idHotel, idHabitacion) REFERENCES habitacion (idHotel, idHabitacion) 
ON UPDATE CASCADE;
