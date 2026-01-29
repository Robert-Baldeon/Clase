CREATE DATABASE IF NOT EXISTS biblioteca_db;
USE biblioteca_db;

-- Socio - Carnet: Relación 1:1. Cada socio posee un carnet de biblioteca único para identificarse.
-- Libro - Ejemplar: Relación 1:N. Un título (Libro) puede tener múltiples copias físicas (Ejemplares) disponibles para préstamo.
-- Socio - Libro: Relación N:M. Representa el histórico o estado actual de préstamos; los socios toman prestados diversos libros y los libros pasan por muchos socios.

DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS ejemplar;
DROP TABLE IF EXISTS socio;
DROP TABLE IF EXISTS libro;
DROP TABLE IF EXISTS carnet;

-- Tablas independientes
CREATE TABLE carnet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_barras VARCHAR(50) NOT NULL,
    fecha_emision DATE
);

CREATE TABLE socio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    id_carnet INT UNIQUE,
    CONSTRAINT fk_socio_carnet FOREIGN KEY (id_carnet) REFERENCES carnet(id)
);

CREATE TABLE libro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL
);

-- Tabla con relación ManyToOne
CREATE TABLE ejemplar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50),
    id_libro INT,
    CONSTRAINT fk_ejemplar_libro FOREIGN KEY (id_libro) REFERENCES libro(id)
);

-- Tabla intermedia para ManyToMany
CREATE TABLE prestamos (
    id_socio INT,
    id_libro INT,
    PRIMARY KEY (id_socio, id_libro),
    CONSTRAINT fk_prestamo_socio FOREIGN KEY (id_socio) REFERENCES socio(id),
    CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro) REFERENCES libro(id)
);
