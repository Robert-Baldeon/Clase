CREATE DATABASE IF NOT EXISTS BDALQUILA;
USE BDALQUILA;

-- Tablas maestras sin FKs
CREATE TABLE cliente (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  prApellido VARCHAR(50) NOT NULL,
  sgApellido VARCHAR(50) NULL,
  domicilio VARCHAR(100),
  numTarjeta VARCHAR(20)
);

CREATE TABLE oficina (
  codOficina VARCHAR(10) PRIMARY KEY,
  domicilio VARCHAR(100),
  telefono VARCHAR(15)
);

CREATE TABLE fabricante (
  codFab VARCHAR(10) PRIMARY KEY,
  fabricante VARCHAR(50) NOT NULL
);

CREATE TABLE marca (
  codMarca VARCHAR(10) PRIMARY KEY,
  marca VARCHAR(50) NOT NULL
);

CREATE TABLE empleado (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  prApellido VARCHAR(50) NOT NULL,
  sgApellido VARCHAR(50) NULL,
  domicilio VARCHAR(100),
  numCuenta VARCHAR(24)
);

CREATE TABLE curso (
  codCurso VARCHAR(10) PRIMARY KEY,
  descripcion VARCHAR(150),
  numHoras INT,
  fecha DATE
);

-- Tablas con claves foráneas (1:N)
CREATE TABLE vehiculo (
  codVeh VARCHAR(10) PRIMARY KEY,
  modelo VARCHAR(50),
  tipo VARCHAR(30),
  color VARCHAR(20),
  codFab VARCHAR(10),
  codMarca VARCHAR(10),
  FOREIGN KEY (codFab) REFERENCES fabricante(codFab),
  FOREIGN KEY (codMarca) REFERENCES marca(codMarca)
);

CREATE TABLE alquiler (
  codAlquiler VARCHAR(10) PRIMARY KEY,
  fecha DATE NOT NULL,
  numDias INT,
  kmActual INT,
  kmEntrega INT,
  dni_cliente VARCHAR(9),
  codVeh VARCHAR(10),
  dni_empleado VARCHAR(9),
  FOREIGN KEY (dni_cliente) REFERENCES cliente(dni),
  FOREIGN KEY (codVeh) REFERENCES vehiculo(codVeh),
  FOREIGN KEY (dni_empleado) REFERENCES empleado(dni)
);

-- Tablas de relación (N:M)
CREATE TABLE oficinas_alquiler (
  codAlquiler VARCHAR(10),
  codOficina VARCHAR(10),
  PRIMARY KEY (codAlquiler, codOficina),
  FOREIGN kEY (codAlquiler) REFERENCES alquiler(codAlquiler),
  FOREIGN KEY (codOficina) REFERENCES oficina(codOficina)
);

CREATE TABLE empleado_curso (
  dni_empleado VARCHAR(9),
  codCurso VARCHAR(10),
  PRIMARY KEY (dni_empleado, codCurso),
  FOREIGN KEY (dni_empleado) REFERENCES empleado(dni),
  FOREIGN KEY (codCurso) REFERENCES curso(codCurso)
);
