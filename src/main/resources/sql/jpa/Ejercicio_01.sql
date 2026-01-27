-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS gimnasio_db;
USE gimnasio_db;

-- Socio - Membresia (1:1):
-- Un Socio tiene una única Membresía asociada mediante el campo id_membresia.
-- Es una relación uno a uno simple donde la clave foránea reside en la tabla socio.

-- Entrenador - Actividad (1:N):
-- Un Entrenador puede dirigir múltiples Actividades (ej. Yoga, Boxeo, Pilates).
-- Cada Actividad tiene asignado un único Entrenador responsable a través de id_entrenador.

-- Socio - Actividad (N:M):
-- Un Socio puede inscribirse en muchas Actividades diferentes.
-- Una Actividad puede tener a muchos Socios inscritos.
-- Esta relación se resuelve mediante la tabla intermedia inscripciones_actividades, que contiene las claves de ambas entidades (id_socio e id_actividad).

-- Tablas independientes
-- Tabla membresia (independiente)
CREATE TABLE membresia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_socio VARCHAR(50) NOT NULL,
    tipo_plan VARCHAR(20) -- Mensual, Anual, Premium
);

-- Tabla socio (relación 1:1 con membresia)
CREATE TABLE socio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    id_membresia INT UNIQUE,
    CONSTRAINT fk_socio_membresia FOREIGN KEY (id_membresia) REFERENCES membresia(id)
);

-- Tabla entrenador (independiente)
CREATE TABLE entrenador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50)
);

-- Tabla con relación ManyToOne
-- Tabla actividad (relación N:1 con entrenador)
CREATE TABLE actividad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_actividad VARCHAR(100),
    id_entrenador INT,
    CONSTRAINT fk_actividad_entrenador FOREIGN KEY (id_entrenador) REFERENCES entrenador(id)
);

-- Tabla intermedia para ManyToMany (Socios inscritos en actividades)
-- Table intermedia (relación N:M entre socio y actividad)
CREATE TABLE inscripciones_actividades (
    id_socio INT,
    id_actividad INT,
    PRIMARY KEY (id_socio, id_actividad),
    CONSTRAINT fk_ins_socio FOREIGN KEY (id_socio) REFERENCES socio(id),
    CONSTRAINT fk_ins_actividad FOREIGN KEY (id_actividad) REFERENCES actividad(id)
);
