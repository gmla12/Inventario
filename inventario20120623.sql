-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: inventario
-- ------------------------------------------------------
-- Server version	5.5.21

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
-- Current Database: `inventario`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `inventario` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `inventario`;

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
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamentos` (
  `idDepartamento` varchar(2) NOT NULL,
  `idPais` varchar(3) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idDepartamento`,`idPais`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES ('05','090','ANTIOQUIA'),('08','090','ATLANTICO'),('11','090','CUNDINAMARCA'),('13','090','BOLIVAR'),('15','090','BOYACA'),('17','090','CALDAS'),('18','090','CAQUETA'),('19','090','CAUCA'),('20','090','CESAR'),('23','090','CORDOBA'),('27','090','CHOCO'),('41','090','HUILA'),('44','090','GUAJIRA'),('47','090','MAGDALENA'),('50','090','META'),('52','090','NARIÑO'),('54','090','NORTE DE SANTANDER'),('63','090','QUINDIO'),('66','090','RISARALDA'),('68','090','SANTANDER'),('70','090','SUCRE'),('73','090','TOLIMA'),('76','090','VALLE DEL CAUCA'),('81','090','ARAUCA'),('85','090','CASANARE'),('86','090','PUTUMAYO'),('88','090','SAN ANDRES'),('91','090','AMAZONAS'),('94','090','GUAINIA'),('95','090','GUAVIARE'),('97','090','VAUPES'),('99','090','VICHADA');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
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
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipios` (
  `idMunicipio` varchar(3) NOT NULL,
  `idPais` varchar(3) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idDepartamento` varchar(2) NOT NULL,
  PRIMARY KEY (`idMunicipio`,`idPais`,`idDepartamento`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipios`
--

LOCK TABLES `municipios` WRITE;
/*!40000 ALTER TABLE `municipios` DISABLE KEYS */;
INSERT INTO `municipios` VALUES ('001','090','MEDELLIN','05'),('001','090','BARRANQUILLA','08'),('001','090','BOGOTA','11'),('001','090','CARTAGENA','13'),('001','090','TUNJA','15'),('001','090','MANIZALES','17'),('001','090','FLORENCIA','18'),('001','090','POPAYAN','19'),('001','090','VALLEDUPAR','20'),('001','090','MONTERIA','23'),('001','090','AGUA DE DIOS','25'),('001','090','QUIBDO','27'),('001','090','NEIVA','41'),('001','090','RIOHACHA','44'),('001','090','SANTA MARTA','47'),('001','090','VILLAVICENCIO','50'),('001','090','PASTO','52'),('001','090','CUCUTA','54'),('001','090','ARMENIA','63'),('001','090','PEREIRA','66'),('001','090','BUCARAMANGA','68'),('001','090','SINCELEJO','70'),('001','090','IBAGUE','73'),('001','090','CALI','76'),('001','090','ARAUCA','81'),('001','090','YOPAL','85'),('001','090','MOCOA','86'),('001','090','SAN ANDRES','88'),('001','090','LETICIA','91'),('001','090','PUERTO INIRIDA','94'),('001','090','SAN JOSE DEL GUAVIARE','95'),('001','090','MITU','97'),('001','090','PUERTO CARREÑO','99'),('002','090','ABEJORRAL','05'),('003','090','ABREGO','54'),('004','090','ABRIAQUI','05'),('006','090','ACHI','13'),('006','090','ACANDI','27'),('006','090','ACEVEDO','41'),('006','090','ACACIAS','50'),('010','090','AGUAZUL','85'),('011','090','AGUACHICA','20'),('013','090','AGUADAS','17'),('013','090','AGUSTIN CODAZZI','20'),('013','090','AGRADO','41'),('013','090','AGUADA','68'),('015','090','CHAMEZA','85'),('015','090','CALAMAR','95'),('016','090','AIPE','41'),('019','090','ALBAN','25'),('019','090','ALBAN','52'),('020','090','ALGECIRAS','41'),('020','090','ALBANIA','68'),('020','090','ALCALA','76'),('021','090','ALEJANDRIA','05'),('022','090','ALMEIDA','15'),('022','090','ALMAGUER','19'),('022','090','ALDAÑA','52'),('024','090','ALPUJARRA','73'),('025','090','ALTO BAUDO (PIE DE PATO)','27'),('025','090','EL RETORNO','95'),('026','090','ALTAMIRA','41'),('026','090','ALVARADO','73'),('029','090','ALBANIA','18'),('030','090','AMAGA','05'),('030','090','ALTOS DEL ROSARIO','13'),('030','090','ALGARROBO','47'),('030','090','AMBALEMA','73'),('031','090','AMALFI','05'),('032','090','ASTREA','20'),('034','090','ANDES','05'),('035','090','ANAPOIMA','25'),('035','090','ALBANIA','44'),('036','090','ANGELOPOLIS','05'),('036','090','ANCUYA','52'),('036','090','ANDALUCIA','76'),('038','090','ANGOSTURA','05'),('040','090','ANORI','05'),('040','090','ANOLAIMA','25'),('041','090','ANSERMANUEVO','76'),('042','090','ANTIOQUIA','05'),('042','090','ARENAL','13'),('042','090','ANSERMA','17'),('043','090','ANZOATEGUI','73'),('044','090','ANZA','05'),('045','090','APARTADO','05'),('045','090','BECERRIL','20'),('045','090','APIA','66'),('047','090','AQUITANIA','15'),('050','090','ARANZAZU','17'),('050','090','ARGELIA','19'),('050','090','ATRATO','27'),('051','090','ARBOLETES','05'),('051','090','ARCABUCO','15'),('051','090','ARBOLEDA','52'),('051','090','ARBOLEDAS','54'),('051','090','ARATOCA','68'),('052','090','ARJONA','13'),('053','090','ARBELAEZ','25'),('053','090','ARACATACA','47'),('054','090','ARGELIA','76'),('055','090','ARGELIA','05'),('055','090','ARMERO (GUAYABAL)','73'),('058','090','ARIGUANI','47'),('059','090','ARMENIA','05'),('060','090','BOSCONIA','20'),('062','090','ARROYOHONDO','13'),('065','090','ARAUQUITA','81'),('067','090','ATACO','73'),('068','090','AYAPEL','23'),('073','090','BAGADO','27'),('074','090','BARRANCO DE LOBA','13'),('075','090','BALBOA','19'),('075','090','BAHIA SOLANO (MUTIS)','27'),('075','090','BALBOA','66'),('077','090','BAJO BAUDO (PIZARRO)','27'),('077','090','BARBOSA','68'),('078','090','BARANOA','08'),('078','090','BARAYA','41'),('078','090','BARRANCAS','44'),('079','090','BARBOSA','05'),('079','090','BUENAVISTA','23'),('079','090','BARBACOAS','52'),('079','090','BARICHARA','68'),('081','090','BARRANCABERMEJA','68'),('083','090','BELEN','52'),('086','090','BELMIRA','05'),('086','090','BELTRAN','25'),('087','090','BELEN','15'),('088','090','BELLO','05'),('088','090','BELALCAZAR','17'),('088','090','BELEN DE UMBRIA','66'),('090','090','BERBEO','15'),('090','090','CANALETE','23'),('090','090','DIBULLA','44'),('091','090','BETANIA','05'),('092','090','BETEITIVA','15'),('092','090','BETULIA','68'),('093','090','BETULIA','05'),('094','090','BELEN ANDAQUIES','18'),('095','090','BITUIMA','25'),('097','090','BOAVITA','15'),('098','090','DISTRACCION','44'),('099','090','BOJACA','25'),('099','090','BOJAYA (BELLAVISTA)','27'),('099','090','BOCHALEMA','54'),('100','090','BOLIVAR','19'),('100','090','BOLIVAR','76'),('101','090','CIUDAD BOLIVAR','05'),('101','090','BOLIVAR','68'),('104','090','BOYACA','15'),('106','090','BRICEÑO','15'),('107','090','BRICEÑO','05'),('109','090','BUENAVISTA','15'),('109','090','BUCARASICA','54'),('109','090','BUENAVENTURA','76'),('110','090','BUENOS AIRES','19'),('110','090','EL MOLINO','44'),('110','090','BARRANCA DE UPIA','50'),('110','090','BUESACO','52'),('110','090','BUENAVISTA','70'),('111','090','BUENAVISTA','63'),('111','090','BUGA','76'),('113','090','BURITICA','05'),('113','090','BUGALAGRANDE','76'),('114','090','BUSBANZA','15'),('120','090','CACERES','05'),('120','090','CABRERA','25'),('121','090','CABRERA','68'),('122','090','CAICEDONIA','76'),('123','090','CACHIPAY','25'),('124','090','CABUYARO','50'),('124','090','CAIMITO','70'),('124','090','CAJAMARCA','73'),('125','090','CAICEDO','05'),('125','090','CACOTA','54'),('125','090','HATO COROZAL','85'),('126','090','CAJICA','25'),('126','090','CALIMA','76'),('128','090','CACHIRA','54'),('129','090','CALDAS','05'),('130','090','CAJIBIO','19'),('130','090','CALARCA','63'),('130','090','CANDELARIA','76'),('131','090','CALDAS','15'),('132','090','CAMPOALEGRE','41'),('132','090','CALIFORNIA','68'),('134','090','CAMPAMENTO','05'),('135','090','CAMPOHERMOSO','15'),('135','090','CANTON DE SAN PABLO','27'),('136','090','LA SALINA','85'),('137','090','CAMPO DE LA CRUZ','08'),('137','090','CALDONO','19'),('138','090','CAÑASGORDAS','05'),('139','090','MANI','85'),('140','090','CALAMAR','13'),('141','090','CANDELARIA','08'),('142','090','CARACOLI','05'),('142','090','CALOTO','19'),('145','090','CARAMANTA','05'),('147','090','CAREPA','05'),('147','090','CAPITANEJO','68'),('147','090','CARTAGO','76'),('148','090','CARMEN DE VIBORAL','05'),('148','090','CAPARRAPI','25'),('148','090','CARMEN APICALA','73'),('150','090','CAROLINA','05'),('150','090','CARTAGENA DEL CHAIRA','18'),('150','090','CARMEN DEL DARIEN','27'),('150','090','CASTILLA LA NUEVA','50'),('151','090','CAQUEZA','25'),('152','090','CARCASI','68'),('152','090','CASABIANCA','73'),('154','090','CAUCASIA','05'),('154','090','CARMEN DE CARUPA','25'),('160','090','CANTAGALLO','13'),('160','090','CERTEGUI','27'),('160','090','CEPITA','68'),('161','090','CERRO SAN ANTONIO','47'),('161','090','CARURU','97'),('162','090','CERINZA','15'),('162','090','CERETE','23'),('162','090','CERRITO','68'),('162','090','MONTERREY','85'),('167','090','CHARALA','68'),('168','090','CHIMA','23'),('168','090','CHAGUANI','25'),('168','090','CHAPARRAL','73'),('169','090','CHARTA','68'),('170','090','CHIVOLO','47'),('170','090','DOS QUEBRADAS','66'),('172','090','CHIGORODO','05'),('172','090','CHINAVITA','15'),('172','090','CHINACOTA','54'),('174','090','CHINCHINA','17'),('174','090','CHITAGA','54'),('175','090','CHIMICHAGUA','20'),('175','090','CHIA','25'),('176','090','CHIQUINQUIRA','15'),('176','090','CHIMA','68'),('178','090','CHIRIGUANA','20'),('178','090','CHIPAQUE','25'),('179','090','CHIPATA','68'),('180','090','CHISCAS','15'),('181','090','CHOACHI','25'),('182','090','CHINU','23'),('183','090','CHITA','15'),('183','090','CHOCONTA','25'),('185','090','CHITARAQUE','15'),('187','090','CHIVATA','15'),('188','090','CICUCO','13'),('189','090','CIENEGA','15'),('189','090','CIENAGA DE ORO','23'),('189','090','CIENAGA','47'),('190','090','CISNEROS','05'),('190','090','CIRCASIA','63'),('190','090','CIMITARRA','68'),('197','090','COCORNA','05'),('200','090','COGUA','25'),('200','090','COELLO','73'),('200','090','MIRAFLORES','95'),('203','090','COLON(GENOVA)','52'),('204','090','COMBITA','15'),('204','090','COLOSO','70'),('205','090','CURILLO','18'),('205','090','CONDOTO','27'),('205','090','CONCORDIA','47'),('206','090','CONCEPCION','05'),('206','090','COLOMBIA','41'),('206','090','CONVENCION','54'),('207','090','CONSACA','52'),('207','090','CONCEPCION','68'),('209','090','CONCORDIA','05'),('209','090','CONFINES','68'),('210','090','CONTADERO','52'),('211','090','CONTRATACION','68'),('212','090','COPACABANA','05'),('212','090','CORDOBA','13'),('212','090','COPER','15'),('212','090','CORINTO','19'),('212','090','CORDOBA','63'),('214','090','COTA','25'),('215','090','CORRALES','15'),('215','090','CORDOBA','52'),('215','090','COROZAL','70'),('217','090','COROMORO','68'),('217','090','COYAIMA','73'),('218','090','COVARACHIA','15'),('219','090','COLON','86'),('220','090','CRAVO NORTE','81'),('221','090','COVEÑAS','70'),('222','090','CLEMENCIA','13'),('223','090','CUBARA','15'),('223','090','CUBARRAL','50'),('223','090','CUCUTILLA','54'),('224','090','CUCAITA','15'),('224','090','CUCUNUBA','25'),('224','090','CUASPUD','52'),('225','090','NUNCHIA','85'),('226','090','CUITIVA','15'),('226','090','CUMARAL','50'),('226','090','CUNDAY','73'),('227','090','CUMBAL','52'),('228','090','CURUMANI','20'),('229','090','CURITI','68'),('230','090','CHALAN','70'),('230','090','OROCUE','85'),('232','090','CHIQUIZA','15'),('233','090','CUMBITARA','52'),('233','090','EL ROBLE','70'),('233','090','DAGUA','76'),('234','090','DABEIBA','05'),('235','090','EL CARMEN','68'),('235','090','GALERAS','70'),('236','090','CHIVOR','15'),('236','090','DOLORES','73'),('237','090','DON MATIAS','05'),('238','090','DUITAMA','15'),('238','090','EL COPEY','20'),('239','090','DURANIA','54'),('240','090','EBEJICO','05'),('240','090','CHACHAGUI','52'),('243','090','EL AGUILA','76'),('244','090','EL CARMEN DE BOLIVAR','13'),('244','090','EL COCUY','15'),('244','090','ELIAS','41'),('245','090','EL COLEGIO','25'),('245','090','EL CARMEN','27'),('245','090','EL BANCO','47'),('245','090','EL CALVARIO','50'),('245','090','EL CARMEN','54'),('245','090','EL GUACAMAYO','68'),('246','090','EL CAIRO','76'),('247','090','EL DONCELLO','18'),('248','090','EL GUAMO','13'),('248','090','EL ESPINO','15'),('248','090','EL CERRITO','76'),('250','090','EL BAGRE','05'),('250','090','EL PASO','20'),('250','090','LITORAL DEL SAN JUAN','27'),('250','090','EL CHARCO','52'),('250','090','EL TARRA','54'),('250','090','EL PEÑON','68'),('250','090','EL DOVIO','76'),('250','090','PAZ DE ARIPORO','85'),('251','090','EL CASTILLO','50'),('254','090','EL PEÑOL','52'),('255','090','EL PLAYON','68'),('256','090','EL PAUJIL','18'),('256','090','EL TAMBO','19'),('256','090','EL ROSARIO','52'),('258','090','EL PEÑON','25'),('258','090','EL PIÑON','47'),('258','090','EL TABLON','52'),('260','090','EL ROSAL','25'),('260','090','EL TAMBO','52'),('261','090','EL ZULIA','54'),('263','090','PORE','85'),('264','090','ENTRERRIOS','05'),('264','090','ENCINO','68'),('265','090','GUARANDA','70'),('266','090','ENVIGADO','05'),('266','090','ENCISO','68'),('268','090','EL PEÑON','13'),('268','090','EL RETEN','47'),('268','090','ESPINAL','73'),('269','090','FACATATIVA','25'),('270','090','EL DORADO','50'),('270','090','FALAN','73'),('271','090','FLORIAN','68'),('272','090','FIRAVITOBA','15'),('272','090','FILADELFIA','17'),('272','090','FILANDIA','63'),('275','090','FLANDES','73'),('275','090','FLORIDA','76'),('276','090','FLORESTA','15'),('276','090','FLORIDABLANCA','68'),('279','090','FOMEQUE','25'),('279','090','FONSECA','44'),('279','090','RECETOR','85'),('281','090','FOSCA','25'),('282','090','FREDONIA','05'),('283','090','FRESNO','73'),('284','090','FRONTINO','05'),('286','090','FUNZA','25'),('287','090','FUENTE DE ORO','50'),('287','090','FUNES','52'),('288','090','FUQUENE','25'),('288','090','FUNDACION','47'),('290','090','FLORENCIA','19'),('290','090','FUSAGASUGA','25'),('293','090','GACHANTIVA','15'),('293','090','GACHALA','25'),('295','090','GAMARRA','20'),('295','090','GACHANCIPA','25'),('296','090','GALAPA','08'),('296','090','GAMEZA','15'),('296','090','GALAN','68'),('297','090','GACHETA','25'),('298','090','GARZON','41'),('298','090','GAMBITA','68'),('299','090','GARAGOA','15'),('299','090','GAMA','25'),('300','090','HATILLO DE LOBA','13'),('300','090','COTORRA','23'),('300','090','FORTUL','81'),('300','090','SABANALARGA','85'),('302','090','GENOVA','63'),('306','090','GIRALDO','05'),('306','090','GIGANTE','41'),('306','090','GINEBRA','76'),('307','090','GIRARDOT','25'),('307','090','GIRON','68'),('308','090','GIRARDOTA','05'),('310','090','GOMEZ PLATA','05'),('310','090','GONZALEZ','20'),('312','090','GRANADA','25'),('313','090','GRANADA','05'),('313','090','GRANADA','50'),('313','090','GRAMALOTE','54'),('315','090','GUADALUPE','05'),('315','090','SACAMA','85'),('317','090','GUACAMAYAS','15'),('317','090','GUACHETA','25'),('317','090','GUACHUCAL','52'),('318','090','GUARNE','05'),('318','090','GUAPI','19'),('318','090','GUAMAL','47'),('318','090','GUAMAL','50'),('318','090','GUATICA','66'),('318','090','GUACA','68'),('318','090','GUACARI','76'),('319','090','GUADALUPE','41'),('319','090','GUAMO','73'),('320','090','GUADUAS','25'),('320','090','GUAITARILLA','52'),('320','090','GUADALUPE','68'),('320','090','ORITO','86'),('321','090','GUATAPE','05'),('322','090','GUATEQUE','15'),('322','090','GUASCA','25'),('322','090','GUAPOTA','68'),('323','090','GUALMATAN','52'),('324','090','GUATAQUI','25'),('324','090','GUAVATA','68'),('325','090','GUAYATA','15'),('325','090','MAPIRIPAN','50'),('325','090','SAN LUIS DE PALENQUE','85'),('326','090','GUATAVITA','25'),('327','090','GUEPSA','68'),('328','090','GUAYABAL DE SIQUIMA','25'),('330','090','MESETAS','50'),('332','090','GUICAN','15'),('335','090','GUAYABETAL','25'),('339','090','GUTIERREZ','25'),('344','090','HACARI','54'),('344','090','HATO','68'),('347','090','HELICONIA','05'),('347','090','HERRAN','54'),('347','090','HERVEO','73'),('349','090','HOBO','41'),('349','090','HONDA','73'),('350','090','LA APARTADA','23'),('350','090','LA MACARENA','50'),('352','090','ILES','52'),('352','090','ICONONZO','73'),('353','090','HISPANIA','05'),('354','090','IMUES','52'),('355','090','INZA','19'),('356','090','IPIALES','52'),('357','090','IQUIRA','41'),('359','090','ISNOS','41'),('360','090','ITAGUI','05'),('361','090','ITUANGO','05'),('361','090','ISTMINA','27'),('362','090','IZA','15'),('364','090','JARDIN','05'),('364','090','JAMBALO','19'),('364','090','JAMUNDI','76'),('367','090','JENESANO','15'),('368','090','JERICO','05'),('368','090','JERICO','15'),('368','090','JERUSALEN','25'),('368','090','JESUS MARIA','68'),('370','090','LA URIBE','50'),('370','090','JORDAN','68'),('372','090','JUAN DE ACOSTA','08'),('372','090','JUNIN','25'),('372','090','JURADO','27'),('376','090','LA CEJA','05'),('377','090','LABRANZAGRANDE','15'),('377','090','LA CALERA','25'),('377','090','LABATECA','54'),('377','090','LA BELLEZA','68'),('377','090','LA CUMBRE','76'),('378','090','LA ARGENTINA','41'),('378','090','HATONUEVO','44'),('378','090','LA CRUZ','52'),('380','090','LA ESTRELLA','05'),('380','090','LA CAPILLA','15'),('380','090','LA DORADA','17'),('381','090','LA FLORIDA','52'),('383','090','LA GLORIA','20'),('383','090','LA CELIA','66'),('385','090','LA LLANADA','52'),('385','090','LA ESPERANZA','54'),('385','090','LANDAZURI','68'),('386','090','LA MESA','25'),('388','090','LA MERCED','17'),('390','090','LA PINTADA','05'),('390','090','LA TOLA','52'),('392','090','LA SIERRA','19'),('394','090','LA PALMA','25'),('396','090','LA PLATA','41'),('397','090','LA VEGA','19'),('397','090','LA PAZ','68'),('398','090','LA PEÑA','25'),('398','090','LA PLAYA','54'),('399','090','LA UNION','52'),('400','090','LA UNION','05'),('400','090','LA JAGUA IBIRICO','20'),('400','090','LEJANIAS','50'),('400','090','LA VIRGINIA','66'),('400','090','LA UNION','70'),('400','090','LA UNION','76'),('400','090','TAMARA','85'),('401','090','LA VICTORIA','15'),('401','090','LA TEBAIDA','63'),('402','090','LA VEGA','25'),('403','090','LA UVITA','15'),('403','090','LA VICTORIA','76'),('405','090','LEIVA','52'),('405','090','LOS PATIOS','54'),('406','090','LEBRIJA','68'),('407','090','LEIVA','15'),('407','090','LENGUAZAQUE','25'),('408','090','LERIDA','73'),('410','090','LA MONTAÑITA','18'),('410','090','TAURAMENA','85'),('411','090','LIBORINA','05'),('411','090','LINARES','52'),('411','090','LIBANO','73'),('413','090','LLORO','27'),('417','090','LORICA','23'),('418','090','LOPEZ','19'),('418','090','LOS ANDES','52'),('418','090','LOURDES','54'),('418','090','LOS SANTOS','68'),('418','090','LOS PALMITOS','70'),('419','090','LOS CORDOBAS','23'),('420','090','LA JAGUA DEL PILAR','44'),('421','090','LURUACO','08'),('425','090','MACEO','05'),('425','090','MACANAL','15'),('425','090','MEDIO ATRATO','27'),('425','090','MACARAVITA','68'),('426','090','MACHETA','25'),('427','090','MAGUI','52'),('429','090','MAJAGUAL','70'),('430','090','MAGANGUE','13'),('430','090','MADRID','25'),('430','090','MEDIO BAUDO (BOCA DE PEPE)','27'),('430','090','MAICAO','44'),('430','090','TRINIDAD','85'),('432','090','MALAGA','68'),('433','090','MALAMBO','08'),('433','090','MAHATES','13'),('433','090','MANZANARES','17'),('435','090','MALLAMA','52'),('436','090','MANATI','08'),('436','090','MANTA','25'),('438','090','MEDINA','25'),('440','090','MARINILLA','05'),('440','090','MARGARITA','13'),('440','090','MARSELLA','66'),('440','090','VILLANUEVA','85'),('442','090','MARIA LA BAJA','13'),('442','090','MARIPI','15'),('442','090','MARMATO','17'),('443','090','MANAURE BALCON DEL CESAR','20'),('443','090','MARIQUITA','73'),('444','090','MARQUETALIA','17'),('444','090','MATANZA','68'),('446','090','MARULANDA','17'),('449','090','MELGAR','73'),('450','090','MERCADERES','19'),('450','090','MEDIO SAN JUAN','27'),('450','090','PUERTO CONCORDIA','50'),('455','090','MIRAFLORES','15'),('455','090','MIRANDA','19'),('456','090','MISTRATO','66'),('458','090','MONTECRISTO','13'),('460','090','MILAN','18'),('460','090','NUEVA GRANADA','47'),('461','090','MURILLO','73'),('464','090','MONGUA','15'),('464','090','MOMIL','23'),('464','090','MOGOTES','68'),('466','090','MONGUI','15'),('466','090','MONTELIBANO','23'),('467','090','MONTEBELLO','05'),('468','090','MOMPOS','13'),('468','090','MOLAGAVITA','68'),('469','090','MONIQUIRA','15'),('470','090','MONTENEGRO','63'),('473','090','MORALES','13'),('473','090','MORALES','19'),('473','090','MOSQUERA','25'),('473','090','MOSQUERA','52'),('473','090','MORROA','70'),('475','090','MURINDO','05'),('476','090','MOTAVITA','15'),('479','090','MORELIA','18'),('480','090','MUTATA','05'),('480','090','MUZO','15'),('480','090','NARIÑO','52'),('480','090','MUTISCUA','54'),('483','090','NARIÑO','05'),('483','090','NARIÑO','25'),('483','090','NATAGA','41'),('483','090','NATAGAIMA','73'),('486','090','NEIRA','17'),('486','090','NEMOCON','25'),('488','090','NILO','25'),('489','090','NIMAIMA','25'),('490','090','NECOCLI','05'),('490','090','OLAYA HERRERA','52'),('491','090','NOBSA','15'),('491','090','NOCAIMA','25'),('491','090','NOVITA','27'),('494','090','NUEVO COLON','15'),('495','090','NECHI','05'),('495','090','NORCASIA','17'),('495','090','NUQUI','27'),('497','090','OBANDO','76'),('498','090','OCAÑA','54'),('498','090','OCAMONTE','68'),('500','090','OICATA','15'),('500','090','MOÑITOS','23'),('500','090','OIBA','68'),('501','090','OLAYA','05'),('502','090','ONZAGA','68'),('503','090','OPORAPA','41'),('504','090','ORTEGA','73'),('506','090','VENECIA (OSPINA PEREZ)','25'),('506','090','OSPINA','52'),('507','090','OTANCHE','15'),('508','090','OVEJAS','70'),('511','090','PACHAVITA','15'),('513','090','PACORA','17'),('513','090','PADILLA','19'),('513','090','PACHO','25'),('514','090','PAEZ','15'),('516','090','PAIPA','15'),('517','090','PAEZ','19'),('517','090','PAILITAS','20'),('518','090','PAJARITO','15'),('518','090','PAIME','25'),('518','090','PAICOL','41'),('518','090','PAMPLONA','54'),('520','090','PALMAR DE VARELA','08'),('520','090','PIZARRO','52'),('520','090','PAMPLONITA','54'),('520','090','PALOCABILDO','73'),('520','090','PALMIRA','76'),('522','090','PANQUEBA','15'),('522','090','PALMAR','68'),('523','090','PALMITO','70'),('524','090','PALESTINA','17'),('524','090','PANDI','25'),('524','090','PALERMO','41'),('524','090','PALMAS DEL SOCORRO','68'),('524','090','LA PRIMAVERA','99'),('530','090','PARATEBUENO','25'),('530','090','PALESTINA','41'),('531','090','PAUNA','15'),('532','090','PATIA(EL BORDO)','19'),('533','090','PAYA','15'),('533','090','PIAMONTE','19'),('533','090','PARAMO','68'),('535','090','PASCA','25'),('537','090','PAZ DEL RIO','15'),('540','090','POLICARPA','52'),('540','090','PUERTO NARIÑO','91'),('541','090','PEÑOL','05'),('541','090','PENSILVANIA','17'),('541','090','PEDRAZA','47'),('542','090','PESCA','15'),('543','090','PEQUE','05'),('545','090','PIJIÑO DEL CARMEN','47'),('547','090','PIEDECUESTA','68'),('547','090','PIEDRAS','73'),('548','090','PIENDAMO','19'),('548','090','PITAL','41'),('548','090','PIJAO','63'),('549','090','PIOJO','08'),('549','090','PINILLOS','13'),('549','090','PINCHOTE','68'),('550','090','PISBA','15'),('550','090','PELAYA','20'),('551','090','PITALITO','41'),('551','090','PIVIJAY','47'),('553','090','PUERTO SANTANDER','54'),('555','090','PLANETA RICA','23'),('555','090','PLATO','47'),('555','090','PLANADAS','73'),('558','090','POLO NUEVO','08'),('560','090','PONEDERA','08'),('560','090','MANAURE','44'),('560','090','POTOSI','52'),('563','090','PRADO','73'),('563','090','PRADERA','76'),('564','090','PROVIDENCIA','88'),('565','090','PROVIDENCIA','52'),('568','090','PUERTO GAITAN','50'),('568','090','PUERTO ASIS','86'),('569','090','PUERTO CAYCEDO','86'),('570','090','PUEBLO BELLO','20'),('570','090','PUEBLO NUEVO','23'),('570','090','PUEBLOVIEJO','47'),('571','090','PUERTO GUZMAN','86'),('572','090','PUERTO BOYACA','15'),('572','090','PUERTO SALGAR','25'),('572','090','PUEBLO RICO','66'),('572','090','PUENTE NACIONAL','68'),('573','090','PUERTO COLOMBIA','08'),('573','090','PUERTO TEJADA','19'),('573','090','PUERTO LOPEZ','50'),('573','090','PUERRES','52'),('573','090','PUERTO PARRA','68'),('573','090','PUERTO LEGUIZAMO','86'),('574','090','PUERTO ESCONDIDO','23'),('575','090','PUERTO WILCHES','68'),('576','090','PUEBLORRICO','05'),('577','090','PUERTO LLERAS','50'),('579','090','PUERTO BERRIO','05'),('580','090','REGIDOR','13'),('580','090','QUIPAMA','15'),('580','090','PUERTO LIBERTADOR','23'),('580','090','PULI','25'),('580','090','RIO IRO','27'),('585','090','PUERTO NARE','05'),('585','090','PURACE','19'),('585','090','PUPIALES','52'),('585','090','PURIFICACION','73'),('586','090','PURISIMA','23'),('590','090','PUERTO RICO','50'),('591','090','PUERTO TRIUNFO','05'),('591','090','PUERTO RONDON','81'),('592','090','PUERTO RICO','18'),('592','090','QUEBRADANEGRA','25'),('594','090','QUETAME','25'),('594','090','QUIMBAYA','63'),('594','090','QUINCHIA','66'),('596','090','QUIPILE','25'),('599','090','RAMIRIQUI','15'),('599','090','RAFAEL REYES','25'),('599','090','RAGONVALIA','54'),('600','090','RIO VIEJO','13'),('600','090','RAQUIRA','15'),('600','090','RIO QUITO','27'),('604','090','REMEDIOS','05'),('605','090','REMOLINO','47'),('606','090','REPELON','08'),('606','090','RESTREPO','50'),('606','090','RESTREPO','76'),('607','090','RETIRO','05'),('610','090','SAN JOSE DE FRAGUA','18'),('612','090','RICAURTE','25'),('612','090','RICAURTE','52'),('614','090','RIOSUCIO','17'),('614','090','RIO DE ORO','20'),('615','090','RIONEGRO','05'),('615','090','RIOSUCIO','27'),('615','090','RIVERA','41'),('615','090','RIONEGRO','68'),('616','090','RISARALDA','17'),('616','090','RIOBLANCO','73'),('616','090','RIOFRIO','76'),('620','090','SAN CRISTOBAL','13'),('621','090','RONDON','15'),('621','090','ROBLES (LA PAZ)','20'),('621','090','ROBERTO PAYAN','52'),('622','090','ROSAS','19'),('622','090','RONCESVALLES','73'),('622','090','ROLDANILLO','76'),('624','090','ROVIRA','73'),('624','090','SANTA ROSALIA','99'),('628','090','SABANALARGA','05'),('631','090','SABANETA','05'),('632','090','SABOYA','15'),('634','090','SABANAGRANDE','08'),('638','090','SABANALARGA','08'),('638','090','SACHICA','15'),('642','090','SALGAR','05'),('645','090','SAN  ANTONIO DEL  TEQUENDAMA','25'),('646','090','SAMACA','15'),('647','090','SAN ANDRES','05'),('647','090','SAN ESTANISLAO','13'),('649','090','SAN CARLOS','05'),('649','090','SAN BERNARDO','25'),('650','090','SAN FERNANDO','13'),('650','090','SAN JUAN DEL CESAR','44'),('652','090','SAN FRANCISCO','05'),('653','090','SALAMINA','17'),('653','090','SAN CAYETANO','25'),('654','090','SAN JACINTO','13'),('655','090','SAN JACINTO DEL CAUCA','13'),('655','090','SABANA DE TORRES','68'),('656','090','SAN JERONIMO','05'),('657','090','SAN JUAN NEPOMUCENO','13'),('658','090','SAN JOSE DE LA MONTAÑA','05'),('658','090','SAN FRANCISCO','25'),('659','090','SAN JUAN DE URABA','05'),('660','090','SAN LUIS','05'),('660','090','SAN EDUARDO','15'),('660','090','SAHAGUN','23'),('660','090','SAN JOSE DEL PALMAR','27'),('660','090','SALADOBLANCO','41'),('660','090','SABANAS DE SAN ANGEL','47'),('660','090','SALAZAR','54'),('662','090','SAMANA','17'),('662','090','SAN JUAN DE RIOSECO','25'),('664','090','SAN PEDRO','05'),('664','090','SAN JOSE DE PARE','15'),('665','090','SAN PEDRO DE URABA','05'),('665','090','SAN JOSE','17'),('666','090','TARAIRA','97'),('667','090','SAN RAFAEL','05'),('667','090','SAN MARTIN DE LOBA','13'),('667','090','SAN LUIS DE GACENO','15'),('668','090','SAN AGUSTIN','41'),('669','090','SAN ANDRES','68'),('670','090','SAN ROQUE','05'),('670','090','SAN PABLO','13'),('670','090','SAN ANDRES SOTAVENTO','23'),('670','090','SAN CALIXTO','54'),('670','090','SAMPUES','70'),('670','090','SAN PEDRO','76'),('671','090','SALDAÑA','73'),('672','090','SAN ANTERO','23'),('673','090','SANTA CATALINA','13'),('673','090','SAN MATEO','15'),('673','090','SAN CAYETANO','54'),('673','090','SAN BENITO','68'),('674','090','SAN VICENTE','05'),('675','090','SANTA LUCIA','08'),('675','090','SAN BERNARDO VIENTO','23'),('675','090','SALAMINA','47'),('675','090','SAN ANTONIO','73'),('676','090','SAN MIGUEL DE SEMA','15'),('676','090','SANTA MARIA','41'),('678','090','SAN CARLOS','23'),('678','090','SAMANIEGO','52'),('678','090','SAN BENITO ABAD','70'),('678','090','SAN LUIS','73'),('679','090','SANTA BARBARA','05'),('679','090','SAN GIL','68'),('680','090','SAN CARLOS GUAROA','50'),('680','090','SANTIAGO','54'),('681','090','SAN PABLO DE BORBUR','15'),('682','090','SANTA ROSA DE CABAL','66'),('682','090','SAN JOAQUIN','68'),('683','090','SANTA ROSA','13'),('683','090','SAN  JUAN DE ARAMA','50'),('683','090','SANDONA','52'),('684','090','SAN JOSE DE MIRANDA','68'),('685','090','SANTO TOMAS','08'),('685','090','SAN BERNARDO','52'),('686','090','SANTA ROSA DE OSOS','05'),('686','090','SANTANA','15'),('686','090','SAN PELAYO','23'),('686','090','SAN JUANITO','50'),('686','090','SAN MIGUEL','68'),('686','090','SANTA ISABEL','73'),('687','090','SAN LORENZO','52'),('687','090','SANTUARIO','66'),('688','090','SANTA ROSA DEL SUR','13'),('689','090','SAN MARTIN','50'),('689','090','SAN VICENTE DE CHUCURI','68'),('690','090','SANTO DOMINGO','05'),('690','090','SANTA MARIA','15'),('690','090','SALENTO','63'),('692','090','SAN SEBASTIAN DE BUENAVISTA','47'),('693','090','SANTA ROSA DE VITERBO','15'),('693','090','SAN SEBASTIAN','19'),('693','090','SAN PABLO','52'),('694','090','SAN PEDRO DE CARTAGO','52'),('696','090','SANTA SOFIA','15'),('696','090','SANTA BARBARA','52'),('697','090','SANTUARIO','05'),('698','090','SANTANDER DE QUILICHAO','19'),('699','090','SANTACRUZ','52'),('701','090','STA ROSA','19'),('702','090','SAN JUAN DE BETULIA','70'),('703','090','SAN ZENON','47'),('705','090','SANTA BARBARA','68'),('707','090','SANTA ANA','47'),('708','090','SAN MARCOS','70'),('710','090','SAN ALBERTO','20'),('711','090','VISTA HERMOSA','50'),('713','090','SAN ONOFRE','70'),('717','090','SAN PEDRO','70'),('718','090','SASAIMA','25'),('720','090','SATIVANORTE','15'),('720','090','SANTA BARBARA DE PINTO','47'),('720','090','SAPUYES','52'),('720','090','SARDINATA','54'),('720','090','SANTA HELENA','68'),('723','090','SATIVASUR','15'),('736','090','SEGOVIA','05'),('736','090','SESQUILE','25'),('736','090','SEVILLA','76'),('736','090','SARAVENA','81'),('740','090','SIACHOQUE','15'),('740','090','SIBATE','25'),('742','090','SINCE','70'),('743','090','SILVIA','19'),('743','090','SILVANIA','25'),('743','090','SILOS','54'),('744','090','SIMITI','13'),('745','090','SIMIJACA','25'),('745','090','SIPI','27'),('745','090','SITIONUEVO','47'),('745','090','SIMACOTA','68'),('749','090','SIBUNDOY','86'),('750','090','SAN DIEGO','20'),('753','090','SOATA','15'),('753','090','SAN  VICENTE DEL CAGUAN','18'),('754','090','SOACHA','25'),('755','090','SOCOTA','15'),('755','090','SOCORRO','68'),('755','090','SAN FRANCISCO','86'),('756','090','SONSON','05'),('756','090','SOLANO','18'),('757','090','SOCHA','15'),('757','090','SAN MIGUEL','86'),('758','090','SOLEDAD','08'),('758','090','SOPO','25'),('759','090','SOGAMOSO','15'),('760','090','SOPLAVIENTO','13'),('760','090','SOTARA','19'),('760','090','SANTIAGO','86'),('761','090','SOPETRAN','05'),('761','090','SOMONDOCO','15'),('762','090','SORA','15'),('763','090','SOTAQUIRA','15'),('764','090','SORACA','15'),('769','090','SUBACHOQUE','25'),('770','090','SUAN','08'),('770','090','SAN MARTIN','20'),('770','090','SUAZA','41'),('770','090','SUAITA','68'),('770','090','SUAREZ','73'),('771','090','SUCRE','70'),('772','090','SUESCA','25'),('773','090','SUCRE','68'),('773','090','CUMARIBO','99'),('774','090','SUSACON','15'),('776','090','SUTAMARCHAN','15'),('777','090','SUPIA','17'),('777','090','SUPATA','25'),('778','090','SUTATENZA','15'),('779','090','SUSA','25'),('780','090','TALAIGUA NUEVO','13'),('780','090','SUAREZ','19'),('780','090','SURATA','68'),('781','090','SUTATAUSA','25'),('785','090','SOLITA','18'),('785','090','SUCRE','19'),('785','090','TABIO','25'),('786','090','TAMINANGO','52'),('787','090','TAMALAMEQUE','20'),('787','090','TADO','27'),('788','090','TANGUA','52'),('789','090','TAMESIS','05'),('790','090','TARAZA','05'),('790','090','TASCO','15'),('791','090','TARQUI','41'),('792','090','TARSO','05'),('793','090','TAUSA','25'),('794','090','TAME','81'),('797','090','TENA','25'),('797','090','TESALIA','41'),('798','090','TENZA','15'),('798','090','TENERIFE','47'),('799','090','TENJO','25'),('799','090','TELLO','41'),('800','090','UNGUIA','27'),('800','090','TEORAMA','54'),('801','090','TERUEL','41'),('804','090','TIBANA','15'),('805','090','TIBACUY','25'),('806','090','TIBASOSA','15'),('807','090','TIMBIO','19'),('807','090','TIERRALTA','23'),('807','090','TIBIRITA','25'),('807','090','TIMANA','41'),('808','090','TINJACA','15'),('809','090','TITIRIBI','05'),('809','090','TIMBIQUI','19'),('810','090','TIQUISIO','13'),('810','090','TIPACOQUE','15'),('810','090','UNION PANAMERICANA','27'),('810','090','TIBU','54'),('814','090','TOCA','15'),('815','090','TOCAIMA','25'),('816','090','TOGUI','15'),('817','090','TOCANCIPA','25'),('819','090','TOLEDO','05'),('820','090','TOPAGA','15'),('820','090','TOLEDO','54'),('820','090','TONA','68'),('820','090','TOLU','70'),('821','090','TORIBIO','19'),('822','090','TOTA','15'),('823','090','TOPAIPI','25'),('823','090','TOLUVIEJO','70'),('823','090','TORO','76'),('824','090','TOTORO','19'),('828','090','TRUJILLO','76'),('832','090','TUBARA','08'),('832','090','TUNUNGUA','15'),('834','090','TULUA','76'),('835','090','TURMEQUE','15'),('835','090','TUMACO','52'),('836','090','TURBACO','13'),('837','090','TURBO','05'),('837','090','TUTA','15'),('838','090','TURBANA','13'),('838','090','TUQUERRES','52'),('839','090','TUTASA','15'),('839','090','UBALA','25'),('841','090','UBAQUE','25'),('842','090','URAMITA','05'),('842','090','UMBITA','15'),('843','090','UBATE','25'),('845','090','VILLA RICA','19'),('845','090','UNE','25'),('845','090','ULLOA','76'),('847','090','URRAO','05'),('847','090','URIBIA','44'),('849','090','USIACURI','08'),('851','090','UTICA','25'),('854','090','VALDIVIA','05'),('854','090','VALLE DE S JUAN','73'),('855','090','VALENCIA','23'),('855','090','URUMITA','44'),('855','090','VALLE SAN JOSE','68'),('856','090','VALPARAISO','05'),('858','090','VEGACHI','05'),('860','090','VALPARAISO','18'),('861','090','VENECIA','05'),('861','090','VENTAQUEMADA','15'),('861','090','VELEZ','68'),('861','090','VENADILLO','73'),('862','090','VERGARA','25'),('863','090','VERSALLES','76'),('865','090','VALLE DEL GUAMUEZ','86'),('867','090','VICTORIA','17'),('867','090','VIANI','25'),('867','090','VETAS','68'),('869','090','VIJES','76'),('870','090','VILLAHERMOSA','73'),('871','090','VILLAGOMEZ','25'),('871','090','VILLACARO','54'),('872','090','VILLAVIEJA','41'),('872','090','VILLANUEVA','68'),('873','090','VIGIA DEL FUERTE','05'),('873','090','VILLANUEVA','13'),('873','090','VILLAMARIA','17'),('873','090','VILLAPINZON','25'),('873','090','VILLARRICA','73'),('874','090','VILLANUEVA','44'),('874','090','VILLA DEL ROSARIO','54'),('875','090','VILLETA','25'),('877','090','VITERBO','17'),('878','090','VIOTA','25'),('879','090','VIRACACHA','15'),('885','090','YALI','05'),('885','090','YACOPI','25'),('885','090','YAGUARA','41'),('885','090','YACUANQUER','52'),('885','090','VILLAGARZON','86'),('887','090','YARUMAL','05'),('890','090','YOLOMBO','05'),('890','090','YOTOCO','76'),('892','090','YUMBO','76'),('893','090','YONDO','05'),('894','090','ZAMBRANO','13'),('895','090','ZARAGOZA','05'),('895','090','ZAPATOCA','68'),('895','090','ZARZAL','76'),('897','090','ZETAQUIRA','15'),('898','090','ZIPACON','25'),('899','090','ZIPAQUIRA','25'),('960','090','ZAPAYAN','47'),('980','090','ZONA BANANERA','47'),('989','090','CHICORAL','73');
/*!40000 ALTER TABLE `municipios` ENABLE KEYS */;
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
INSERT INTO `paises` VALUES ('005','Alemania'),('010','American Samoa'),('015','Andorra'),('020','Antillas Netherlands'),('025','Argentina'),('030','Armenia'),('035','Aruba'),('040','Australia'),('045','Austria'),('050','Bahamas'),('055','Belgica'),('060','Bermudas'),('065','Bolivia'),('070','Brasil'),('075','Bulgaria'),('080','Burundi'),('085','Canada'),('090','Colombia'),('095','Corea'),('100','Costa Rica'),('105','Croatia'),('110','Cuba'),('115','Chile'),('120','China'),('125','Dinamarca'),('130','Ecuador'),('135','El Salvador'),('140','España'),('145','Estados Unidos'),('150','Estonia'),('155','Faroe Islands'),('160','Filipinas'),('165','Finlandia'),('170','Francia'),('175','Gran Bretaña'),('180','Grecia'),('185','Greenland'),('190','Guatemala'),('195','Guayana Francesa'),('200','Honduras'),('205','Hong Kong'),('210','Hungria'),('215','Irlanda'),('220','Isla de Man'),('225','Islas Caimanes'),('230','Islas Virgenes Inglesas'),('235','Islas Virgenes U.S.'),('240','Israel'),('245','Italia'),('250','Jamaica'),('255','Japon'),('260','Jordania'),('265','Libano'),('270','Liberia'),('275','Liechenstein'),('280','Lithuania'),('285','Luxemburgo'),('290','Malaysia'),('295','Mexico'),('300','Netherlands'),('305','Nicaragua'),('310','Norfolk Island'),('315','Norway - Noruega'),('320','Nueva Zelanda'),('325','Pakistan'),('330','Panama'),('335','Paraguay'),('340','Peru'),('345','Polonia'),('350','Portugal'),('355','Puerto Rico'),('360','Republica Checa'),('365','Republica Democratica del congo'),('370','Republica Dominicana'),('375','Rumania'),('380','Rusia'),('385','Rwanda'),('390','Singapur'),('395','Slovakia'),('400','Sri Lanka'),('405','Sur Africa'),('410','Suecia'),('415','Suiza'),('420','Taiwan'),('425','Thailandia'),('430','Tonga'),('435','Trinidad y Tobago'),('440','Turkia'),('445','Turmenistan'),('450','Uganda'),('455','Ukraine'),('460','Uruguay'),('465','Venezuela'),('470','Yugoslavia'),('475','Zaire'),('480','Otros Paises');
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
INSERT INTO `tipodocumento` VALUES (3,'Tarjeta de Extranjería'),(4,'Cédula de Extranjería'),(5,'NIT'),(6,'Cédula de Ciudadanía');
/*!40000 ALTER TABLE `tipodocumento` ENABLE KEYS */;
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
INSERT INTO `usuario` VALUES (7,'glinero','��}�[�S�c@R��R',1,1065571485,6);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-23 19:22:42
