/* =============================================================================
BASE DE DATOS: BDCENTROIDIOMAS
Descripción: Gestión de alumnos, profesores, ciclos y matrículas de idiomas.
============================================================================= */

CREATE DATABASE BDCENTROIDIOMAS;
USE bdcentroidiomas;

-- ---------------------------------------------------------
-- 1. TABLA: Departamento
-- Nota: Se crea primero pero la FK del director se añade después
-- para evitar errores de tabla inexistente.
-- ---------------------------------------------------------
CREATE TABLE Departamento(
  CodDep INT(2) PRIMARY KEY,
  Nombre VARCHAR(100) NOT NULL,
  dni varchar(8) -- DNI del profesor que es JEFE de departamento
);

-- ---------------------------------------------------------
-- 2. TABLA: Profesor
-- Cada profesor pertenece a un departamento.
-- ---------------------------------------------------------
CREATE TABLE Profesor(
  dni varchar(8) PRIMARY KEY,
  Nombre VARCHAR(30) NOT NULL,
  prApellido VARCHAR(30) NOT NULL,
  sgApellido VARCHAR(30),
  CodDep INT(2) NOT NULL, -- FK hacia Departamento
  FOREIGN KEY (CodDep) REFERENCES Departamento(CodDep)
);

-- RESTRICCIÓN CIRCULAR: Ahora que existe Profesor, vinculamos al jefe del Dept.
ALTER TABLE Departamento ADD CONSTRAINT fk_prof_Dep
FOREIGN KEY (dni) REFERENCES Profesor(dni);

-- ---------------------------------------------------------
-- 3. TABLA: Alumno
-- ---------------------------------------------------------
CREATE TABLE Alumno(
  dni varchar(8) PRIMARY KEY,
  Nombre VARCHAR(30) NOT NULL,
  prApellido VARCHAR(30) NOT NULL,
  sgApellido VARCHAR(30),
  Bilingue CHAR(1) NOT NULL -- Indicador 'S' o 'N'
);

-- ---------------------------------------------------------
-- 4. TABLA: Ciclo
-- Define los ciclos formativos (ej. DAW, ASIR, etc.)
-- ---------------------------------------------------------
CREATE TABLE Ciclo(
  CodCF INT(2) PRIMARY KEY,
  Nombre VARCHAR(256) NOT NULL,
  Siglas VARCHAR(10) NOT NULL
);

-- ---------------------------------------------------------
-- 5. TABLA: Asignatura
-- Incluye la especialidad (B = Bilingüe) y el Ciclo al que pertenece.
-- ---------------------------------------------------------
CREATE TABLE Asignatura(
  codAsig INT(3) PRIMARY KEY,
  Nombre VARCHAR(128) NOT NULL,
  numHoras INT(3) NOT NULL,
  B CHAR(1) NOT NULL, -- ¿Es asignatura bilingüe?
  CodCF INT(2) NOT NULL, -- FK hacia Ciclo
  FOREIGN KEY (CodCF) REFERENCES Ciclo(CodCF)
);

-- ---------------------------------------------------------
-- 6. TABLA: Matricula
-- Cabecera de la matrícula: quién se matricula y en qué año.
-- ---------------------------------------------------------
CREATE TABLE Matricula(
  codMatr INT(10) PRIMARY KEY,
  dni varchar(8) NOT NULL, -- FK hacia Alumno
  anyMatr INT(4) NOT NULL,
  FOREIGN KEY (dni) REFERENCES Alumno(dni)
);

-- ---------------------------------------------------------
-- 7. TABLA: LineaMatricula
-- Detalle de las asignaturas de cada matrícula y la nota obtenida.
-- ---------------------------------------------------------
CREATE TABLE LineaMatricula(
  codMatr INT(10),
  codAsig INT(3),
  nota INT(2),
  PRIMARY KEY (codMatr, codAsig), -- Clave compuesta
  FOREIGN KEY (codMatr) REFERENCES Matricula(codMatr),
  FOREIGN KEY (codAsig) REFERENCES Asignatura(codAsig)
);

-- ---------------------------------------------------------
-- 8. INSERCIÓN DE DATOS DE EJEMPLO
-- ---------------------------------------------------------

-- Departamentos (Sin jefe inicialmente para evitar errores de FK)
INSERT INTO Departamento (CodDep, Nombre) VALUES (1, 'Informática');

-- Alumnos
INSERT INTO Alumno VALUES ('13409827','Iker','Casillas','Fernández','S');

-- Ciclos y Asignaturas
INSERT INTO Ciclo VALUES (1, 'Desarrollo de Aplicaciones Web', 'DAW');
INSERT INTO Asignatura VALUES (1, 'Bases de Datos', 200, 'S', 1);

-- Matrícula y Notas
INSERT INTO Matricula VALUES (1, '13409827', 2024);
INSERT INTO LineaMatricula (codMatr, codAsig, nota) VALUES (1, 1, 9);
