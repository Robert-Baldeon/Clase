-- IMPORTANTE: El orden importa por las llaves foráneas (FK)

-- 1. Direcciones (No dependen de nadie)
INSERT INTO direccion (calle, ciudad) VALUES ('Calle Mayor 5', 'Madrid');
INSERT INTO direccion (calle, ciudad) VALUES ('Avenida Diagonal 100', 'Barcelona');

-- 2. Alumnos (Tienen una FK a direccion)
-- Asumimos que la columna se llama id_direccion
INSERT INTO alumno (nombre, email, id_direccion) VALUES ('Pepe Garcia', 'pepe@correo.com', 1);
INSERT INTO alumno (nombre, email, id_direccion) VALUES ('Ana Lopez', 'ana@correo.com', 2);

-- 3. Cursos
INSERT INTO curso (titulo) VALUES ('Desarrollo Web Java');
INSERT INTO curso (titulo) VALUES ('Administración de Sistemas');

-- 4. Módulos (Tienen una FK al curso)
-- Asumimos que la columna se llama id_curso
INSERT INTO modulo (nombre_modulo, horas, id_curso) VALUES ('Bases de Datos con JPA', 50, 1);
INSERT INTO modulo (nombre_modulo, horas, id_curso) VALUES ('Seguridad en Redes', 40, 2);

-- 5. Inscripciones (Tabla intermedia Many-To-Many)
-- Asumimos que la tabla se llama inscripciones y las columnas id_alumno e id_curso
INSERT INTO inscripciones (id_alumno, id_curso) VALUES (1, 1);
INSERT INTO inscripciones (id_alumno, id_curso) VALUES (2, 1);
INSERT INTO inscripciones (id_alumno, id_curso) VALUES (2, 2);
