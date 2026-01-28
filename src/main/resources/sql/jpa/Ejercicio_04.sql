CREATE DATABASE IF NOT EXISTS taller_db;
USE taller_db;

-- Tablas independientes
CREATE TABLE ficha_tecnica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_bastidor VARCHAR(50) NOT NULL,
    ultima_itv DATE
);

CREATE TABLE vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(15) NOT NULL,
    modelo VARCHAR(50),
    id_ficha INT UNIQUE,
    CONSTRAINT fk_vehiculo_ficha FOREIGN KEY (id_ficha) REFERENCES ficha_tecnica(id)
);

CREATE TABLE mecanico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50)
);

-- Tabla con relación ManyToOne
CREATE TABLE reparacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    fecha DATE,
    id_vehiculo INT,
    CONSTRAINT fk_reparacion_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id)
);

-- Tabla intermedia para ManyToMany (Mecánicos asignados a reparaciones)
CREATE TABLE asignacion_reparaciones (
    id_reparacion INT,
    id_mecanico INT,
    PRIMARY KEY (id_reparacion, id_mecanico),
    CONSTRAINT fk_asig_reparacion FOREIGN KEY (id_reparacion) REFERENCES reparacion(id),
    CONSTRAINT fk_asig_mecanico FOREIGN KEY (id_mecanico) REFERENCES mecanico(id)
);
