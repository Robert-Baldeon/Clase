CREATE DATABASE IF NOT EXISTS TEST;
USE TEST;

-- Tabla ALUMNO
CREATE TABLE alumno (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  ap1 VARCHAR(50) NOT NULL,
  ap2 VARCHAR(50) NULL
);

-- Tabla ASIGNATURA
CREATE TABLE asignatura (
  cod_asig VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  num_horas INT
);

-- Tabla de relación N:M (Inscripción)
CREATE TABLE inscripcion (
  dni_alumno VARCHAR(9),
  cod_asig VARCHAR(10),
  calificacion DECIMAL(4,2),
  PRIMARY KEY (dni_alumno, cod_asig),
  FOREIGN KEY (dni_alumno) REFERENCES alumno(dni) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (cod_asig) REFERENCES asignatura(cod_asig) ON DELETE CASCADE UPDATE CASCADE
);
