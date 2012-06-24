CREATE DATABASE  IF NOT EXISTS `inventario` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `inventario`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: inventario
-- ------------------------------------------------------
-- Server version	5.5.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `entidad`
--

DROP TABLE IF EXISTS `entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidad` (
  `idEntidad` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `primerNombre` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  `segundoNombre` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  `primerApellido` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `segundoApellido` varchar(40) CHARACTER SET latin1 DEFAULT NULL,
  `idTipoDocumento` int(11) DEFAULT NULL,
  `identificacion` bigint(20) DEFAULT NULL,
  `razonSocial` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `idPais` int(10) unsigned DEFAULT NULL,
  `idmunicipio` int(10) unsigned DEFAULT NULL,
  `direccion` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `idTipoEntidad` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idEntidad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES (2,'Gilberth','Miguel','Linero','Ariza',6,1065571485,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `idPais` varchar(3) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES ('005','Alemania'),('010','American Samoa'),('015','Andorra'),('020','Antillas Netherlands'),('025','Argentina'),('030','Armenia'),('035','Aruba'),('040','Australia'),('045','Austria'),('050','Bahamas'),('055','Belgica'),('060','Bermudas'),('065','Bolivia'),('070','Brasil'),('075','Bulgaria'),('080','Burundi'),('085','Canada'),('090','Colombia'),('095','Corea'),('100','Costa Rica'),('105','Croatia'),('110','Cuba'),('115','Chile'),('120','China'),('125','Dinamarca'),('130','Ecuador'),('135','El Salvador'),('140','Espa√±a'),('145','Estados Unidos'),('150','Estonia'),('155','Faroe Islands'),('160','Filipinas'),('165','Finlandia'),('170','Francia'),('175','Gran Breta√±a'),('180','Grecia'),('185','Greenland'),('190','Guatemala'),('195','Guayana Francesa'),('200','Honduras'),('205','Hong Kong'),('210','Hungria'),('215','Irlanda'),('220','Isla de Man'),('225','Islas Caimanes'),('230','Islas Virgenes Inglesas'),('235','Islas Virgenes U.S.'),('240','Israel'),('245','Italia'),('250','Jamaica'),('255','Japon'),('260','Jordania'),('265','Libano'),('270','Liberia'),('275','Liechenstein'),('280','Lithuania'),('285','Luxemburgo'),('290','Malaysia'),('295','Mexico'),('300','Netherlands'),('305','Nicaragua'),('310','Norfolk Island'),('315','Norway - Noruega'),('320','Nueva Zelanda'),('325','Pakistan'),('330','Panama'),('335','Paraguay'),('340','Peru'),('345','Polonia'),('350','Portugal'),('355','Puerto Rico'),('360','Republica Checa'),('365','Republica Democratica del congo'),('370','Republica Dominicana'),('375','Rumania'),('380','Rusia'),('385','Rwanda'),('390','Singapur'),('395','Slovakia'),('400','Sri Lanka'),('405','Sur Africa'),('410','Suecia'),('415','Suiza'),('420','Taiwan'),('425','Thailandia'),('430','Tonga'),('435','Trinidad y Tobago'),('440','Turkia'),('445','Turmenistan'),('450','Uganda'),('455','Ukraine'),('460','Uruguay'),('465','Venezuela'),('470','Yugoslavia'),('475','Zaire'),('480','Otros Paises');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `idRoles` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`idRoles`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROOT'),(2,'Empleado'),(3,'Cliente');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `password` blob,
  `idRol` int(11) DEFAULT NULL,
  `identificacion` bigint(20) DEFAULT NULL,
  `idTipoDocumento` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (7,'glinero','ÃÌ}≈[¿S÷c@RŸÓR',1,1065571485,6);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumento`
--

DROP TABLE IF EXISTS `tipodocumento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodocumento` (
  `idTipoDocumento` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`idTipoDocumento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumento`
--

LOCK TABLES `tipodocumento` WRITE;
/*!40000 ALTER TABLE `tipodocumento` DISABLE KEYS */;
INSERT INTO `tipodocumento` VALUES (3,'Tarjeta de Extranjer√≠a'),(4,'C√©dula de Extranjer√≠a'),(5,'NIT'),(6,'C√©dula de Ciudadan√≠a');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idDispositivo` int(4) unsigned NOT NULL,
  `valor` double NOT NULL,
  `idComputador` int(4) unsigned NOT NULL,
  `idFactura` int(4) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (1,1,0,1,1),(2,2,0,2,1);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computador`
--

DROP TABLE IF EXISTS `computador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computador` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) CHARACTER SET latin1 NOT NULL,
  `nombre` varchar(20) CHARACTER SET latin1 NOT NULL,
  `idEmpleado` int(4) unsigned NOT NULL,
  `ubicacion` varchar(45) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador`
--

LOCK TABLES `computador` WRITE;
/*!40000 ALTER TABLE `computador` DISABLE KEYS */;
INSERT INTO `computador` VALUES (1,'1','PC Gilberth',1,'Dpto Sistemas'),(2,'1','PC Ejemplo',2,'Dpto Ejemplo');
/*!40000 ALTER TABLE `computador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numFactura` varchar(40) CHARACTER SET latin1 NOT NULL,
  `idEntidad` int(4) unsigned NOT NULL,
  `fecha` date NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,'12',1,'2012-04-03',123);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumentoaut`
--

DROP TABLE IF EXISTS `tipodocumentoaut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodocumentoaut` (
  `idTipoDocumento` int(10) unsigned NOT NULL,
  `campoEntidad` varchar(40) NOT NULL,
  `habilitar` tinyint(1) NOT NULL,
  `obligatorio` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTipoDocumento`,`campoEntidad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumentoaut`
--

LOCK TABLES `tipodocumentoaut` WRITE;
/*!40000 ALTER TABLE `tipodocumentoaut` DISABLE KEYS */;
INSERT INTO `tipodocumentoaut` VALUES (3,'idEntidad',1,1),(3,'identificacion',1,1),(3,'idTipoDocumento',1,1),(3,'primerApellido',1,1),(3,'primerNombre',1,1),(3,'razonSocial',1,1),(3,'segundoApellido',1,1),(3,'segundoNombre',1,1),(6,'direccion',1,1),(6,'email',1,1),(6,'idEntidad',1,1),(6,'identificacion',1,1),(6,'idmunicipio',1,1),(6,'idPais',1,1),(6,'idTipoDocumento',1,1),(6,'idTipoEntidad',1,1),(6,'primerApellido',1,1),(6,'primerNombre',1,1),(6,'razonSocial',0,0),(6,'segundoApellido',1,0),(6,'segundoNombre',1,0),(6,'telefono',1,1);
/*!40000 ALTER TABLE `tipodocumentoaut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolespermisos`
--

DROP TABLE IF EXISTS `rolespermisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolespermisos` (
  `idrol` int(10) unsigned NOT NULL,
  `modulo` varchar(45) NOT NULL,
  `formulario` varchar(45) NOT NULL,
  `accion` varchar(45) NOT NULL,
  `valor` tinyint(1) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolespermisos`
--

LOCK TABLES `rolespermisos` WRITE;
/*!40000 ALTER TABLE `rolespermisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `rolespermisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariopermisos`
--

DROP TABLE IF EXISTS `usuariopermisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariopermisos` (
  `idUsuario` int(10) unsigned NOT NULL,
  `modulo` varchar(45) NOT NULL,
  `formulario` varchar(45) NOT NULL,
  `accion` varchar(45) NOT NULL,
  `valor` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariopermisos`
--

LOCK TABLES `usuariopermisos` WRITE;
/*!40000 ALTER TABLE `usuariopermisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuariopermisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipios` (
  `idMunicipio` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPais` int(10) unsigned NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idMunicipio`,`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipios`
--

LOCK TABLES `municipios` WRITE;
/*!40000 ALTER TABLE `municipios` DISABLE KEYS */;
/*!40000 ALTER TABLE `municipios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Descripcion` varchar(30) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
INSERT INTO `dispositivo` VALUES (1,'Monitor 17\'\' Acer','Modelo tal'),(2,'CPU','CPU');
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-18 22:25:40
