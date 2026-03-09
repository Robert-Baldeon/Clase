-- Sobre BDCines:
-- 1. Cuántos pueblos o ciudades tienen cine.

SELECT COUNT(DISTINCT poblacionCine) AS totalPoblaciones
FROM cine;

-- 2. Cuántas salas hay en total en todos los cines.

SELECT COUNT(*) AS totalSalas
FROM sala;

-- 3. Cuántas salas hay en el cine ‘Colon’ (idCine = 1).

SELECT COUNT(*)
FROM sala
WHERE idCine = 1;

-- 4. Cuántas salas hay en cada uno de los cines. Escribe una única consulta.

SELECT idCine, COUNT(*) AS totalSalas
FROM sala
GROUP BY idCine;

-- 5. Cuántos cines hay en cada ciudad.

SELECT poblacionCine, COUNT(*) AS totalCines
FROM cine
GROUP BY poblacionCine;

-- 6. Cines que tienen en la penúltima letra de su nombre una ‘e’.

SELECT nombreCine
FROM cine
WHERE nombreCine LIKE '%e_';

-- 7. Para cada cine mostrar el máximo número de butacas.

SELECT idCine, MAX(butacasSala) AS maxButacas
FROM sala
GROUP BY idCine;

-- 8. Qué sala tiene el mínimo número de butacas.

SELECT *
FROM sala
ORDER BY maxButacas ASC
LIMIT 1;

SELECT *
FROM sala
WHERE butacasSala = (
  SELECT MIN(butacasSala)
  FROM sala;
);

-- 9. Media de todas las butacas de todos los cines.

SELECT AVG(butacasSala)
FROM sala;

-- 10. Total de todas las butacas de todos los cines.

SELECT SUM(butacasSala) AS totalButacas
FROM sala

-- 11. Media de butacas por cine.

SELECT idCine, AVG(butacasSala) AS mediaButacas
FROM sala
GROUP BY idCine;

-- 12. Media de butacas por cine de aquellas salas que tienen al menos 110 butacas.

SELECT idCine, AVG(butacasSala) AS mediaButacas
FROM sala
WHERE butacasSala >= 110
GROUP BY idCine;

--Sobre BDHoteles ejecuta las consultas que:

-- 13. Devuelva todos los registros de habitación con los m2 incrementados en 10.

SELECT idHotel, idHabitacion, m2 + 10 AS m2_incrementados, precio, camas
FROM habitacion;

-- 14. Devuelva aquellos registros cuyos m2 divididos entre 2 resulten en un valor > 15.

SELECT *
FROM habitacion
WHERE m2 / 2 > 15;

-- 15. Devuelva los registros con el número de camas duplicado para aquellos hoteles con idhotel > 2.

SELECT idHotel, camas, COUNT(*) AS numHabitaciones
FROM habitacion
WHERE idHotel > 2
GROUP BY idHotel, camas
HAVING COUNT(*) > 1;

-- 16. Devuelva los registros de las habitaciones con dos camas o más aplicando un descuento del 10% sobre el precio (añade una columna con el precio con descuento).

SELECT idHotel, idHabitacion, m2, precio - precio * 0.10 AS descuento, camas
FROM habitacion
WHERE camas >= 2;

-- 17. Devuelva las habitaciones con tamaño mayor a 35m2 con el precio duplicado.

SELECT idHotel, precio, COUNT(*) AS numHabitaciones
FROM habitacion
WHERE m2 > 35             -- Solo habitaciones mayores que 35m2
GROUP BY idHotel, precio  -- Agrupa por hotel y precio, para encontrar duplicados dentro de un hotel (o puedes quitar idHotel si quieres duplicados globales)
HAVING COUNT(*) > 1;      -- Filtra aquellos precios que aparecen más de una vez, es decir, duplicados

-- 18. Habitaciones que tienen menos de 35 m2 con precio mayor o igual a 50€.

SELECT *
FROM habitacion
WHERE m2 < 35
  AND precio >= 50;

-- 19. Precio máximo de las habitaciones agrupadas por número de camas.

SELECT camas, MAX(precio) AS precioMaximo
FROM habitacion
GROUP BY camas;

-- 20. Precio mínimo de las habitaciones agrupadas por número de camas.

SELECT camas, MIN(Precio) AS precioMinimo
FROM habitacion
GROUP BY camas;

-- 21. Media del precio de las habitaciones por hotel.

SELECT idHotel, AVG(precio) AS mediaPrecio
FROM habitacion
GROUP BY idHotel;

-- 22. Media del precio de las habitaciones por hotel de aquellas habitaciones que tienen más de 1 cama.

SELECT idHotel, AVG(precio) AS mediaPrecio
FROM habitacion
WHERE camas > 1;
GROUP BY idHotel;

-- 23. Cuántas camas hay en total considerando las que se encuentran en habitaciones de más de 30m2.

SELECT SUM(camas) AS totalCamas
FROM habitacion
WHERE m2 > 30;

-- 24. ¿Cuántas camas hay por hotel?

SELECT SUM(camas) AS totalCamas
FROM habitacion
GROUP BY idHotel;

-- 25. ¿En qué hotel (id) se encuentra la habitación más barata?

SELECT idHotel
FROM habitacion
WHERE precio = (
  SELECT min(precio)
  FROM habitacion
);

SELECT idHotel
FROM habitacion
ORDER BY precio ASC
LIMIT 1;

-- 26. ¿En qué hotel (id) se encuentra la habitación de mayor tamaño?

SELECT idHotel
FROM habitacion
WHERE m2 = (
  SELECT MAX(m2)
  FROM habitacion
);

SELECT idhotel
FROM habitacion
ORDER BY m2 DESC
LIMIT 1;

-- 27. Habitaciones que tienen número de camas par.

SELECT *
FROM habitacion
WHERE camas % 2 = 0;

-- 28. ¿Cuántas habitaciones con número de camas impar tiene cada hotel?

SELECT idHotel, COUNT(*) AS numHabitaciones
FROM habitacion
WHERE camas % 2 != 0
GROUP BY idHotel;

-- 29. Suma del precio de todas las habitaciones agrupando por hotel.

SELECT idHotel, sum(precio) AS totalPrecio
FROM habitacion
GROUP BY idHotel;
