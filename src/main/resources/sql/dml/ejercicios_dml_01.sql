-- 1. Inserta registros en la tabla ALUMNO con los siguientes datos:

INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('98765432', 'Luis', 'Sánchez', 'Hernández', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('45678901', 'Elena', 'Ramírez', 'Díaz', 'S');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('56789012', 'Javier', 'Moreno', 'Ruiz', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('11112222', 'Fernando', 'García', 'Pérez', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('22223333', 'Lucía', 'Martínez', 'Santos', 'S');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('33334444', 'Roberto', 'Hernández', 'López', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('44445555', 'Paula', 'Díaz', 'Morales', 'S');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('55556666', 'Andrea', 'Ruiz', 'Gómez', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('66667777', 'Manuel', 'Fernández', 'Torres', 'S');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('77778888', 'Beatriz', 'Jiménez', 'Vargas', 'N');
INSERT INTO Alumno (dni, nombre, prApellido, sgApellido, Bilingue) VALUES ('88889999', 'Alberto', 'Castro', 'Ramos', 'S');

-- 2. Modifica la tabla matricula para que su PK sea de tipo autonumérico.

ALTER TABLE Matricula
MODIFY codMatr INT(7) AUTO_INCREMENT;

-- 3. Inserta registros para matricula a todos los nuevos alumnos en el curso 2025.
-- Utiliza un INSERT.. SELECT:

INSERT INTO Matricula (dni, curso)
SELECT dni, 2025
FROM Alumno
WHERE dni NOT IN (
  SELECT dni
  FROM Matricula
  WHERE curso = 2025
);

-- 4. Añade los siguientes registros a la tabla PROFESOR. Utiliza la opción ‘Multi-row Insert’

INSERT INTO Profesor (dni, Nombre, prApellido, sgApellido, CodDep) VALUES
('12345678', 'Carlos', 'Pérez', 'Gómez', 1),
('87654321', 'María', 'López', 'Martínez', 2),
('11223344', 'Ana', 'González', 'Fernández', 2);

-- 5. Crea una tabla ALUMNO_BCK con el contenido de la tabla Alumno, añadiendo una columna más ‘YEARMONTH’ con valor 0 para todos los registros. Utiliza un CREATE TABLE AS SELECT...

CREATE TABLE ALUMNO_BCK AS
SELECT *, 0 AS YEARMONTH 
FROM Alumno;

-- 6. Actualiza la columna ALUMNO_BCK.YEARMONTH con el valor ‘ENE2026’ para todos los registros.

UPDATE ALUMNO_BCK
SET YEARMONTH = 'ENE2026';

-- 7. Crea una clave primaria para la tabla ALUMNO_BCK (sin añadir columnas).

ALTER TABLE ALUMNO_BCK
ADD PRIMARY KEY (dni);

-- 8. Inserta en ALUMBIL los alumnos que tienen en la tabla alumno el campo ‘bilingüe’ a ‘S’ y todavía no se encuentran en la tabla. Todos con la fecha actual (CURRENT_DATE()) y sin ‘lugar’. Utiliza un INSERT SELECT.

INSERT INTO ALUMBIL (dni, fecha)
SELECT dni, CURRENT_DATE()
FROM Alumno
WHERE Bilingue = 'S'
  AND dni NOT IN (
    SELECT dni
    FROM ALUMBIL
  );

INSERT INTO ALUMBIL (dni, fecha)
SELECT a.dni, CURRENT_DATE()
FROM Alumno a
WHERE a.Bilingue = 'S'
  AND NOT EXISTS (
    SELECT 1
    FROM ALUMBIL b
    WHERE b.dni = a.dni
  );

-- 9. Modifica la tabla contrato para que su campo codcont sea autonumérico.

ALTER TABLE contrato
MODIFY codCont INT(6) AUTO_INCREMENT;

-- 10. Utilizando un INSERT..SELECT, añade un registro en la tabla CONTRATO para cada profesor, teniendo en cuenta que CONTRATO.curso debe corresponder con el primer curso en el que cada profesor impartió alguna asignatura.

INSERT INTO Contrato (dni, curso)
SELECT dni, MIN(curso)
FROM Imparte
GROUP BY dni;
