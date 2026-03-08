CREATE DATABASE IF NOT EXISTS BDSUPER;
USE BDSUPER;

-- 1. Tabla Director
CREATE TABLE Director (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  prApellido VARCHAR(50) NOT NULL,
  sgApellido VARCHAR(50) NULL,
  domicilio VARCHAR(100),
  telefono VARCHAR(15),
  email VARCHAR(100) NULL
);

-- 2. Tabla Supermercado
CREATE TABLE Supermercado (
  codSup VARCHAR(10) PRIMARY KEY,
  direccion VARCHAR(100),
  superficie DECIMAL(10,2),
  esAlquiler BOOLEAN,
  fecha DATE,
  dni_director VARCHAR(9),
  FOREIGN KEY (dni_director) REFERENCES Director(dni)
);

-- 3. Tabla Vendedor
CREATE TABLE Vendedor (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  prApellido VARCHAR(50) NOT NULL,
  sgApellido VARCHAR(50) NULL,
  domicilio VARCHAR(100),
  telefono VARCHAR(15),
  email VARCHAR(100) NULL,
  codSup VARCHAR(10),
  FOREIGN KEY (CodSup) REFERENCES Supermercado(CodSup)
);

-- 4. Tabla Cliente
CREATE TABLE Cliente (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  prApellido VARCHAR(50) NOT NULL,
  sgApellido VARCHAR(50) NULL,
  domicilio VARCHAR(100),
  telefono VARCHAR(15),
  email VARCHAR(100) NULL
);

-- 5. Tabla Venta
CREATE TABLE Venta (
  CodVenta VARCHAR(15) PRIMARY KEY,
  fecha DATE NOT NULL,
  dniVend VARCHAR(9),
  dniCl VARCHAR(9),
  FOREIGN KEY (dniVend) REFERENCES Vendedor(dni),
  FOREIGN KEY (dniCl) REFERENCES Cliente(dni)
);

-- 6. Tabla Producto
CREATE TABLE Producto (
  codProducto VARCHAR(15) PRIMARY KEY,
  descripcion VARCHAR(100) NOT NULL,
  familia VARCHAR(50),
  Genero VARCHAR(50),
  descuento DECIMAL(5,2) NULL,
  iva DECIMAL(4,2)
);

-- 7. Tabla Precio
CREATE TABLE Precio (
  codProd VARCHAR(15),
  fecha DATE,
  precio DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (CodProd, fecha),
  FOREIGN KEY (CodProd) REFERENCES Producto(CodProducto)
);

-- 8. Tabla LineaVenta
CREATE TABLE LineaVenta (
  codVenta VARCHAR(15),
  numLinea INT,
  cantidad INT NOT NULL,
  codProd VARCHAR(15),
  fecha_precio DATE,
  PRIMARY KEY (CodVenta, numLinea),
  FOREIGN KEY (CodVenta) REFERENCES Venta(CodVenta),
  FOREIGN KEY (CodProd, fecha_precio) REFERENCES Precio(CodProd, fecha)
);

-- 9. Tabla Devolucion
CREATE TABLE Devolucion (
  codVenta VARCHAR(15),
  numLinea INT,
  fecha DATE,
  estado VARCHAR(30),
  tipoDev VARCHAR(30),
  PRIMARY KEY (CodVenta, numLinea, fecha),
  FOREIGN KEY (CodVenta, numLinea) REFERENCES LineaVenta(CodVenta, numLinea)
);
