CREATE DATABASE  IF NOT EXISTS `zdarzeniowe` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `zdarzeniowe`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zdarzeniowe
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `karnety`
--

DROP TABLE IF EXISTS `karnety`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `karnety` (
  `Idkarnetu` int(11) NOT NULL,
  `NrKlienta` int(11) DEFAULT NULL,
  `Nazwakarnetu` text NOT NULL,
  `Od` date NOT NULL,
  `Do` date NOT NULL,
  PRIMARY KEY (`Idkarnetu`),
  KEY `FK_Relationship_4` (`NrKlienta`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`NrKlienta`) REFERENCES `klient` (`NrKlienta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karnety`
--

LOCK TABLES `karnety` WRITE;
/*!40000 ALTER TABLE `karnety` DISABLE KEYS */;
INSERT INTO `karnety` VALUES (2,2,'90 dniowy','2017-12-27','2018-03-26'),(4,3,'Tygodniowy','2017-12-26','2018-01-03'),(5,4,'30 dniowy','2018-01-05','2018-02-04'),(7,7,'Tygodniowy','2017-01-04','2017-01-11'),(8,9,'30 dniowy','2018-01-11','2018-02-10'),(9,12,'90 dniowy','2018-01-18','2018-04-18'),(10,8,'90 dniowy','2018-01-11','2018-04-11'),(11,5,'90 dniowy','2018-01-13','2018-04-13'),(12,6,'30 dniowy','2018-01-12','2018-02-11'),(14,4,'30 dniowy','2018-01-25','2018-02-24'),(15,3,'30 dniowy','2018-01-14','2018-02-13');
/*!40000 ALTER TABLE `karnety` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klient`
--

DROP TABLE IF EXISTS `klient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klient` (
  `NrKlienta` int(11) NOT NULL,
  `IdMiasta` int(11) DEFAULT NULL,
  `Imie` text NOT NULL,
  `Nazwisko` text NOT NULL,
  `Telefon` int(11) NOT NULL,
  PRIMARY KEY (`NrKlienta`),
  KEY `FK_Relationship_9` (`IdMiasta`),
  CONSTRAINT `FK_Relationship_9` FOREIGN KEY (`IdMiasta`) REFERENCES `miasto` (`IdMiasta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klient`
--

LOCK TABLES `klient` WRITE;
/*!40000 ALTER TABLE `klient` DISABLE KEYS */;
INSERT INTO `klient` VALUES (2,1,'Jakub','Nowak',645865345),(3,1,'Kacper','Kowalski',956845345),(4,1,'Kamil','Nowak',856345930),(5,1,'Kamila','Kowalska',454323454),(6,1,'Jacek','Urbanski',458345743),(7,1,'Olaf','Nawrocki',654832297),(8,1,'Kacper','Wielki',745234854),(9,1,'Daniel','Michalski',945673213),(10,1,'Monika','Labacz',543234657),(11,1,'Renata','Urbanska',458349085),(12,1,'Janusz','Trawski',435678543),(13,1,'Jan','Kowal',765902304);
/*!40000 ALTER TABLE `klient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magazyn`
--

DROP TABLE IF EXISTS `magazyn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `magazyn` (
  `IdMagazynu` int(11) NOT NULL,
  `IdSilowni` int(11) DEFAULT NULL,
  `Urzadzenie` text NOT NULL,
  `Ilosc` int(11) NOT NULL,
  PRIMARY KEY (`IdMagazynu`),
  KEY `FK_Relationship_2` (`IdSilowni`),
  CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`IdSilowni`) REFERENCES `silownia` (`IdSilowni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazyn`
--

LOCK TABLES `magazyn` WRITE;
/*!40000 ALTER TABLE `magazyn` DISABLE KEYS */;
INSERT INTO `magazyn` VALUES (1,1,'Orbitrek',5),(2,1,'Wioslasz',3),(3,1,'Atlas',2);
/*!40000 ALTER TABLE `magazyn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miasto`
--

DROP TABLE IF EXISTS `miasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miasto` (
  `IdMiasta` int(11) NOT NULL,
  `Nazwa` text NOT NULL,
  PRIMARY KEY (`IdMiasta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miasto`
--

LOCK TABLES `miasto` WRITE;
/*!40000 ALTER TABLE `miasto` DISABLE KEYS */;
INSERT INTO `miasto` VALUES (1,'Warszawa'),(2,'Krakow');
/*!40000 ALTER TABLE `miasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silownia`
--

DROP TABLE IF EXISTS `silownia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `silownia` (
  `IdSilowni` int(11) NOT NULL,
  `IdMiasta` int(11) DEFAULT NULL,
  `Nazwa` text NOT NULL,
  PRIMARY KEY (`IdSilowni`),
  KEY `FK_Relationship_8` (`IdMiasta`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`IdMiasta`) REFERENCES `miasto` (`IdMiasta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silownia`
--

LOCK TABLES `silownia` WRITE;
/*!40000 ALTER TABLE `silownia` DISABLE KEYS */;
INSERT INTO `silownia` VALUES (1,1,'Fit Gym');
/*!40000 ALTER TABLE `silownia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `szafki`
--

DROP TABLE IF EXISTS `szafki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `szafki` (
  `Zajetosc` tinyint(1) NOT NULL,
  `NrSzafki` int(11) NOT NULL,
  `IdSzatni` int(11) DEFAULT NULL,
  `AktualnyKlient` int(11) DEFAULT NULL,
  PRIMARY KEY (`NrSzafki`),
  KEY `FK_Relationship_7` (`IdSzatni`),
  CONSTRAINT `FK_Relationship_7` FOREIGN KEY (`IdSzatni`) REFERENCES `szatnia` (`IdSzatni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `szafki`
--

LOCK TABLES `szafki` WRITE;
/*!40000 ALTER TABLE `szafki` DISABLE KEYS */;
INSERT INTO `szafki` VALUES (0,1,1,NULL),(0,2,1,NULL),(1,3,1,3),(0,4,1,NULL),(0,5,1,NULL),(0,6,1,NULL),(0,7,1,NULL),(0,8,1,NULL),(0,9,1,NULL),(0,10,1,NULL),(0,11,2,NULL),(0,12,2,NULL),(0,13,2,NULL),(0,14,2,NULL),(0,15,2,NULL),(0,16,2,NULL),(0,17,2,NULL),(0,18,2,NULL);
/*!40000 ALTER TABLE `szafki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `szatnia`
--

DROP TABLE IF EXISTS `szatnia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `szatnia` (
  `IdSzatni` int(11) NOT NULL,
  `IdSilowni` int(11) DEFAULT NULL,
  `Plec` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdSzatni`),
  KEY `FK_Relationship_5` (`IdSilowni`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`IdSilowni`) REFERENCES `silownia` (`IdSilowni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `szatnia`
--

LOCK TABLES `szatnia` WRITE;
/*!40000 ALTER TABLE `szatnia` DISABLE KEYS */;
INSERT INTO `szatnia` VALUES (1,1,1),(2,1,0);
/*!40000 ALTER TABLE `szatnia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uzytkownik`
--

DROP TABLE IF EXISTS `uzytkownik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uzytkownik` (
  `Login` text NOT NULL,
  `Haslo` text NOT NULL,
  `IdUzytkownika` int(11) NOT NULL,
  `IdSilowni` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdUzytkownika`),
  KEY `FK_Relationship_3` (`IdSilowni`),
  CONSTRAINT `FK_Relationship_3` FOREIGN KEY (`IdSilowni`) REFERENCES `silownia` (`IdSilowni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uzytkownik`
--

LOCK TABLES `uzytkownik` WRITE;
/*!40000 ALTER TABLE `uzytkownik` DISABLE KEYS */;
INSERT INTO `uzytkownik` VALUES ('User1','User123',1,1),('User2','User123',2,1);
/*!40000 ALTER TABLE `uzytkownik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wplaty`
--

DROP TABLE IF EXISTS `wplaty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wplaty` (
  `IdWplaty` int(11) NOT NULL,
  `IdSilowni` int(11) DEFAULT NULL,
  `Tytul` text NOT NULL,
  `Kwota` int(11) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`IdWplaty`),
  KEY `FK_Relationship_6` (`IdSilowni`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`IdSilowni`) REFERENCES `silownia` (`IdSilowni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wplaty`
--

LOCK TABLES `wplaty` WRITE;
/*!40000 ALTER TABLE `wplaty` DISABLE KEYS */;
INSERT INTO `wplaty` VALUES (1,1,'Urzadzenia',1000,'2017-12-24'),(2,1,'Napoje',500,'2017-12-26');
/*!40000 ALTER TABLE `wplaty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'zdarzeniowe'
--

--
-- Dumping routines for database 'zdarzeniowe'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-16 19:21:40
