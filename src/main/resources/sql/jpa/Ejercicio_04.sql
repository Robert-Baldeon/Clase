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

INSERT INTO ficha_tecnica (numero_bastidor, ultima_itv) VALUES
('VF312345678901234', '2023-05-15'),
('WBAAA111222333444', '2024-01-10'),
('ZAR99988877766655', '2022-11-20'),
('TMBJJJ00011122233', '2023-08-05'),
('JM1GG444555666777', '2024-02-12');

-- 2. Poblamos la tabla de Vehículos asociándolos a las fichas anteriores
-- El id_ficha debe corresponder a un ID existente en la tabla ficha_tecnica
INSERT INTO vehiculo (matricula, modelo, id_ficha) VALUES
('1234-ABC', 'Peugeot 208', 1),
('5678-DEF', 'BMW Serie 3', 2),
('9012-GHI', 'Alfa Romeo Giulia', 3),
('3456-JKL', 'Skoda Octavia', 4),
('7890-MNP', 'Mazda CX-5', 5);

-- 3. (Opcional) Poblamos algunos mecánicos para tener datos listos
INSERT INTO mecanico (nombre, especialidad) VALUES
('Ricardo Montaner', 'Electrónica'),
('Sofía Loren', 'Chapa y Pintura'),
('Marcos Automotriz', 'Motores Diésel');
