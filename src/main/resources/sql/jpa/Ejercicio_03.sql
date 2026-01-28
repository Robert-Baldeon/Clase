CREATE DATABASE IF NOT EXISTS logistica_db;
USE logistica_db;

DROP TABLE IF EXISTS asignacion_envios;
DROP TABLE IF EXISTS incidencia;
DROP TABLE IF EXISTS transportista;
DROP TABLE IF EXISTS paquete;
DROP TABLE IF EXISTS seguro;

-- Tabla independiente
CREATE TABLE seguro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_poliza VARCHAR(50) NOT NULL,
    cobertura_maxima DECIMAL(10,2)
);

-- Tabla principal (incluye las columnas del objeto embebido)
CREATE TABLE paquete (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100),
    peso DOUBLE,
    -- Columnas para el objeto @Embedded Dimensiones
    alto DOUBLE,
    ancho DOUBLE,
    profundo DOUBLE,
    -- Relación 1:1
    id_seguro INT UNIQUE,
    CONSTRAINT fk_paquete_seguro FOREIGN KEY (id_seguro) REFERENCES seguro(id)
);

CREATE TABLE transportista (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    empresa VARCHAR(50)
);

-- Relación ManyToONe
CREATE TABLE incidencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comentario TEXT,
    id_paquete INT,
    CONSTRAINT fk_incidencia_paquete FOREIGN KEY (id_paquete) REFERENCES paquete(id)
);

-- Relación ManyToMany
CREATE TABLE asignacion_envios (
    id_paquete INT,
    id_transportista INT,
    PRIMARY KEY (id_paquete, id_transportista),
    CONSTRAINT fk_asig_paquete FOREIGN KEY (id_paquete) REFERENCES paquete(id),
    CONSTRAINT fk_asig_trans FOREIGN KEY (id_transportista) REFERENCES transportista(id)
);
