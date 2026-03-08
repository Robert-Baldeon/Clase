-- ---------------------------------------------------------
-- 1. INSERCIÓN DE CINES
-- ---------------------------------------------------------
INSERT INTO `cine` (`idCine`, `nombreCine`, `poblacionCine`) VALUES
(1, 'Colon', 'Alzira'),
(2, 'Cervantes', 'Cullera'),
(3, 'Salón Giner', 'Carlet'),
(4, 'Centro Cultural Enric Valor', 'Benifaió'),
(5, 'Centre Cultural', 'Almussafes'),
(6, 'ABC Saler', 'Valencia'),
(7, 'ABC Park', 'Valencia'),
(8, 'Autocine Star', 'Valencia'),
(9, 'Cines Babel', 'Valencia'),
(10, 'Cines Lys', 'Valencia'),
(11, 'Cinestudio d´Or', 'Valencia'),
(12, 'Filmoteca Valencia', 'Valencia'),
(13, 'Hemisfèric', 'Valencia'),
(14, 'Ocine Aqua', 'Valencia'),
(15, 'Yelmo Cines Mercado de Campanar', 'Valencia'),
(16, 'Teatre LAgricola', 'Alboraia/Alboraya'),
(17, 'Teatro de verano Terrassa Lumiere', 'Alboraia/Alboraya'),
(18, 'Cinesa Bonaire', 'Aldaia'),
(19, 'Cines MN4', 'Alfafar'),
(20, 'Kinépolis Alzira', 'Alzira'),
(21, 'Centre Cultural El Molí', 'Benetússer'),
(22, 'Cines Victoria', 'Cullera'),
(23, 'ABC Gandía', 'Gandia'),
(24, 'Cine de verano Tugar', 'Gandia'),
(25, 'Cines Axion Premium Gandia', 'Gandia'),
(26, 'Teatro Capitolio', 'Godella'),
(27, 'Cine La Unió', 'Llíria'),
(28, 'Cine de verano Terraza Charly', 'Miramar'),
(29, 'Centre Cultural Blasco Ibáñez', 'Moncada'),
(30, 'Cine Museros', 'Museros'),
(31, 'Cinema Olimpia', 'Oliva'),
(32, 'Cineapolis El Teler', 'Ontinyent'),
(33, 'Kinépolis Valencia', 'Paterna'),
(34, 'Casa de Cultura', 'Pobla de Vallbona, la'),
(35, 'Casa cultura', 'Puçol'),
(36, 'Auditori Molí de Vila', 'Quart de Poblet'),
(37, 'Auditori/Terraza de verano', 'Riba-roja de Túria'),
(38, 'Alucine', 'Sagunto'),
(39, 'Yelmo Cines VidaNova Parc', 'Sagunto'),
(40, 'Cine de verano', 'Serra'),
(41, 'Cine de verano Olimpo Cine Terraza', 'Tavernes de la Valldigna'),
(42, 'Cine CAM', 'Torrent'),
(43, 'Cines Axión Xativa', 'Xàtiva'),
(44, 'ABC Gran Turia', 'Xirivella');

-- ---------------------------------------------------------
-- 2. INSERCIÓN DE SALAS
-- ---------------------------------------------------------
INSERT INTO `sala` (`idCine`, `idSala`, `butacasSala`) VALUES
(1, 1, 100), -- Cine Colon: Sala 1
(1, 2, 120), -- Cine Colon: Sala 2
(1, 3, 111), -- Cine Colon: Sala 3
(2, 1, 188); -- Cine Cervantes: Sala 1

-- ---------------------------------------------------------
-- 3. INSERCIÓN DE TICKETS
-- ---------------------------------------------------------
INSERT INTO `ticket` (`idTicket`, `idCine`, `idSala`, `dia`, `hora`, `precio`) VALUES
(1, 1, 2, '2018-01-25', '18:00:00', 8);
