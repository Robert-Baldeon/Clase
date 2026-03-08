/* =============================================================================
BASE DE DATOS: CICLISMO
Descripción: Gestión de ciclistas, equipos, etapas, puertos y maillots.
============================================================================= */

CREATE DATABASE IF NOT EXISTS ciclismo;
USE ciclismo;

-- ---------------------------------------------------------
-- 1. TABLA: ciclista
-- Información sobre los corredores participantes.
-- ---------------------------------------------------------
CREATE TABLE ciclista (
  dorsal smallint(6) default NULL, -- Número identificador del corredor -> para números pequeños
  nombre varchar(30) default NULL, -- Nombre y apellidos
  edad smallint(6) default NULL,   -- Edad del ciclista
  nomeq varchar(25) default NULL   -- Nombre del equipo al que pertenece
) ;

-- Inserción de ciclistas históricos
INSERT INTO ciclista (dorsal,nombre,edad,nomeq) VALUES
(1,'Miguel Induráin',32,'Banesto'),
(2,'Pedro Delgado',35,'Banesto'),
(3,'Alex Zulle',27,'ONCE'),
(4,'Tony Rominger',30,'Mapei-Clas');
-- (Siguen más registros...)

-- ---------------------------------------------------------
-- 2. TABLA: equipo
-- Datos de las escuadras que compiten.
-- ---------------------------------------------------------
CREATE TABLE equipo (
  nomeq varchar(25) NOT NULL,    -- Nombre del equipo (Clave Primaria)
  director varchar(30) default NULL -- Nombre del director deportivo
) ;

INSERT INTO equipo (nomeq,director) VALUES
('Amore Vita','Guido Bontempi'),
('Banesto','José Miguel Echávarri'),
('ONCE','Manolo Saiz');

-- ---------------------------------------------------------
-- 3. TABLA: etapa
-- Información sobre los recorridos de la competición.
-- ---------------------------------------------------------
CREATE TABLE etapa (
  netapa smallint(6) NOT NULL,   -- Número de la etapa
  km smallint(6) default NULL,   -- Distancia en kilómetros
  salida varchar(35) default NULL, -- Ciudad de inicio
  llegada varchar(35) default NULL, -- Ciudad de meta
  dorsal smallint(6) default NULL   -- Dorsal del ciclista que ganó la etapa
) ;

INSERT INTO etapa (netapa,km,salida,llegada,dorsal) VALUES
(1,9,'Valladolid','Valladolid',1),
(2,180,'Valladolid','Salamanca',36);

-- ---------------------------------------------------------
-- 4. TABLA: puerto
-- Datos sobre las ascensiones de montaña en las etapas.
-- ---------------------------------------------------------
CREATE TABLE puerto (
  nompuerto varchar(35) NOT NULL, -- Nombre del puerto
  altura smallint(6) default NULL, -- Altura en metros sobre el nivel del mar
  categoria varchar(1) default NULL, -- Categoría (E, 1ª, 2ª, 3ª)
  pendiente double(8,2) default NULL, -- Pendiente media
  netapa smallint(6) default NULL, -- En qué etapa se sube
  dorsal smallint(6) default NULL  -- Dorsal del ciclista que pasó primero
) ;

INSERT INTO puerto(nompuerto,altura,categoria,pendiente,netapa,dorsal) VALUES
('Arcalis', 2230, 'E', 5.5, 10, 7),
('Cerler-Circo de Ampriu', 2500, 'E', 5.68, 11, 9);

-- ---------------------------------------------------------
-- 5. TABLA: maillot
-- Define los premios y colores de los líderes.
-- ---------------------------------------------------------
CREATE TABLE maillot (
  codigo varchar(3) NOT NULL,     -- Código del maillot (MGE, MMO, etc.)
  tipo varchar(30) default NULL,  -- Qué premia (General, Montaña...)
  color varchar(20) default NULL, -- Color físico de la prenda
  premio int(11) default NULL     -- Valor económico del premio
) ;

INSERT INTO maillot (codigo,tipo,color,premio) VALUES
('MGE','General','Amarillo',8000000),
('MMO','Montaña','Blanco y Rojo',2000000);

-- ---------------------------------------------------------
-- 6. TABLA: llevar
-- Relación que indica qué ciclista llevó qué maillot en cada etapa.
-- ---------------------------------------------------------
CREATE TABLE llevar (
  dorsal smallint(6) NOT NULL,
  netapa smallint(6) NOT NULL,
  codigo varchar(3) NOT NULL
) ;

INSERT INTO llevar (dorsal,netapa,codigo) VALUES
(1, 1, 'MGE'), -- Induráin llevó el amarillo en la etapa 1
(1, 2, 'MGE');

-- ---------------------------------------------------------
-- 7. CLAVES PRIMARIAS (Se añaden al final)
-- ---------------------------------------------------------
ALTER TABLE ciclista ADD PRIMARY KEY (dorsal);
ALTER TABLE equipo ADD PRIMARY KEY (nomeq);
ALTER TABLE etapa ADD PRIMARY KEY (netapa);
ALTER TABLE puerto ADD PRIMARY KEY (nompuerto);
ALTER TABLE maillot ADD PRIMARY KEY (codigo);
ALTER TABLE llevar ADD PRIMARY KEY (netapa, codigo); -- Clave compuesta (Un maillot por etapa)
