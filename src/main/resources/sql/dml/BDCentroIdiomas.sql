CREATE DATABASE BDCENTROIDIOMAS;
USE bdcentroidiomas;

CREATE TABLE Departamento (
  CodDep INT(2),
  Nombre VARCHAR(100) NOT NULL,
  dni varchar(8),
  PRIMARY KEY (CodDep)
);

CREATE TABLE Profesor (
  dni varchar(8),
  Nombre VARCHAR(30) NOT NULL,
  prApellido VARCHAR(30) NOT NULL,
  sgApellido VARCHAR(30),
  CodDep INT(2)  NOT NULL,
  PRIMARY KEY (dni),
  FOREIGN KEY (CodDep) REFERENCES Departamento(CodDep)
);

ALTER TABLE Departamento ADD CONSTRAINT fk_prof_Dep FOREIGN KEY (dni) REFERENCES Profesor(dni);

CREATE TABLE Alumno (
  dni varchar(8),
  Nombre VARCHAR(30) NOT NULL,
  prApellido VARCHAR(30) NOT NULL,
  sgApellido VARCHAR(30),
  Bilingue CHAR(1) NOT NULL,
  PRIMARY KEY (dni)
);

CREATE TABLE Ciclo (
  CodCF INT(2) ,
  Nombre VARCHAR(256) NOT NULL,
  Siglas VARCHAR(10) NOT NULL,
  PRIMARY KEY (CodCF)
);

CREATE TABLE Asignatura (
  codAsig INT(3) ,
  Nombre VARCHAR(128) NOT NULL,
  numHoras INT(3)  NOT NULL,
  B CHAR(1) NOT NULL, 
  codCF INT(2) NOT NULL,
  PRIMARY KEY (codAsig),
  FOREIGN KEY (codCF) REFERENCES Ciclo(codCF)
);

CREATE TABLE Matricula (
  codMatr INT(7) ,
  dni varchar(8) NOT NULL,
  curso INT(4) NOT NULL,
  PRIMARY KEY (codMatr),
  FOREIGN KEY (dni) REFERENCES Alumno(dni)
);

CREATE TABLE Imparte (
  codAsig INT(3) NOT NULL,
  curso INT(4) NOT NULL,
  dni varchar(8) NOT NULL,
  PRIMARY KEY (codAsig,curso,dni),
  FOREIGN KEY (dni) REFERENCES Profesor(dni),
  FOREIGN KEY (codAsig) REFERENCES Asignatura(codAsig)
);

CREATE TABLE LineaMatricula (
  codMatr INT(7),
  codAsig INT(3),
  nota DECIMAL(2,1), 
  PRIMARY KEY (codMatr, codAsig),
  FOREIGN KEY (codMatr) REFERENCES Matricula (codMatr),
  FOREIGN KEY (codAsig) REFERENCES Asignatura (codAsig)
);

CREATE TABLE Contrato (
  codCont INT(6) ,
  dni varchar(8)  NOT NULL,
  curso INT(4)  NOT NULL,
  PRIMARY KEY (codCont),
  FOREIGN KEY (dni) REFERENCES Profesor(dni)
);

CREATE TABLE LineaContrato (
  codCont INT(6) ,
  codAsig INT(3) ,
  PRIMARY KEY (codCont,codAsig),
  FOREIGN KEY (codCont) REFERENCES Contrato(codCont),
  FOREIGN KEY (codAsig) REFERENCES Asignatura(codAsig)
);

CREATE TABLE AlumBil (
  dni varchar(8) ,
  fecha DATE NOT NULL,
  lugar VARCHAR(20),
  PRIMARY KEY (dni),
  FOREIGN KEY (dni) REFERENCES Alumno(dni)
);

INSERT INTO Departamento VALUES(1,'Informática y Comunicaciones',NULL);
INSERT INTO Departamento VALUES(2,'Administración y Finanzas',NULL);

INSERT INTO Profesor VALUES(44102321,'Antonio','Martínez','González',1);
INSERT INTO Profesor VALUES(29600501,'Dolores','Ramos','Cabrera',1);
INSERT INTO Profesor VALUES(48300100,'Ian','Oxley',NULL,2);
INSERT INTO Profesor VALUES(84501495,'Iván','Sánchez','Muñoz',1);
INSERT INTO Profesor VALUES(48103100,'Miguel Ángel','Martínez','Marín',1);
INSERT INTO Profesor VALUES(90100200,'Alejandro','Marín','Cantos',1);
INSERT INTO Profesor VALUES(28900194,'David','Negro','Catalán',1);

UPDATE Departamento SET dni=48103100 WHERE codDep=1;
UPDATE Departamento SET dni=48300100 WHERE codDep=2;

INSERT INTO Ciclo VALUES(1,'Desarrollo de aplicaciones web','DAW');
INSERT INTO Ciclo VALUES(2,'Administración de sistemas informáticos en red','ASIR');
INSERT INTO Ciclo VALUES(3,'Desarrollo de aplicaciones multiplataforma','DAM');

INSERT INTO Asignatura VALUES(1,'Bases de datos',165,'S',1);
INSERT INTO Asignatura VALUES(2,'Lenguaje de marcas',120,'N',1);
INSERT INTO Asignatura VALUES(3,'Seguridad informática',90,'S',2);
INSERT INTO Asignatura VALUES(4,'Despliegue de aplicaciones web',110,'N',1);
INSERT INTO Asignatura VALUES(5,'Fundamentos de hardware',90,'N',2);
INSERT INTO Asignatura VALUES(6,'Acceso a datos',180,'N',1);
INSERT INTO Asignatura VALUES(7,'Bases de Datos',165,'N',3);
INSERT INTO Asignatura VALUES(8,'Programación',180,'N',3);

INSERT INTO Alumno VALUES(94342001,'Rosa','Blanco','Montero','S');
INSERT INTO Alumno VALUES(35143098,'Dolores','García','Ramos','N');
INSERT INTO Alumno VALUES(98105401,'Pedro','Marín','Espinosa','N');
INSERT INTO Alumno VALUES(13409827,'Ángel','Luque','Nieto','N');
INSERT INTO Alumno VALUES(39099100,'Josefa','Muñoz','Aguilar','S');
INSERT INTO Alumno VALUES(94392805,'Pilar','Cea','Ruíz','N');
INSERT INTO Alumno VALUES(51437206,'David','Chaparro','Gómez','S');

INSERT INTO Imparte (dni, codasig, curso) VALUES(44102321,1,2021);
INSERT INTO Imparte (dni, codasig, curso)VALUES(44102321,2,2022);
INSERT INTO Imparte (dni, codasig, curso)VALUES(29600501,1,2021);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,3,2022);
INSERT INTO Imparte (dni, codasig, curso)VALUES(84501495,1,2023);
INSERT INTO Imparte (dni, codasig, curso)VALUES(44102321,4,2022);
INSERT INTO Imparte (dni, codasig, curso)VALUES(48103100,6,2021);
INSERT INTO Imparte (dni, codasig, curso)VALUES(48300100,5,2022);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,6,2023);
INSERT INTO Imparte (dni, codasig, curso)VALUES(28900194,1,2022);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,7,2025);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,8,2025);
INSERT INTO Imparte (dni, codasig, curso) VALUES(44102321,1,2025);
INSERT INTO Imparte (dni, codasig, curso)VALUES(44102321,2,2025);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,3,2025);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,7,2023);
INSERT INTO Imparte (dni, codasig, curso)VALUES(90100200,8,2023);

INSERT INTO Matricula VALUES(1,51437206,2021);
INSERT INTO Matricula VALUES(2,35143098,2021);
INSERT INTO Matricula VALUES(3,51437206,2022);
INSERT INTO Matricula VALUES(4,94392805,2022);
INSERT INTO Matricula VALUES(5,35143098,2022);
INSERT INTO Matricula VALUES(6,94392805,2021);
INSERT INTO Matricula VALUES(7,35143098,2023);
INSERT INTO Matricula VALUES(8,51437206,2023);
INSERT INTO Matricula VALUES(9,39099100,2022);
INSERT INTO Matricula VALUES(10,94392805,2023);
INSERT INTO Matricula VALUES(11,13409827,2022);
INSERT INTO Matricula VALUES(12,94342001,2024);
INSERT INTO Matricula VALUES(13,94342001,2024);
INSERT INTO Matricula VALUES(14,98105401,2024);

INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (1,1,3);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (2,1,3);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (3,1,9);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (4,1,4);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (5,4,5);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (6,6,8);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (7,6,7);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (8,6,6);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (9,1,4);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (10,1,8);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (11,1,8);    
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (12,7,6);
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (13,8,8); 
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (14,5,7); 
INSERT INTO LineaMatricula(codMatr, codAsig , nota) values (9,8,5);

INSERT INTO AlumBil VALUES(39099100,'2019/06/10','Barcelona');
INSERT INTO AlumBil VALUES(94342001,'2019/09/10','Sevilla');
INSERT INTO AlumBil VALUES(51437206,'2017/09/01','Madrid');
