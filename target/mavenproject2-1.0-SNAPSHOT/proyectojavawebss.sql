-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-06-2022 a las 09:02:03
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectojavawebss`
--
CREATE DATABASE IF NOT EXISTS `proyectojavawebss` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyectojavawebss`;

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addAlumno` (IN `pusuario` VARCHAR(255), IN `pnombre` VARCHAR(255), IN `pcontraseña` VARCHAR(255))   BEGIN
INSERT INTO `proyectojavawebss`.`alumno`(`usuario`,`nombre`,`contraseña`)
VALUES(pusuario,pnombre,pcontraseña);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addGrupo` (IN `pclaveMateriaGrupo` INT, IN `pnumAlumnos` INT)   BEGIN
INSERT INTO `proyectojavawebss`.`grupo`(`claveMateriaGrupo`,`numAlumnos`)
VALUES(pclaveMateriaGrupo,pnumAlumnos);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addMaestro` (IN `pnombreMaestro` VARCHAR(255), IN `pusuario` VARCHAR(255), IN `pcontraseña` VARCHAR(255), IN `ptipo` VARCHAR(255))   BEGIN
INSERT INTO `proyectojavawebss`.`maestro`(`nombreMaestro`,`usuario`,`contraseña`,`tipo`)
VALUES(pnombreMaestro,pusuario,pcontraseña,ptipo);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `addMateria` (IN `pnombreMateria` VARCHAR(255))   BEGIN
INSERT INTO `proyectojavawebss`.`materia`(`nombreMateria`)
VALUES(pnombreMateria);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `darBajaMateria` (IN `pidGrupoInscrito` INT, IN `pidAlumnoInscrito` INT)   BEGIN
DELETE FROM `proyectojavawebss`.`materiasinscritas`
WHERE idGrupoInscrito=pidGrupoInscrito AND idAlumnoInscrito=pidAlumnoInscrito;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarAlumno` (IN `pmatricula` INT, IN `pusuario` VARCHAR(255), IN `pnombre` VARCHAR(255), IN `pcontraseña` VARCHAR(255), IN `pestatus` BOOLEAN)   BEGIN
	IF pcontraseña="" THEN
		UPDATE `proyectojavawebss`.`alumno`
		SET
		`usuario` = pusuario,
		`nombre` = pnombre,
		`estatus` = pestatus
		WHERE `matricula` = pmatricula;
	ELSE 
		UPDATE `proyectojavawebss`.`alumno`
		SET
		`usuario` = pusuario,
		`nombre` = pnombre,
		`contraseña` = pcontraseña,
		`estatus` = pestatus
		WHERE `matricula` = pmatricula;
	END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarGrupo` (IN `pidGrupo` INT, IN `pclaveMateriaGrupo` INT, IN `pnumAlumnos` INT, IN `pestatus` BOOLEAN)   BEGIN
UPDATE `proyectojavawebss`.`grupo`
SET
`claveMateriaGrupo` = pclaveMateriaGrupo,
`numAlumnos` = pnumAlumnos,
`estatus` = pestatus
WHERE `idGrupo` = pidGrupo;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarMaestro` (IN `pnoEmpleado` INT, IN `pnombreMaestro` VARCHAR(255), IN `pusuario` VARCHAR(255), IN `pcontraseña` VARCHAR(255), IN `pestatus` BOOLEAN)   BEGIN
declare contraseñaAux varchar(255);
select contraseña into contraseñaAux from `proyectojavawebss`.`maestro` WHERE `noEmpleado` = pnoEmpleado;
IF contraseñaAux=pcontraseña THEN
	UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
ELSE 
	UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`contraseña` = pcontraseña,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
END IF;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarMaestro2` (IN `pnoEmpleado` INT, IN `pnombreMaestro` VARCHAR(255), IN `pusuario` VARCHAR(255), IN `pcontraseña` VARCHAR(255), IN `pestatus` BOOLEAN)   BEGIN
IF pcontraseña="" THEN
	UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
ELSE 
    UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`contraseña` = pcontraseña,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `editarMateria` (IN `pclaveMateria` INT, IN `pnombreMateria` VARCHAR(255), IN `pestatus` BOOLEAN)   BEGIN
UPDATE `proyectojavawebss`.`materia`
SET
`nombreMateria` = pnombreMateria,
`estatus` = pestatus
WHERE `claveMateria` = pclaveMateria;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `elimimnarAlumno` (IN `pmatricula` INT)   BEGIN
DELETE FROM `proyectojavawebss`.`alumno`
WHERE matricula=pmatricula;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarGrupo` (IN `pidGrupo` INT)   BEGIN
	UPDATE `proyectojavawebss`.`grupo`
	SET
	`estatus` = 0
	WHERE `idGrupo` = pidGrupo;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMaestro` (IN `pnoEmpleado` INT)   BEGIN
DELETE FROM `proyectojavawebss`.`maestro`
WHERE pnoEmpleado=noEmpleado;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMateria` (IN `pclaveMateria` INT)   BEGIN
UPDATE `proyectojavawebss`.`materia`
SET
`estatus` = 0
WHERE `claveMateria` = pclaveMateria;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAlumnoByID` (IN `pmatricula` INT)   BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`
WHERE pmatricula=matricula;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAlumnos` ()   BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`contraseña`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBusquedaAlumno` (IN `pnombre` VARCHAR(255))   BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`contraseña`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`
WHERE nombreMaestro LIKE CONCAT('%',pnombre,'%');

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBusquedaGrupos` (IN `pclaveMateriaGrupo` INT)   BEGIN
SELECT `grupo`.`idGrupo`,
    `grupo`.`claveMateriaGrupo`,
    `grupo`.`numAlumnos`
FROM `proyectojavawebss`.`grupo`;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBusquedaMaestro` (IN `pnombreMaestro` VARCHAR(255))   BEGIN
	SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`contraseña`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`
WHERE nombreMaestro LIKE CONCAT('%',pnombreMaestro,'%');

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBusquedaMateria` (IN `pnombreMateria` VARCHAR(255))   BEGIN
SELECT `materia`.`claveMateria`,
    `materia`.`nombreMateria`,
    `materia`.`estatus`
FROM `proyectojavawebss`.`materia`;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrupoByID` (IN `pidGrupo` INT)   BEGIN
SELECT `grupo`.`idGrupo`,
    `grupo`.`claveMateriaGrupo`,
    `grupo`.`numAlumnos`,
    `grupo`.`estatus`
FROM `proyectojavawebss`.`grupo`
WHERE pidGrupo=idGrupo;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrupos` ()   BEGIN
SELECT `grupo`.`idGrupo`,
    `grupo`.`claveMateriaGrupo`,
    `grupo`.`numAlumnos`,
    `grupo`.`estatus`
FROM `proyectojavawebss`.`grupo`;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMaestroById` (IN `pnoEmpleado` INT)   BEGIN
SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`
WHERE noEmpleado=pnoEmpleado;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMaestros` ()   BEGIN
SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`contraseña`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMateriaByID` (IN `pclaveMateria` INT)   BEGIN
SELECT `materia`.`claveMateria`,
    `materia`.`nombreMateria`,
    `materia`.`estatus`
FROM `proyectojavawebss`.`materia`
WHERE claveMateria=pclaveMateria;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMaterias` ()   BEGIN
SELECT `materia`.`claveMateria`,
    `materia`.`nombreMateria`,
    `materia`.`estatus`
FROM `proyectojavawebss`.`materia`;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `inscribirMateria` (IN `pidGrupoInscrito` INT, IN `pidAlumnoInscrito` INT)   BEGIN
INSERT INTO `proyectojavawebss`.`materiasinscritas`
(`idGrupoInscrito`,
`idAlumnoInscrito`)
VALUES
(pidGrupoInscrito,pidAlumnoInscrito);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logInEstudiante` (IN `pusuario` VARCHAR(255), IN `pcontraseña` VARCHAR(255))   BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`
WHERE pusuario=usuario AND pcontraseña=contraseña;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `logInMaestro` (IN `pusuario` VARCHAR(255), IN `pcontraseña` VARCHAR(255))   BEGIN
SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`
WHERE pusuario=usuario AND pcontraseña=contraseña;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `matricula` int(11) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `contraseña` varchar(8) DEFAULT NULL,
  `estatus` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`matricula`, `usuario`, `nombre`, `contraseña`, `estatus`) VALUES
(1, 'alumno1', 'nombre alumno 1', 'Abcde5', 1),
(2, 'alumno2', 'nombre alumno 2', 'Abcd6', 1),
(4, 'alumno4Edit', 'nombre alumno 4', 'Abcd7', 0),
(6, 'alumno6', 'nombre alumno 6', 'Abcde5', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `idGrupo` int(11) NOT NULL,
  `claveMateriaGrupo` int(11) DEFAULT NULL,
  `numAlumnos` int(11) DEFAULT NULL,
  `estatus` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `grupo`
--

INSERT INTO `grupo` (`idGrupo`, `claveMateriaGrupo`, `numAlumnos`, `estatus`) VALUES
(1, 1, 32, 0),
(2, 3, 54, 1),
(3, 4, 45, 1),
(4, 4, 28, 1),
(5, 9, 60, 0),
(6, 6, 50, 1),
(7, 9, 16, 1),
(8, 1, 25, 1),
(9, 2, 26, 1),
(10, 3, 27, 1),
(11, 4, 28, 1),
(12, 5, 33, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maestro`
--

CREATE TABLE `maestro` (
  `noEmpleado` int(11) NOT NULL,
  `nombreMaestro` varchar(255) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `contraseña` varchar(8) NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `estatus` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `maestro`
--

INSERT INTO `maestro` (`noEmpleado`, `nombreMaestro`, `usuario`, `contraseña`, `tipo`, `estatus`) VALUES
(1, 'Admin', 'Admin', '12345', 'Administrador', 1),
(2, 'brandon loera', 'brando', 'Abcd5', 'maestro', 1),
(4, 'nombre maestro 3', 'maestro3 editado', 'Abcd5', 'maestro', 0),
(6, 'nombre maestro 5', 'maestro5 edit', 'Abcd6', 'maestro', 1),
(7, 'nombre maestro 6', 'maestro6', 'Abcde5', 'maestro1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE `materia` (
  `claveMateria` int(11) NOT NULL,
  `nombreMateria` varchar(255) DEFAULT NULL,
  `estatus` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`claveMateria`, `nombreMateria`, `estatus`) VALUES
(1, 'materia1', 1),
(2, 'materia2', 1),
(3, 'progra basica', 1),
(4, 'poo', 1),
(5, 'progra web', 1),
(6, 'materia 344', 0),
(7, 'materia 4', 0),
(8, 'materia5', 1),
(9, 'materia6', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiasinscritas`
--

CREATE TABLE `materiasinscritas` (
  `idGrupoInscrito` int(11) NOT NULL,
  `idAlumnoInscrito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materiasinscritas`
--

INSERT INTO `materiasinscritas` (`idGrupoInscrito`, `idAlumnoInscrito`) VALUES
(2, 1),
(3, 1),
(4, 1),
(7, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`matricula`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`idGrupo`),
  ADD KEY `fkmateria` (`claveMateriaGrupo`);

--
-- Indices de la tabla `maestro`
--
ALTER TABLE `maestro`
  ADD PRIMARY KEY (`noEmpleado`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `materia`
--
ALTER TABLE `materia`
  ADD PRIMARY KEY (`claveMateria`);

--
-- Indices de la tabla `materiasinscritas`
--
ALTER TABLE `materiasinscritas`
  ADD PRIMARY KEY (`idGrupoInscrito`,`idAlumnoInscrito`),
  ADD KEY `fkAlumnoInscrito` (`idAlumnoInscrito`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `grupo`
--
ALTER TABLE `grupo`
  MODIFY `idGrupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `maestro`
--
ALTER TABLE `maestro`
  MODIFY `noEmpleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `materia`
--
ALTER TABLE `materia`
  MODIFY `claveMateria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD CONSTRAINT `fkmateria` FOREIGN KEY (`claveMateriaGrupo`) REFERENCES `materia` (`claveMateria`);

--
-- Filtros para la tabla `materiasinscritas`
--
ALTER TABLE `materiasinscritas`
  ADD CONSTRAINT `fkAlumnoInscrito` FOREIGN KEY (`idAlumnoInscrito`) REFERENCES `alumno` (`matricula`),
  ADD CONSTRAINT `fkGrupoInscrito` FOREIGN KEY (`idGrupoInscrito`) REFERENCES `grupo` (`idGrupo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
