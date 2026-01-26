CREATE DATABASE taller_seguridad;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100)
);

INSERT INTO usuarios (username, password, email) VALUES
('admin', 'p4ssw0rd_ultra_secreta', 'admin@empresa.com'),
('alexis', '12345', 'alexis@correo.com'),
('invitado', 'invitado2024', 'guest@correo.com');
