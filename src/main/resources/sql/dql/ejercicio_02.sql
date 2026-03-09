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

15. Devuelva los registros con el número de camas duplicado para aquellos hoteles
con idhotel >2
16. Devuelva los registros de las habitaciones con dos camas o más aplicando un
descuento del 10% sobre el precio (añade una columna con el precio con
descuento)
17. Devuelva las habitaciones con tamaño mayor a 35m2 con el precio duplicado.
18. Habitaciones que tienen menos de 35 m2 con precio mayor o igual a 50€
19. Precio máximo de las habitaciones agrupadas por número de camas
20. Precio mínimo de las habitaciones agrupadas por número de camas
3
21. Media del precio de las habitaciones por hotel
22. Media del precio de las habitaciones por hotel de aquellas habitaciones que
tienen más de 1 cama.
23. Cuántas camas hay en total considerando las que se encuentran en
habitaciones de más de 30m2
24. ¿Cuántas camas hay por hotel?
25. ¿En qué hotel (id) se encuentra la habitación más barata?
26. ¿En qué hotel (id) se encuentra la habitación de mayor tamaño?
27. Habitaciones que tienen número de camas par.
28. ¿Cuántas habitaciones con número de camas impar tiene cada hotel?
29. Suma del precio de todas las habitaciones agrupando por hotel.
