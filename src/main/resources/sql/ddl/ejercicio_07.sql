CREATE DATABASE PromeCamping;
USE PromeCamping;

-- TABLA PERSONA
CREATE TABLE Persona(
  dni CHAR(9) PRIMARY KEY,
  Nombre VARCHAR(50) NOT NULL,
  Ap1 VARCHAR(50) NOT NULL,
  Ap2 VARCHAR(50),
  FechaNacimiento DATE,
  Telefono VARCHAR(15),
  Email VARCHAR(100)
);

-- TABLA ALOJAMIENTO
CREATE TABLE Alojamiento(
  CodAlojamiento INT PRIMARY KEY AUTO_INCREMENT,
  Ubicacion VARCHAR(100),
  CapacidadMax INT
);

-- TABLA CLIENTE
CREATE TABLE Cliente(
  DNI CHAR(9) PRIMARY KEY,
  Domicilio VARCHAR(150),
  Provincia VARCHAR(50),
  CodAlojamiento INT,

  FOREIGN KEY (DNI) REFERENCES Persona(dni),
  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento)
);

-- TABLA OCUPANTE
CREATE TABLE Ocupante(
  DNI CHAR(9) PRIMARY KEY,
  FOREIGN KEY (DNI) REFERENCES Persona(dni)
);

-- TABLA EMPLEADO
CREATE TABLE Empleado(
  DNI CHAR(9) PRIMARY KEY,
  NSS VARCHAR(15) UNIQUE,

  FOREIGN KEY (DNI) REFERENCES Persona(dni)
);

-- TABLA ESTADO FACTURA
CREATE TABLE EstadoFactura(
  CodEstado INT PRIMARY KEY AUTO_INCREMENT,
  Descripcion VARCHAR(50)
);

-- TABLA ESTADO RESERVA
CREATE TABLE EstadoReserva(
  CodEstadoReserva INT PRIMARY KEY AUTO_INCREMENT,
  Estado VARCHAR(50)
);

-- TABLA TEMPORADA
CREATE TABLE Temporada(
  CodTemporada INT PRIMARY KEY AUTO_INCREMENT,
  Temporada VARCHAR(50),
  FechaInicio DATE,
  FechaFin DATE
);

-- TABLA SERVICIO
CREATE TABLE Servicio(
  IdServicio INT PRIMARY KEY AUTO_INCREMENT,
  Servicio VARCHAR(100)
);

-- TABLA TARIFA
CREATE TABLE Tarifa(
  IdTarifa INT PRIMARY KEY AUTO_INCREMENT,
  Tipo VARCHAR(50),
  ImporteBase DECIMAL(10,2),
  Impuesto DECIMAL(5,2),
  Descuento DECIMAL(5,2),
  CodTemporada INT,

  FOREIGN KEY (CodTemporada) REFERENCES Temporada(CodTemporada)
);

-- TABLA RESERVA
CREATE TABLE Reserva(
  CodReserva INT PRIMARY KEY AUTO_INCREMENT,
  FechaReserva DATE,
  FechaEntrada DATE,
  FechaSalida DATE,
  NumVehiculos INT,
  ImporteTotal DECIMAL(10,2),
  DNI CHAR(9),
  CodEstadoReserva INT,
  CodTemporada INT,

  FOREIGN KEY (DNI) REFERENCES Cliente(DNI),
  FOREIGN KEY (CodEstadoReserva) REFERENCES EstadoReserva(CodEstadoReserva),
  FOREIGN KEY (CodTemporada) REFERENCES Temporada(CodTemporada)
);

-- TABLA OCUPANTE RESERVA
CREATE TABLE OcupanteReserva(
  CodReserva INT,
  DNI CHAR(9),

  PRIMARY KEY (CodReserva, DNI),
  FOREIGN KEY (CodReserva) REFERENCES Reserva(CodReserva),
  FOREIGN KEY (DNI) REFERENCES Ocupante(DNI)
);

-- TABLA COMENTARIO
CREATE TABLE Comentario(
  codComentario INT PRIMARY KEY AUTO_INCREMENT,
  FechaComentario DATE,
  comentario TEXT,
  DNI CHAR(9),

  FOREIGN KEY (DNI) REFERENCES Cliente(DNI)
);

-- TABLA IBAN
CREATE TABLE IBAN(
  codIban INT PRIMARY KEY AUTO_INCREMENT,
  dni CHAR(9),
  CodPais CHAR(2),
  DC CHAR(2),
  Entidad CHAR(4),
  cSucursal CHAR(4),
  cDCCta CHAR(2),
  cnumCuenta CHAR(10),

  FOREIGN KEY (dni) REFERENCES Empleado(DNI)
);

-- TABLA FACTURA
CREATE TABLE Factura(
  IdFactura INT PRIMARY KEY AUTO_INCREMENT,
  NumFactura VARCHAR(20),
  FechaFactura DATE,
  BaseImponible DECIMAL(10,2),
  IVA DECIMAL(10,2),
  Importetotal DECIMAL(10,2),
  DNI CHAR(9),
  CodReserva INT,
  CodEstado INT,

  FOREIGN KEY (DNI) REFERENCES Cliente(DNI),
  FOREIGN KEY (CodReserva) REFERENCES Reserva(CodReserva),
  FOREIGN KEY (CodEstado) REFERENCES EstadoFactura(CodEstado)
);

-- TABLA LINEA FACTURA
CREATE TABLE LineaFactura(
  IdDetalle INT PRIMARY KEY AUTO_INCREMENT,
  Concepto VARCHAR(200),
  PrecioUnitario DECIMAL(10,2),
  Cantidad INT,
  ImporteBaseLinea DECIMAL(10,2),
  ImporteIVALinea DECIMAL(10,2),
  ImporteTotalLinea DECIMAL(10,2),
  IdFactura INT,
  IdTarifa INT,

  FOREIGN KEY (IdFactura) REFERENCES Factura(IdFactura),
  FOREIGN KEY (IdTarifa) REFERENCES Tarifa(IdTarifa)
);

-- TABLA ALOJAMIENTO TARIFA
CREATE TABLE AlojamientoTarifa(
  CodAlojamiento INT,
  IdTarifa INT,

  PRIMARY KEY (CodAlojamiento, IdTarifa),
  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento),
  FOREIGN KEY (IdTarifa) REFERENCES Tarifa(IdTarifa)
);

-- TABLA RESERVA ALOJAMIENTO
CREATE TABLE ReservaAlojamiento(
  CodReserva INT,
  CodAlojamiento INT,

  PRIMARY KEY (CodReserva, CodAlojamiento),
  FOREIGN KEY (CodReserva) REFERENCES Reserva(CodReserva),
  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento)
);

-- TABLA RESERVA SERVICIO
CREATE TABLE ReservaServicio(
  IdServicio INT,
  CodReserva INT,

  PRIMARY KEY (IdServicio, CodReserva),
  FOREIGN KEY (IdServicio) REFERENCES Servicio(IdServicio),
  FOREIGN KEY (CodReserva) REFERENCES Reserva(CodReserva)
);

-- TABLA SERVICIO TARIFA
CREATE TABLE ServicioTarifa(
  IdServicio INT,
  IdTarifa INT,

  PRIMARY KEY (IdServicio, IdTarifa),
  FOREIGN KEY (IdServicio) REFERENCES Servicio(IdServicio),
  FOREIGN KEY (IdTarifa) REFERENCES Tarifa(IdTarifa)
);

-- TABLA HOJA MANTENIMIENTO
CREATE TABLE HojaMantenimiento(
  IdHoja INT PRIMARY KEY AUTO_INCREMENT,
  FechaMto DATE,
  Descripcion TEXT
);

-- TABLA EMPLEADO MANTENIMIENTO ALOJAMIENTO
CREATE TABLE EmpMtoAlojamiento(
  DNI CHAR(9),
  IdHoja INT,
  CodAlojamiento INT,

  PRIMARY KEY (DNI, IdHoja, CodAlojamiento),
  FOREIGN KEY (DNI) REFERENCES Empleado(DNI),
  FOREIGN KEY (IdHoja) REFERENCES HojaMantenimiento(IdHoja),
  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento)
);

-- TABLA BUNGALOW
CREATE TABLE Bungalow(
  CodAlojamiento INT PRIMARY KEY,
  numHabitaciones INT,
  numCamas INT,
  nombre VARCHAR(100),
  Calefaccion BOOLEAN,
  A_A BOOLEAN,

  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento)
);

-- TABLA PARCELA
CREATE TABLE Parcela(
  CodAlojamiento INT PRIMARY KEY,
  Metros2 INT,
  TieneSombra BOOLEAN,

  FOREIGN KEY (CodAlojamiento) REFERENCES Alojamiento(CodAlojamiento)
);
