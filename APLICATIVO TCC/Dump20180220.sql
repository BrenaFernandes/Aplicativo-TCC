CREATE DATABASE  IF NOT EXISTS `carregamentotransformador` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `carregamentotransformador`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: carregamentotransformador
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `medidor` varchar(20) NOT NULL,
  `contrato` varchar(20) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `rg` int(11) NOT NULL,
  `cpf` int(11) NOT NULL,
  `telefone` int(11) DEFAULT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `demandacontratada` double NOT NULL,
  `classe` varchar(20) NOT NULL,
  `unidadegeradora` char(3) DEFAULT NULL,
  `alimentacao` varchar(20) NOT NULL,
  `tensao` int(11) NOT NULL,
  `poste` varchar(10) NOT NULL,
  `demandacalc` double DEFAULT NULL,
  `transformador` varchar(15) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`medidor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('CCCCC','HIJ','HIJ',89347934,1732145,83947256,'CCCCCCC',1878,8346,'Industrial','Não','Trifásico',380,'CCCCCC3724',NULL,'HIJ',NULL,NULL),('DDDDD','ABC','DEF',9725690,53646597,216497314,'DDDDD',1000,456,'Rural','Sim','Monofásico C',220,'EWURYEUW',NULL,'HIJ',NULL,NULL),('DDDD','KLI','KLI',897087,8708,8708,'DDDDD',808,987,'Industrial','Sim','Monofásico B',220,'ou9',NULL,'HIJ',NULL,NULL),('AAA','AAA','AAA',123,123,1234,'AAA',123,1234,'Comercial','Sim','Monofásico A',220,'123D',NULL,'DEF',NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demanda`
--

DROP TABLE IF EXISTS `demanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demanda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contrato` varchar(20) NOT NULL,
  `mes` int(11) NOT NULL,
  `ano` int(11) NOT NULL,
  `demandamed` double NOT NULL,
  `equipamento` varchar(25) DEFAULT NULL,
  `alimentacao` varchar(20) DEFAULT NULL,
  `demandacalc` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demanda`
--

LOCK TABLES `demanda` WRITE;
/*!40000 ALTER TABLE `demanda` DISABLE KEYS */;
INSERT INTO `demanda` VALUES (1,'ABC',1,2018,250,NULL,NULL,NULL),(2,'ABC',2,2018,300,NULL,NULL,NULL),(3,'HIJ',1,2018,400,NULL,NULL,NULL),(4,'ABC',1,2018,250,NULL,NULL,NULL),(5,'ABC',2,2018,300,NULL,NULL,NULL),(6,'HIJ',1,2018,400,NULL,NULL,NULL),(7,'ABC',1,2018,250,NULL,NULL,NULL),(8,'ABC',2,2018,300,NULL,NULL,NULL),(9,'HIJ',1,2018,400,NULL,NULL,NULL),(10,'ABC',1,2005,300,'HIJ','Monofásico C',1.8016818234782606),(11,'ABC',10,2018,250,NULL,NULL,NULL),(12,'ABC',12,2018,300,NULL,NULL,NULL),(13,'HIJ',9,2018,400,NULL,NULL,NULL),(14,'ABC',10,2018,250,NULL,NULL,NULL),(15,'ABC',12,2018,300,NULL,NULL,NULL),(16,'HIJ',9,2018,400,NULL,NULL,NULL),(17,'ABC',10,2018,250,NULL,NULL,NULL),(18,'ABC',12,2018,300,NULL,NULL,NULL),(19,'HIJ',9,2018,400,NULL,NULL,NULL),(20,'ABC',10,2018,250,NULL,NULL,NULL),(21,'ABC',12,2018,300,NULL,NULL,NULL),(22,'HIJ',9,2018,400,NULL,NULL,NULL),(23,'ABC',10,2018,250,NULL,NULL,NULL),(24,'ABC',12,2018,300,NULL,NULL,NULL),(25,'HIJ',9,2018,400,NULL,NULL,NULL),(26,'ABC',10,2018,250,NULL,NULL,NULL),(27,'ABC',12,2018,300,NULL,NULL,NULL),(28,'HIJ',9,2018,400,NULL,NULL,NULL);
/*!40000 ALTER TABLE `demanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipamento`
--

DROP TABLE IF EXISTS `equipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipamento` (
  `id` varchar(25) NOT NULL,
  `sigla` varchar(10) DEFAULT NULL,
  `modelo` varchar(40) DEFAULT NULL,
  `marca` varchar(40) DEFAULT NULL,
  `alimentador` varchar(20) NOT NULL,
  `subestacao` varchar(30) NOT NULL,
  `potencia` int(11) NOT NULL,
  `tensaoaliment` int(11) NOT NULL,
  `descricao` varchar(1000) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `complemento` varchar(200) DEFAULT NULL,
  `coordenada` varchar(40) DEFAULT NULL,
  `codigolocalizacao` varchar(30) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `cep` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipamento`
--

LOCK TABLES `equipamento` WRITE;
/*!40000 ALTER TABLE `equipamento` DISABLE KEYS */;
INSERT INTO `equipamento` VALUES ('DEF','bnm','bnm','bnm','bnm','bnm',400,220,'bnm bnm bnm','C',123,'ABC','ABC','ABC','ABC','ABC','BA','123'),('HIJ','AAA','AAA','AAA','AAA123','AAA123',100,220,'PRIMEIRO TRAFO DO BANCO DE DADOS','AAA',123,'AAA','AAA','AA123','AAA123','AAA','AA','123456'),('BBB','BBB','BBB','BBB','BBB','BBB',123,110,'SEGUNDO TRAFO','BBB',123,'BBB','BBB','BBB','BBB','BBB','BB','12345');
/*!40000 ALTER TABLE `equipamento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-20  2:56:06
