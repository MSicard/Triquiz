-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: triquizdb
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `idAnswer` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idQuestion` int(11) NOT NULL,
  `answerText` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAnswer`)
) ENGINE=InnoDB AUTO_INCREMENT=8991 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,1,'Axioma'),(2,1,'Teorema'),(3,1,'Ley'),(4,1,'Postulado'),(5,9,'Triángulo Escaleno'),(6,9,'Triángulo Isóceles'),(7,9,'Triángulo Rectángulo'),(8,9,'Triángulo Equilátero'),(9,10,'5'),(10,10,'6'),(11,10,'8'),(12,10,'4'),(13,11,'2:00'),(14,11,'1:35'),(15,11,'2:10'),(16,11,'1:50'),(17,12,'4'),(18,12,'11'),(19,12,'22'),(20,12,'8'),(21,15,'6'),(22,15,'4'),(23,15,'5'),(24,15,'2'),(25,2,'Asociativa'),(26,2,'Conmutativa'),(27,2,'Cerradura'),(28,2,'Neutro'),(29,3,'Asociativa'),(30,3,'Conmutativa'),(31,3,'Cerradura'),(32,3,'Neutro'),(33,4,'2x^3/3'),(34,4,'6x'),(35,4,'2x^2/4'),(36,4,'4x'),(37,6,'Onza'),(38,6,'Libras'),(39,6,'Newtons'),(40,6,'Poundal'),(41,13,'120'),(42,13,'100'),(43,13,'90'),(44,13,'80'),(45,5,'Falacia'),(46,5,'Tautología'),(47,5,'Contigencia'),(48,5,'Proposición'),(49,8,'(pi*radio^2)/2'),(50,8,'pi*radio*2'),(51,8,'pi*radio^2'),(52,8,'(pi*radio)/2'),(53,14,'pi*r^2*P^2'),(54,14,'pi*r^2*P'),(55,14,'pi*r*2*P'),(56,14,'pi*r*2*P^2'),(57,7,'12'),(58,7,'0'),(59,7,'9'),(60,7,'3'),(61,16,'1939'),(62,16,'1942'),(63,16,'1914'),(64,16,'1918'),(65,17,'Loba'),(66,17,'Perra'),(67,17,'Zorra'),(68,17,'Coyote'),(69,18,'Margarita'),(70,18,'Juana'),(71,18,'Isabel'),(72,18,'Fernanda'),(73,19,'Hungría'),(74,19,'Alemania'),(75,19,'Austria'),(76,19,'Polonia'),(77,20,'Marconi'),(78,20,'Graham Bell'),(79,20,'Thomas A. Edison'),(80,20,'Alexander Fleming'),(81,21,'Olmecas'),(82,21,'Aztecas'),(83,21,'Huastecas'),(84,21,'Maya'),(85,22,'13'),(86,22,'11'),(87,22,'12'),(88,22,'14'),(91,23,'1512'),(92,23,'1500'),(93,23,'1492'),(94,24,'Horus'),(95,24,'Anubis'),(96,24,'Thot'),(97,24,'Geb'),(98,25,'Miguel Hidalgo'),(99,25,'Vicente Guerrero'),(100,25,'Agustín Inturbide'),(101,25,'Maximiliando de Hansburgo'),(102,26,'Tratado de Versalles'),(103,26,'Catorce Puntos'),(104,26,'Tratado de Paris'),(105,26,'Tratado de Roma'),(106,27,'Manos'),(107,27,'Bastón'),(108,27,'Espadas'),(109,27,'Ninguna de las anteriores'),(110,28,'Frigg'),(111,28,'Thor'),(112,28,'Odín'),(113,28,'Ragnarok'),(114,29,'Eje y Alianza'),(115,29,'Eje y Aliados'),(116,29,'Entente y Alianza'),(117,29,'Entente y Aliados'),(118,30,'Alejandro III de Macedonia'),(119,30,'Alejandro II de Macedonia'),(120,30,'Alejandro de Macedonia'),(121,30,'Alejandro I de Macedonia'),(122,31,'Luces del sur'),(123,31,'Luces del norte'),(124,31,'Luces del oeste'),(125,31,'Luces del este'),(126,32,'Na'),(127,32,'So'),(128,32,'S'),(129,32,'N'),(130,33,'Venus'),(131,33,'Saturno'),(132,33,'Marte'),(133,33,'Júpiter'),(134,34,'120'),(135,34,'234'),(136,34,'118'),(137,34,'110'),(138,61,'Tamerina'),(139,61,'Timina'),(140,61,'Citosina'),(141,61,'Adenina'),(142,35,'Aire'),(143,35,'Células'),(144,35,'Plantas'),(145,35,'Sangre'),(146,36,'Meiosis'),(147,36,'Segregación'),(148,36,'Bipartición'),(149,36,'Mitosis'),(150,37,'Normal'),(151,37,'Rozamiento'),(153,37,'Gravedad'),(154,37,'Tensión'),(155,38,'Creacionismo'),(156,38,'Darwinismo'),(157,38,'Gradualismo'),(158,38,'Ninguna de las anteriores'),(159,39,'Minerales'),(160,39,'Harulos'),(161,39,'Gases Nobles'),(162,39,'Metales'),(163,40,'30,000 km/h'),(164,40,'300,000 km/h'),(165,40,'300,000 m/s'),(166,40,'30,000 m/s'),(167,41,'Bomba atómica'),(168,41,'Penicilana'),(169,41,'Dinamita'),(170,41,'La bombilla'),(171,42,'Bacterias'),(172,42,'Levaduras'),(173,42,'Microbios'),(174,42,'Ninguna de las anteriores'),(175,43,'Combatir enfermedades'),(176,43,'Llevar oxígeno'),(177,43,'Coagular Sangre'),(178,43,'Llevar nutrientes'),(179,44,'Rayos X'),(180,44,'Ultavioleta'),(181,44,'Infrarroja'),(182,44,'Ninguna de las anteriores'),(183,45,'Marconi'),(184,45,'Graham Bell'),(185,45,'Thomas A. Edison'),(186,45,'Alexander Fleming'),(187,46,'Neutra'),(188,46,'Positiva'),(189,46,'Negativa'),(190,46,'Ninguna de las anteriores'),(191,47,'Java'),(192,47,'C++'),(193,47,'C'),(194,47,'Python'),(195,48,'Código Máquina'),(196,48,'Ensamblador'),(197,48,'Ejecutable'),(198,49,'Variable'),(199,49,'Función'),(200,49,'Método'),(201,49,'Constante'),(202,50,'Switch'),(203,50,'If'),(204,50,'While'),(205,50,'For'),(206,51,'Do While'),(207,51,'For'),(208,51,'If'),(209,51,'While'),(210,52,'Int'),(211,52,'Float'),(212,52,'String'),(213,52,'Double'),(214,53,'32'),(215,53,'65'),(216,53,'97'),(217,53,'126'),(218,54,'C%'),(219,54,'C++'),(220,54,'C#'),(221,54,'C'),(222,55,'Hola'),(223,55,'true'),(224,55,'14.5'),(225,55,'H'),(226,56,'c.meth()'),(227,56,'c->meth()'),(228,56,'meth.c'),(229,56,'meth->c'),(230,57,'='),(231,57,'!='),(232,57,'+='),(233,57,'=='),(234,58,'temp*'),(235,58,'temp&'),(236,58,'&temp'),(237,58,'*temp'),(238,62,'!'),(239,62,'='),(240,62,'=='),(241,62,'*'),(242,59,'enum'),(243,59,'method'),(244,59,'struct'),(245,59,'ninguna de las anteriores'),(246,60,'char'),(247,60,'int'),(248,60,'float'),(249,60,'bool'),(250,48,'Compilador'),(8990,23,'1498');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `idCategory` int(11) unsigned NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Programación'),(2,'Ciencia'),(3,'Matemáticas'),(4,'Historia');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combat`
--

DROP TABLE IF EXISTS `combat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combat` (
  `idCombat` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `time` int(8) DEFAULT NULL,
  PRIMARY KEY (`idCombat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combat`
--

LOCK TABLES `combat` WRITE;
/*!40000 ALTER TABLE `combat` DISABLE KEYS */;
/*!40000 ALTER TABLE `combat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `idGame` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idCategory` int(11) DEFAULT NULL,
  `textQuestion` varchar(90) DEFAULT NULL,
  `text1` varchar(60) DEFAULT NULL,
  `text2` varchar(60) DEFAULT NULL,
  `text3` varchar(60) DEFAULT NULL,
  `text4` varchar(60) DEFAULT NULL,
  `correct` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idGame`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `idQuestion` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `textQuestion` varchar(90) DEFAULT NULL COMMENT 'The question',
  `idAnswer` int(11) unsigned NOT NULL,
  `idCategory` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idQuestion`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'No necesita demostración',1,3),(2,'z + w = w + z Es la propiedad:  ',29,3),(3,'(z + w) + v = z + (w + v ) Es la propiedad:',26,3),(4,'¿Cuál es la derivada de 2x^2 + 5?',36,3),(5,'Fórmula lógica sin ningún modelo',45,3),(6,'¿En qué unidades se mide la fuerza en el sistema internacional?',39,3),(7,'(9 / 3) * 3 = ?',59,3),(8,'¿Cuál es el área de un círculo?',51,3),(9,'¿Cómo se llama al triángulo de tres lados iguales?',8,3),(10,'¿Cuál de los siguientes números no es factor de 40?',10,3),(11,'Si son las 1:25, ¿qué hora será en 30 minutos?',13,3),(12,'¿Cuánto es 2 + 2? (Esta pregunta es troll)',19,3),(13,'4 *5 *5 = ?',42,3),(14,'¿Cuál es el volumen de un cilindro?',54,3),(15,'(10 * 2) /4 = ?',23,3),(16,'¿En qué año inició la segunda guerra mundial?',61,4),(17,'¿Qué animal, dice la tradición que amamantó a Rómulo y Remo?',65,4),(18,'¿Quién era la esposa de Fernando el Católico?',71,4),(19,'¿En qué país nació Adolf Hitler?',75,4),(20,'¿Quién inventó el teléfono?',78,4),(21,'¿Qué civilización prehispánica adoró al Dios Kukulkán? ',84,4),(22,'¿Con cuántas colonias inglesas se formaron los Estados Unidos Americanos?',85,4),(23,'¿En qué fecha se descubrió América?',93,4),(24,'¿Qué nombre recibe el hijo de Osiris e Isis en la mitología egipta? ',94,4),(25,'¿Quién fue el primer emperador de México al independizarse?',100,4),(26,'¿Cómo se llamó al tratado después de la primera guerra mundial?',102,4),(27,'Según la Biblia, ¿con qué objeto Moisés abrió los mares?',107,4),(28,'En la mitología nórdica, ¿quién gobierna el asgard?',112,4),(29,'¿Cómo se llaman los dos bandos de la segunda guerra mundial?',115,4),(30,'¿Cuál era el nombre de Alejandro Magno?',118,4),(31,'¿Con qué otro nombre es conocida la aurora boreal?',123,2),(32,'¿Cuál es el símbolo químico del sodio?',126,2),(33,'¿Cuál es el planeta más grande de nuestro sistema solar?',133,2),(34,'¿Cuántos elemento químicos son conocidos?',136,2),(35,'¿Dónde se encuentra el citoplasma?',143,2),(36,'¿Cómo se llama el proceso por el que una célula se divide para formar dos células? ',149,2),(37,'¿Cómo se llama la fuerza que la tierra ejerce sobre los objetos hacia su centro? ',153,2),(38,'¿Teoría que considera que los organismos vienen del mismo ancestro?',156,2),(39,'¿De qué es la columna más a la derecha en la tabla periódica?',161,2),(40,'¿Cuál es la velocidad en la que viaja la luz?',164,2),(41,'¿Qué inventó Aldred Nobel, el que da el nombre al premio?',169,2),(42,'Para el pan y la cervaza se utiliza para fermentar',172,2),(43,'¿Cuál es la principal función de los glóbulos rojos?',176,2),(44,'¿Qué tipo de radiación te produce quemaduras?',180,2),(45,'¿Quién patentó la bombilla?',185,2),(46,'¿Qué carga tiene un protón?',188,2),(47,'No es un lenguaje de programación orientada a objetos',193,1),(48,'Codificación de programas en binario que es el único que puede ser ejecutado',195,1),(49,'Puede cambiar de contenido',198,1),(50,'¿Cuál es la sentencia para la selección de opciones múltiples?',202,1),(51,'Esta no es una sentencia repetitiva',208,1),(52,'Este no es un tipo de dato simple',212,1),(53,'¿Cuál es el código ASCII deciaml de la letra A mayúscula?',215,1),(54,'No es un lenguaje de programación',218,1),(55,'Es un flotante',224,1),(56,'¿Cómo seleccionas un método llamado meth, en java de la clase c?',226,1),(57,'Operador para saber si dos valores son iguales ',233,1),(58,'Hacer una referencia de memoria a la variable temp',235,1),(59,'¿Cómo se declara una estructura en c?',244,1),(60,'Regresa un valor verdadero o falso',249,1),(61,'¿Cuál no es una base nitrogenada?',138,2),(62,'Operador para asignar un valor a una variable',240,1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking`
--

DROP TABLE IF EXISTS `ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ranking` (
  `idRank` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` int(11) unsigned NOT NULL,
  `idCategory` int(11) unsigned NOT NULL,
  `score` int(11) unsigned NOT NULL,
  PRIMARY KEY (`idRank`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking`
--

LOCK TABLES `ranking` WRITE;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
INSERT INTO `ranking` VALUES (1,2,1,0),(2,2,2,0),(3,2,3,0),(4,2,4,0),(5,3,1,0),(6,3,2,0),(7,3,3,0),(8,3,4,0),(9,4,1,0),(10,4,2,0),(11,4,3,0),(12,4,4,0),(13,5,1,0),(14,5,2,0),(15,5,3,0),(16,5,4,0),(17,6,1,0),(18,6,2,0),(19,6,3,0),(20,6,4,0),(21,7,1,0),(22,7,2,0),(23,7,3,0),(24,7,4,0),(25,8,1,0),(26,8,2,0),(27,8,3,0),(28,8,4,0),(29,9,1,0),(30,9,2,0),(31,9,3,0),(32,9,4,0),(33,10,1,0),(34,10,2,0),(35,10,3,0),(36,10,4,0);
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idUser` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'asd','123'),(2,'mar','123'),(3,'mari','123'),(4,'maris','123'),(5,'maritza','123'),(6,'hola','123'),(7,'user1','123'),(8,'carmen','123'),(9,'juana','123'),(10,'pedro','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01  7:32:13
