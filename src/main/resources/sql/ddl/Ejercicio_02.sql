CREATE DATABASE IF NOT EXISTS BDCENTRO;
USE BDCENTRO;

CREATE TABLE profesor (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  ap1 VARCHAR(50) NOT NULL,
  ap2 VARCHAR(50) NULL,
  direccion VARCHAR(100),
  cuenta VARCHAR(24),
  telefono VARCHAR(15)
);

CREATE TABLE ciclo_form (
  cod_ciclo VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(100) UNIQUE NOT NULL,
  tipo VARCHAR(50)
);

CREATE TABLE asignatura (
  cod_asig VARCHAR(10) PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  num_horas INT,
  dni_profesor VARCHAR(9),
  cod_ciclo VARCHAR(10),
  FOREIGN KEY (dni_profesor) REFERENCES profesor(dni) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (cod_ciclo) REFERENCES ciclo_form(cod_ciclo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE idioma (
  cod_idioma VARCHAR(10) PRIMARY KEY,
  descripcion VARCHAR(100) UNIQUE
);

CREATE TABLE alumno (
  dni VARCHAR(9) PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  ap1 VARCHAR(50) NOT NULL,
  ap2 VARCHAR(50) NULL,
  direccion VARCHAR(100),
  email VARCHAR(100),
  telefono VARCHAR(15),
  cod_idioma VARCHAR(10),
  FOREIGN KEY (cod_idioma) REFERENCES idioma(cod_idioma) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE matricula (
  dni_alumno VARCHAR(9),
  cod_asig VARCHAR(10),
  PRIMARY KEY (dni_alumno, cod_asig),
  FOREIGN KEY (dni_alumno) REFERENCES alumno(dni) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (cod_asig) REFERENCES asignatura(cod_asig) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE idiomas_prof (
  cod_idioma VARCHAR(10),
  dni_profesor VARCHAR(9),
  PRIMARY KEY (cod_idioma, dni_profesor),
  FOREIGN KEY (cod_idioma) REFERENCES idioma(cod_idioma) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(dni_profesor) REFERENCES profesor(dni) ON DELETE CASCADE ON UPDATE CASCADE
);
