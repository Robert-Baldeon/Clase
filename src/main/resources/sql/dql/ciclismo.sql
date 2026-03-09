-- A) Añade a cada tabla la clave primary y las FK si las hubiera.
-- Realiza los cambios que sean necesarios.

CREATE TABLE equipo (
  nomeq VARCHAR(20),
  director VARCHAR(50),
  PRIMARY KEY (nomeq)
);

CREATE TABLE ciclista (
  dorsal INT,
  nombre VARCHAR(50),
  edad INT,
  nomeq VARCHAR(20),
  PRIMARY KEY (dorsal)
  FOREIGN KEY (nomeq) REFERENCES equipo(nomeq)
);

CREATE TABLE etapa (
  netapa INT,
  km INT,
  salida VARCHAR(50),
  llegada VARCHAR(50),
  dorsal INT,
  PRIMARY KEY (netapa),
  FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal)
);

CREATE TABLE puerto (
  nompuerto VARCHAR(50),
  altura INT,
  categoria VARCHAR(50),
  pendiente INT,
  netapa INT,
  dorsal INT,
  PRIMARY KEY (nompuerto),
  FOREIGN KEY (netapa) REFERENCES etapa(netapa),
  FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal)
);

CREATE TABLE maillot (
  codigo VARCHAR(10),
  tipo VARCHAR(20),
  color VARCHAR(20),
  premio DECIMAL(10, 2),
  PRIMARY KEY (codigo)
);

CREATE TABLE llevar (
  dorsal INT,
  netapa INT,
  codigo VARCHAR(10),
  PRIMARY KEY (dorsal, netapa, codigo),
  FOREIGN KEY (dorsal) REFERENCES ciclista(dorsal),
  FOREIGN KEY (netapa) REFERENCES etapa(netapa),
  FOREIGN KEY (codigo) REFERENCES maillot(codigo)
);

-- B) Escribe las sentencias SQL para:

-- 1. Obtener el código, el tipo, el color y el premio de todos los maillots que hay.

SELECT codigo, tipo, color, premio
FROM maillot;

-- 2. Obtener el dorsal y el nombre de los ciclistas cuya edad sea menor o igual que 25 años.

SELECT dorsal, nombre
FROM ciclista
WHERE edad <= 25;

-- 3. Obtener el nombre y la altura de todos los puertos de categoría ‘E’ (Especial).

SELECT nompuerto, altura
FROM puerto
WHERE categoria LIKE 'E%';

SELECT nompuerto, altura
FROM puerto
WHERE categoria LIKE 'E';

-- 4. Obtener el valor del atributo netapa de aquellas etapas con salida y llegada en la misma ciudad.

SELECT netapa
FROM etapa
WHERE salida = llegada;

-- 5. ¿Cuántos ciclistas hay?

SELECT COUNT(*) AS total_ciclistas
FROM ciclista;

-- 7. ¿Cuántos equipos hay?
SELECT COUNT(*) AS total_equipos
FROM equipo;

-- 8. Obtener la media de edad de los ciclistas. 

SELECT AVG(edad) AS edad_media
FROM ciclista;

-- 9. Obtener la altura mínima y máxima de los puertos de montaña.

SELECT MAX(altura) AS altura_min, MIN(altura) AS altura_max
FROM puerto;

-- 10. Obtener cuántas etapas empiezan en ciudades cuyo nombre empieza por A o B.

SELECT COUNT(*) AS etapas_A_B
FROM etapa
WHERE salida LIKE 'A%' OR salida LIKE 'B%';

-- 11. Agrupar las etapas por ciudad de llegada y contabilizar cuántas etapas llegan a cada una de las ciudades.

SELECT llegada, COUNT(*) AS num_etapas
FROM etapa
GROUP BY llegada;

-- 12. Obtener el dorsal que más etapas ha ganado.

SELECT dorsal, COUNT(*) AS etapas_ganadas
FROM etapa
GROUP BY dorsal
ORDER BY etapas_ganadas
LIMIT 1;

-- 13. Obtener la altura media de los puertos según su categoría.

SELECT categoria, AVG(altura) AS altura_media
FROM puerto
GROUP BY categoria;

-- 14. Obtener cuál es la categoría de los puertos que tiene una media de pendiente menor.

SELECT categoria, AVG(pendiente) AS pendiente_media
FROM puerto
GROUP BY categoria
ORDER BY pendiente_media ASC
LIMIT 1;

-- 15. Obtener el equipo que tiene más ciclistas.

SELECT nomeq, COUNT(*) AS num_ciclistas
FROM ciclista
GROUP BY nomeq
ORDER BY num_ciclistas DESC
LIMIT 1;

-- 16. Obtener el equipo que tiene el ciclista de mayor edad.

SELECT nomeq
FROM ciclista
WHERE edad = (
  SELECT MAX(edad)
  FROM ciclista
);

-- 17. ¿Cuál es el equipo más joven? (tiene una media de edad menor)

SELECT nomeq, AVG(edad) AS media_edad
FROM ciclista
GROUP BY nomeq
ORDER BY media_edad ASC
LIMIT 1;

-- 18. Obtener aquellos ciclistas cuyo apellido empieza por P.

SELECT nombre
FROM ciclista
WHERE nombre LIKE '% P%';

-- 19. Obtener qué edades no están repetidas.

SELECT edad
FROM ciclista
GROUP BY edad         -- Agrupa todos los ciclistas por edad
HAVING COUNT(*) = 1;  -- Cuenta cuántos ciclistas hay con cada edad
-- Filtra solo las edades que aparecen una vez
