CREATE DATABASE IF NOT EXISTS veterinaria_db;

USE veterinaria_db;

-- Mascota - HistorialMedico (1:1): Cada mascota tiene un único historial clínico vinculado por id_historial.
-- Veterinario - Consulta (1:N): Un veterinario puede atender muchas consultas, pero cada consulta es realizada por un solo profesional.
-- Mascota - Consulta (N:M): Una mascota puede asistir a varias consultas a lo largo del tiempo, y en una consulta (como una campaña de vacunación) pueden participar varias mascotas. Se resuelve con la tabla registro_consultas.

CREATE TABLE historial_medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_historial VARCHAR(50) NOT NULL,
    fecha_creacion DATE
);

CREATE TABLE mascota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    id_historial INT UNIQUE,
    CONSTRAINT fk_mascota_historial FOREIGN KEY (id_historial) REFERENCES historial_medico(id)
);

CREATE TABLE veterinario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    num_colegiado VARCHAR(50)
);

CREATE TABLE consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    motivo VARCHAR(255),
    id_veterinario INT,
    CONSTRAINT fk_consulta_veterinario FOREIGN KEY (id_veterinario) REFERENCES veterinario(id)
);

CREATE TABLE registro_consultas (
    id_mascota INT,
    id_consulta INT,
    PRIMARY KEY (id_mascota, id_consulta),
    CONSTRAINT fk_reg_mascota FOREIGN KEY (id_mascota) REFERENCES mascota(id),
    CONSTRAINT fk_reg_consulta FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);
