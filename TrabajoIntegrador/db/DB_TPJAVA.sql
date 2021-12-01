CREATE DATABASE  IF NOT EXISTS `trabajointegrador` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trabajointegrador`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: trabajointegrador
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `precioXdia` double NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'chico',1000),(2,'mediano',2000),(3,'grande',3000);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `idReserva` int NOT NULL AUTO_INCREMENT,
  `fechaReserva` datetime DEFAULT NULL,
  `fechaRetiro` date NOT NULL,
  `fechaDevolucion` date NOT NULL,
  `fechaCancelacion` datetime DEFAULT NULL,
  `motivoCancelacion` varchar(100) DEFAULT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`idReserva`),
  UNIQUE KEY `idReserva_UNIQUE` (`idReserva`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (6,'2021-04-20 23:28:13','2021-04-21','2021-04-28','2021-04-20 23:41:22',NULL,'Cancelada'),(7,'2021-04-20 23:41:41','2021-04-28','2021-05-13','2021-04-20 23:43:41',NULL,'Cancelada'),(8,'2021-04-20 23:44:13','2021-04-22','2021-04-30','2021-04-20 23:49:38',NULL,'Cancelada'),(9,'2021-07-12 18:23:58','2021-07-21','2021-07-29','2021-07-12 18:47:28',NULL,'Cancelada'),(10,'2021-07-14 10:59:59','2021-07-16','2021-07-31',NULL,NULL,'Iniciada'),(11,'2021-07-14 12:20:28','2021-07-16','2021-07-29','2021-07-14 12:25:37',NULL,'Cancelada'),(12,'2021-07-14 12:25:55','2021-07-22','2021-07-29','2021-07-14 12:28:17',NULL,'Cancelada'),(13,'2021-07-14 12:46:11','2021-07-23','2021-07-31','2021-07-14 12:48:26',NULL,'Cancelada'),(14,'2021-07-14 12:48:34','2021-07-16','2021-07-31','2021-07-15 12:42:48',NULL,'Cancelada'),(16,'2021-07-15 12:45:16','2021-07-17','2021-07-30','2021-07-15 12:45:30',NULL,'Cancelada'),(17,'2021-07-15 18:35:08','2021-07-16','2021-07-30','2021-07-19 19:11:55',NULL,'Cancelada'),(18,'2021-08-02 10:02:15','2021-08-17','2021-08-31','2021-08-02 11:44:06',NULL,'Cancelada'),(19,'2021-08-02 11:44:17','2021-08-26','2021-08-31','2021-08-02 18:07:05',NULL,'Cancelada'),(20,'2021-08-02 21:23:18','2021-08-04','2021-08-31','2021-08-05 11:06:24','Seleccion� mal las fechas','Cancelada');
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_usuario_vehiculo`
--

DROP TABLE IF EXISTS `reserva_usuario_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_usuario_vehiculo` (
  `id_reserva` int NOT NULL,
  `id_vehiculo` int NOT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_reserva`,`id_vehiculo`,`id_usuario`),
  KEY `usuario_ffkk_idx` (`id_usuario`),
  KEY `vehiculofkk_idx` (`id_vehiculo`),
  CONSTRAINT `reserva_fk` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`idReserva`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario_ffkk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehiculofkk` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`idVehiculo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_usuario_vehiculo`
--

LOCK TABLES `reserva_usuario_vehiculo` WRITE;
/*!40000 ALTER TABLE `reserva_usuario_vehiculo` DISABLE KEYS */;
INSERT INTO `reserva_usuario_vehiculo` VALUES (9,31,2),(6,17,8),(7,25,8),(8,24,8),(11,31,43),(12,17,43),(13,31,43),(14,31,43),(16,35,43),(17,35,43),(18,8,43),(19,35,43),(20,8,43);
/*!40000 ALTER TABLE `reserva_usuario_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'empleado'),(2,'cliente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) NOT NULL,
  `contraseña` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `tipoDocumento` varchar(45) NOT NULL,
  `nroDocumento` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  UNIQUE KEY `nroDocumento_UNIQUE` (`nroDocumento`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'nt@gmail.com','NINO','dni','67891','Nino','Tell','34567','Casa Negra 456'),(8,'fp@gmail.com','fperez','DNI','4056464','Fulanito','Perez','35468564','Rivadavia 3657'),(10,'sd@gmail.com','sdiaz','DNI','40148978','Sultano','Diaz','3465044','Almirante Brown 654'),(34,'ms@gmail.com','mercedes','DNI','15448235','Mercedes','Sosa','3411141455','Balcarce 534'),(35,'ej@gmail.com','ejimenez','DNI','12554756','Ernesto','Jimenez','1145354889','Nicolas Levalle 738'),(36,'bd@gmail.com','bdylan','DNI','28477564','Bob','Dylan','3415847568','Almirante Brown 549'),(37,'fl@gmail.com','flopez','DNI','29554787','Fausto','Lopez','3401154875','Corrientes 1055'),(38,'bh@gmail.com','bhernandez','DNI','37664854','Brian','Hernandez','3422568486','Entre Rios 746'),(40,'bp@gmail.com','bpatiño','DNI','35447955','Bob','Patiño','3415465842','Bv. Alsina 354'),(41,'giorgiotell@gmail.com','giorgio','DNI','35135111','Bob','Patiño','3535435435','Pellegrini 351'),(43,'ninotell@gmail.com','ninotell','DNI','40875646','Patiño','De Elía','3435411488','Almirante 1554');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` int NOT NULL,
  `id_rol` int NOT NULL,
  `fecha_cambio` datetime NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`,`fecha_cambio`),
  KEY `rol_fk_idx` (`id_rol`),
  CONSTRAINT `rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuario_fk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (2,1,'2021-03-31 20:26:22'),(8,1,'2021-07-12 17:42:53'),(8,1,'2021-07-12 17:43:51'),(8,1,'2021-07-12 17:43:53'),(8,1,'2021-07-12 17:43:55'),(8,1,'2021-07-12 17:45:06'),(40,1,'2021-07-17 20:05:13'),(8,2,'2021-07-12 17:42:41'),(8,2,'2021-07-12 17:43:03'),(8,2,'2021-07-12 17:43:52'),(8,2,'2021-07-12 17:43:54'),(8,2,'2021-07-12 17:43:56'),(8,2,'2021-07-12 17:45:31'),(10,2,'2021-04-06 23:13:43'),(34,2,'2021-04-13 18:46:58'),(35,2,'2021-04-13 18:48:32'),(36,2,'2021-04-13 18:49:20'),(37,2,'2021-04-13 18:52:14'),(38,2,'2021-04-13 18:53:53'),(40,2,'2021-07-14 12:12:50'),(40,2,'2021-07-17 20:05:28'),(41,2,'2021-07-14 12:14:07'),(43,2,'2021-07-14 12:19:49');
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `idVehiculo` int NOT NULL AUTO_INCREMENT,
  `patente` varchar(10) NOT NULL,
  `marca` varchar(10) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `año` varchar(45) NOT NULL,
  `transmision` varchar(10) NOT NULL,
  `km` int NOT NULL,
  `foto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idVehiculo`),
  UNIQUE KEY `patente_UNIQUE` (`patente`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (1,'AA111AA','Ford','Focus','2016','Automático',12854,'https://ucarecdn.com/7809ae3a-aedd-40b0-920f-59511daa7d51/focus.jpg'),(2,'BB222BB','Toyota','Corolla','2017','Manual',25352,'https://ucarecdn.com/85797ba7-4079-4695-91b8-7f0646e30154/corolla.jpg'),(3,'CC333CC','Renault','Kwid','2019','Manual',10000,'https://ucarecdn.com/22d89b5d-55d4-42ad-b24b-c508f78231c7/kwid.jpg'),(5,'EE555EE','Toyota','Hilux','2020','Automático',12660,'https://ucarecdn.com/a22b7617-e97b-4e0e-80ba-844af21a1a4f/hilux.jpg'),(8,'HH888HH','Citroen','Cactus','2019','Automático',12624,'https://ucarecdn.com/47e27a71-9916-4753-b2c9-9f4d173d184c/'),(17,'PLH 216','Chevrolet','Onix','2016','Automático',45500,'https://ucarecdn.com/8f12875d-c545-4ca0-80cd-fced692892a3/'),(22,'AE638FF','Ford','Ranger','2021','Automático',770,'https://ucarecdn.com/2aea604d-9d46-44e5-87d5-f05e7272b4b2/ranger.jpg'),(23,'AA154BE','Ford','Ka','2016','Manual',24381,'https://ucarecdn.com/78ebfe2c-59bb-41e3-a9c4-4a75b5fec377/ka.jpg'),(25,'AA 325 JF','DS','DS4','2021','Automático',100,'https://ucarecdn.com/5b4a56c7-f44a-4109-af19-ab8e87e9b239/'),(31,'AB 317 FK','Chevrolet','Tracker','2020','Automático',14747,'https://ucarecdn.com/2bd5f982-2caa-42f5-af9d-648cf0dec4d6/'),(32,'AC 493 ZQ','Chevrolet','Camaro','2020','Manual',4677,'https://ucarecdn.com/a35e2d6e-c6f0-4c09-9940-3069243cb32a/camaro.webp'),(35,'AB 123 KK','Ford','Fiesta','2018','Automático',7800,'https://ucarecdn.com/8c60acb8-2792-4859-98bf-32e8906b5e91/fiestasinfondo.png'),(44,'AE 156 CG','Chevrolet','Cruze','2019','Automatico',6950,'https://ucarecdn.com/d3875f97-ea49-4137-93e6-b6cd51f234a4/cruze.jpg');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos_categorias`
--

DROP TABLE IF EXISTS `vehiculos_categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos_categorias` (
  `id_vehiculo` int NOT NULL,
  `id_categoria` int NOT NULL,
  PRIMARY KEY (`id_vehiculo`,`id_categoria`),
  KEY `vehiculos_categorias_vehiculos_fk_idx` (`id_vehiculo`),
  KEY `categoría_fk1` (`id_categoria`),
  CONSTRAINT `categoria-fkk` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `vehiculo_fkk` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`idVehiculo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos_categorias`
--

LOCK TABLES `vehiculos_categorias` WRITE;
/*!40000 ALTER TABLE `vehiculos_categorias` DISABLE KEYS */;
INSERT INTO `vehiculos_categorias` VALUES (1,2),(2,2),(3,1),(5,3),(8,2),(17,1),(22,3),(23,1),(25,2),(31,2),(32,2),(35,1),(44,2);
/*!40000 ALTER TABLE `vehiculos_categorias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-29 21:55:06
