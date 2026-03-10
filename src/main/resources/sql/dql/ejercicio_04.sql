-- 1. Obtener el nombre y la categoría de los puertos ganados por ciclistas del equipo ‘Banesto’.

SELECT p.nompuerto, p.categoria
FROM puerto p
JOIN ciclista c ON p.dorsal = c.dorsal
WHERE c.nomeq = 'Banesto';

-- 2. Obtener el nombre del cada puerto indicando el número (netapa) y los kilómetros de la etapa en la que se encuentra el puerto.

SELECT p.nompuerto, p.netapa, e.km
FROM puerto p
JOIN etapa e ON p.netapa = e.netapa;

-- 3. Obtener el ciclista y edad de los ciclistas que han llevado el maillot Rosa.

SELECT c.nombre, c.edad
FROM ciclista c
JOIN llevar l ON c.dorsal = l.dorsal
JOIN maillot m ON l.codigo = m.codigo
WHERE m.color = 'Rosa';

-- 4. Obtener el nombre de los equipos que tienen ciclistas que hayan ganado el premio de 8.000.000.

SELECT
