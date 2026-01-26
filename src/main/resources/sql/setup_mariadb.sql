CREATE DATABASE IF NOT EXISTS taller_seguridad;

-- Mostramos la DB
SHOW DATABASES;

-- Darles permisos totales al usuario mariadb sobre es DB
GRANT ALL PRIVILEGES ON taller_seguridad.* TO mariadb@localhost;

-- Listamos los permisos del usuario mariadb
SHOW GRANTS FOR CURRENT_USER;
SHOW GRANTS FOR mariadb@localhost;

-- Aplicar
FLUSH PRIVILEGES;

USE taller_seguridad;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL, -- En la vida real, aquí iría el hash
    email VARCHAR(100)
);

INSERT INTO usuarios (username, password, email) VALUES
('admin', 'p4ssw0rd_ultra_secreta', 'admin@empresa.com'),
('alexis', '12345', 'alexis@correo.com'),
('invitado', 'invitado2024', 'guest@correo.com');
