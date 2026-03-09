-- 1. Selecciona el nombre de los cines de Valencia

SELECT nombreCine
FROM cine
WHERE poblacionCine = 'Valencia';

-- 2. Selecciona nombre e id de los cines de Cullera o Gandia

SELECT nombreCine, idCine
FROM cine
WHERE poblacionCine IN ('Cullera', 'Gandia');

-- 3. Selecciona nombre y población de los cines con id menor que 10 o id mayor que 30

SELECT nombreCine, poblacionCine
FROM cine
WHERE idCine < 10 OR idCine > 30;

-- 4. Selecciona el nombre y población de los cines cuyo nombre empieza por ‘C’.
-- Muestra el resultado ordenado por población

SELECT nombreCine, poblacionCine
FROM cine
WHERE nombreCine LIKE 'C%'
ORDER BY poblacionCine;

-- 5. Selecciona la población de los cines cuyo nombre termina en ‘a’.
-- Muestra el resultado ordenado por población de forma descendente

SELECT poblacionCine
FROM cine
WHERE nombreCine LIKE '%a'
ORDER BY poblacionCine DESC;

-- 6. Selecciona todos los datos de la tabla cine para los cines con id comprendido entre 8 y 15

SELECT *
FROM cine
WHERE idCine BETWEEN 8 AND 15;

-- 7. Selecciona el nombre y población de los cines con id mayor que 32 que tengan en el nombre de la población al menos una ‘o’ y al menos dos ‘e’ en el nombre.
-- Muestra el resultado ordenado por población de forma descendente y nombre de forma ascendente.
SELECT nombreCine, poblacionCine
FROM cine
WHERE idCine > 32
  AND poblacionCine LIKE '%o%'
  AND poblacionCine LIKE '%e%e%'
ORDER BY poblacionCine DESC, nombreCine ASC;

-- 8. Selecciona las distintas poblaciones de los cines que tienen en su nombre una ‘u’ seguida de cualquier carácter y a continuación una ‘a’ (ejemplos: ura, una, upa…).
-- Muestra únicamente los 2 primeros registros que cumplen con las condiciones.

SELECT DISTINCT poblacionCine
FROM cine
WHERE nombreCine LIKE '%u_a%'
LIMIT 2;

-- 9. Selecciona población, cine e id de los cines cuyo id es mayor que 30 y que son de Sagunto o tienen en su nombre la secuencia de caracteres ‘ca’.
-- Ordena el resultado por población y cine, ambas de forma ascendente

SELECT poblacionCine, nombreCine, idCine
FROM cine
WHERE idCine > 30
  AND (poblacionCine = 'Sagunto' OR poblacionCine LIKE '%ca%')
ORDER BY poblacionCine ASC, nombreCine ASC;

-- 10. Realiza la consulta para obtener cuántos cines hay en Valencia.

SELECT COUNT(*)
FROM cine
WHERE poblacionCine = 'Valencia';

-- 11. Selecciona nombre y población de los 5 primeros cines, considerando el orden por id, cuyo nombre empieza por ‘cine’.

SELECT nombreCine, poblacionCine
FROM cine
WHERE nombreCine LIKE 'Cine%'
ORDER By idCine
LIMIT 5;

-- 12. Selecciona nombre y población de los 5 primeros cines, cuyo nombre empieza por ‘cine’, considerando el orden descendente por población.

SELECT nombreCine, poblacionCine
FROM cine
WHERE nombreCine LIKE 'Cine%'
ORDER BY poblacionCine DESC
LIMIT 5;

-- 13. Selecciona nombre e id de los 8 últimos cines, considerando el orden por nombre de cine.

SELECT nombreCine, idCine
FROM cine
ORDER BY nombreCine DESC
LIMIT 8;

SELECT nombreCine, idCine
FROM (
  SELECT nombreCine, idCine
  FROM cine
  ORDER BY nombreCine DESC
  LIMIT 8
) AS ultimos
ORDER BY nombreCine ASC;

-- 14. Selecciona el nombre y población de los cines que tienen en su nombre la secuencia ‘ABC’ o que son de Gandía.

SELECT nombreCine, poblacionCine
FROM cine
WHERE nombreCine LIKE '%ABC%'
  OR poblacionCine = 'Gandia';

-- 15. Indica cuántos cines tienen el nombre acabado en ‘o’.

SELECT COUNT(*)
FROM cine
WHERE nombreCine LIKE '%o';
