-- ===========================================
-- CREACIÓN DE LA TABLA CINE
-- ===========================================
CREATE TABLE cine (
  idCine int(11) NOT NULL,                    -- Identificador único del cine
  nombreCine varchar(44) NOT NULL,            -- Nombre del cine, obligatorio
  poblacionCine varchar(33) NOT NULL          -- Ciudad o población del cine, obligatorio
);

-- ===========================================
-- CREACIÓN DE LA TABLA SALA
-- ===========================================
CREATE TABLE sala (
  idCine int(11) NOT NULL,                    -- Referencia al cine al que pertenece la sala
  idSala int(11) NOT NULL,                    -- Número de sala dentro del cine
  butacasSala int(11) NOT NULL                -- Número de butacas en la sala
);

-- ===========================================
-- CREACIÓN DE LA TABLA TICKET
-- ===========================================
CREATE TABLE ticket (
  idTicket int(11) NOT NULL,                  -- Identificador único del ticket
  idCine int(11) NOT NULL,                    -- Referencia al cine de la sala
  idSala int(11) NOT NULL,                    -- Referencia a la sala del cine
  dia date NOT NULL,                          -- Fecha de la función
  hora time NOT NULL,                         -- Hora de la función
  precio float NOT NULL                       -- Precio del ticket
);

-- ===========================================
-- CLAVES PRIMARIAS
-- ===========================================
ALTER TABLE cine
  ADD PRIMARY KEY (idCine);                   -- idCine es único en cine

ALTER TABLE sala
  ADD PRIMARY KEY (idCine, idSala),           -- Combinación cine+ sala es única
  ADD KEY idCine (idCine);                    -- Índice para búsquedas por cine

ALTER TABLE ticket
  ADD PRIMARY KEY (idTicket),                 -- idTicket es único
  ADD KEY idCine (idCine,idSala);             -- Índice compuesto para búsquedas por cine y sala

-- ===========================================
-- AUTO_INCREMENT (MySQL) / SERIAL (PostgreSQL)
-- ===========================================
ALTER TABLE cine
  MODIFY idCine int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT=45;                          -- Siguiente idCine = 45

ALTER TABLE ticket
  MODIFY idTicket int(11) NOT NULL AUTO_INCREMENT,
  AUTO_INCREMENT=3;                           -- Siguiente idTicket = 3

-- Nota para PostgreSQL: reemplazar por SERIAL o GENERATED AS IDENTITY
-- idCine SERIAL PRIMARY KEY, idTicket SERIAL PRIMARY KEY

-- ===========================================
-- CLAVES FORÁNEAS
-- ===========================================
ALTER TABLE sala
  ADD CONSTRAINT sala_ibfk_1
  FOREIGN KEY (idCine) REFERENCES cine (idCine)
  ON UPDATE CASCADE;                          -- Si cambia idCine en cine, se actualiza aquí

ALTER TABLE ticket
  ADD CONSTRAINT ticket_ibfk_1
  FOREIGN KEY (idCine,idSala) REFERENCES sala (idCine, idSala)
  ON UPDATE CASCADE;                          -- Si cambia cine o sala en sala, se actualiza aquí

-- Nota: se podría usar ON DELETE CASCADE si queremos eliminar automáticamente registros relacionados
