CREATE DATABASE IF NOT EXISTS BDOFICINA;
USE BDOFICINA;

-- 1. Tabla SEDE
CREATE TABLE SEDE (
  CodSede VARCHAR(10) PRIMARY KEY,
  Nombre VARCHAR(50) NOT NULL,
  Direccion VARCHAR(100)
);

-- 2. Tabla DEPARTAMENTO (Relación 1:N con Sede)
CREATE TABLE DEPARTAMENTO (
  CodDep VARCHAR(10) PRIMARY KEY,
  Nombre VARCHAR(50) NOT NULL,
  Ubicacion VARCHAR(100),
  CodSede VARCHAR(10),
  FOREIGN KEY (CodSede) REFERENCES SEDE(CodSede)
);

-- 3. Tabla PROGRAMADOR (Incluye relación reflexiva de Mentor y 1:N con Departamento)
CREATE TABLE PROGRAMADOR (
  secuencia INT PRIMARY KEY,
  Nombre VARCHAR(50) NOT NULL,
  Ap1 VARCHAR(50) NOT NULL,
  Ap2 VARCHAR(50) NULL, -- N. Ap2
  Direccion VARCHAR(100),
  Cuenta VARCHAR(24),
  Telefono VARCHAR(15),
  Tipo VARCHAR(30),
  CodDep VARCHAR(10),
  secuencia_mentor INT, -- Relación reflexiva "Mentor"
  FOREIGN KEY (CodDep) REFERENCES DEPARTAMENTO(CodDep),
  FOREIGN KEY (secuencia_mentor) REFERENCES PROGRAMADOR(secuencia)
);

-- 4. Tabla EQUIPO (Relación 1:N con Programador para el "Jefe")
CREATE TABLE EQUIPO (
  CodEquipo VARCHAR(10) PRIMARY KEY,
  Descripcion VARCHAR(150),
  secuencia_jefe INT,
  FOREIGN KEY (secuencia_jefe) REFERENCES PROGRAMADOR(secuencia)
);

-- 5. Tabla PROYECTO (Entidad débil de EQUIPO)
CREATE TABLE PROYECTO (
  FechaIni DATE,
  CodEquipo VARCHAR(10),
  Descripcion VARCHAR(150),
  FechaFin DATE NULL, -- N fechafin
  PRIMARY KEY (FechaIni, CodEquipo),
  FOREIGN KEY (CodEquipo) REFERENCES EQUIPO(CodEquipo)
);

-- 6. Tabla SUBPROYECTO (Relación reflexiva 1:N con Proyecto)
CREATE TABLE SUBPROYECTO (
  FechaIni_Sub DATE,
  FechaIni_Proy DATE,
  CodEquipo_Proy VARCHAR(10),
  Descripcion VARCHAR(150),
  PRIMARY KEY (FechaIni_Sub, FechaIni_Proy, CodEquipo_Proy),
  FOREIGN KEY (FechaIni_Proy, CodEquipo_Proy) REFERENCES PROYECTO(FechaIni, CodEquipo)
);

-- 7. Tabla EQUIPO_PROG (Relación N:M entre Equipo y Programador)
CREATE TABLE EQUIPO_PROG (
  CodEquipo VARCHAR(10),
  secuencia_prog INT,
  PRIMARY KEY (CodEquipo, secuencia_prog),
  FOREIGN KEY (CodEquipo) REFERENCES EQUIPO(CodEquipo),
  FOREIGN KEY (secuencia_prog) REFERENCES PROGRAMADOR(secuencia)
);
