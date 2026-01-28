CREATE DATABASE IF NOT EXISTS usuarios_db;
USE usuarios_db;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL
);

-- Tabla para la colección de tipos básicos (Teléfonos)
CREATE TABLE usuario_telefonos (
    id_usuario INT,
    telefono VARCHAR(20),
    CONSTRAINT fk_tel_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Tabla para la colección de objetos empotrados (Direcciones)
CREATE TABLE usuario_direcciones (
    id_usuario INT,
    calle VARCHAR(100),
    ciudad VARCHAR(50),
    tipo_direccion VARCHAR(20), -- Ej: 'TRABAJO', 'CASA'
    CONSTRAINT fk_dir_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
