-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: evaltrack
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_role` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK101ph19ckaj2nsdm0bo9jibrr` (`id_role`),
  CONSTRAINT `FK101ph19ckaj2nsdm0bo9jibrr` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrateur`
--

LOCK TABLES `administrateur` WRITE;
/*!40000 ALTER TABLE `administrateur` DISABLE KEYS */;
INSERT INTO `administrateur` VALUES (1,'chellyoumaima70@gmail.com','$2a$11$FlibGwknUU/dA2.3Y7X28ueKbkdaGGFjd/a8squvdPnRlPru5XuJC','oumaima chelly',1),(2,'chellyoumaima15@gmail.com','$2a$11$d/bTwI9UO2LA5LrbjmPTTe.bJOChLYfakbNMKHOXSlApTmQPfxQGO','oumaima chelly',1);
/*!40000 ALTER TABLE `administrateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enseignant` (
  `enseignant_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enseignant_id`),
  UNIQUE KEY `UK6kxdv8s2oqch2dcl5euax55hj` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enseignant`
--

LOCK TABLES `enseignant` WRITE;
/*!40000 ALTER TABLE `enseignant` DISABLE KEYS */;
INSERT INTO `enseignant` VALUES (1,'Ameni@gmail.com','Ameni','$2a$11$pl2u48QsZkVQIBzYFIORIe5uoM.lZkAgwt8zQV2AmR1/H3sp0pm5.');
/*!40000 ALTER TABLE `enseignant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etudiant` (
  `cin` int DEFAULT NULL,
  `niveau` int DEFAULT NULL,
  `section_id` int DEFAULT NULL,
  `id_etudinat` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_role` bigint DEFAULT NULL,
  PRIMARY KEY (`id_etudinat`),
  KEY `FKk1uuio6g2w8d2x12liuwrb16h` (`section_id`),
  KEY `FKclhkn475c8bpt2i62qmk5rvut` (`id_role`),
  CONSTRAINT `FKclhkn475c8bpt2i62qmk5rvut` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `FKk1uuio6g2w8d2x12liuwrb16h` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etudiant`
--

LOCK TABLES `etudiant` WRITE;
/*!40000 ALTER TABLE `etudiant` DISABLE KEYS */;
INSERT INTO `etudiant` VALUES (14412728,4,2,135,'amenizakraoui@gmail.com','$2a$11$vidp97DoJHwA5ebP6.pPV.G/aDC9aEXDRHgkW9VE72pbzXRdGUn9m','Zakraoui Ameni',2),(14514585,4,2,136,'eyaziri2@gmail.com','$2a$11$Cmejk6pkUa9RKtAoK7xroeJ2/VY0bjZNBgfVCBfto9IooWHDNl5kq','Ziri Eya',2),(14415634,4,2,137,'chellyoumaima7@gmail.com','$2a$11$mX0AIZ4QBWAx5rVFMg.kI.Fhuh.aaaLyV4pa3/m4YoQ5oPX0qaLne','Chelly Oumaima',2);
/*!40000 ALTER TABLE `etudiant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examen`
--

DROP TABLE IF EXISTS `examen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examen` (
  `id_exam` int NOT NULL AUTO_INCREMENT,
  `matiere_id` int DEFAULT NULL,
  `notes` double DEFAULT NULL,
  `id_etudinat` bigint DEFAULT NULL,
  `lien_copie` varchar(255) DEFAULT NULL,
  `type_exam` varchar(255) DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_exam`),
  KEY `FKhjo7ahv8td4akfiocni17rcyj` (`id_etudinat`),
  KEY `FK3ut04wrso372dc99q4bdt3vfx` (`matiere_id`),
  CONSTRAINT `FK3ut04wrso372dc99q4bdt3vfx` FOREIGN KEY (`matiere_id`) REFERENCES `matiére` (`matiere_id`),
  CONSTRAINT `FKhjo7ahv8td4akfiocni17rcyj` FOREIGN KEY (`id_etudinat`) REFERENCES `etudiant` (`id_etudinat`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examen`
--

LOCK TABLES `examen` WRITE;
/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiére`
--

DROP TABLE IF EXISTS `matiére`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matiére` (
  `matiere_id` int NOT NULL AUTO_INCREMENT,
  `coefficient` float DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `moyenne` float DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `ponderation` varchar(30) DEFAULT NULL,
  `enseignant_id` int DEFAULT NULL,
  `id_module` int NOT NULL,
  PRIMARY KEY (`matiere_id`),
  KEY `FKepfgakksoowpx0hls8gxlj13n` (`enseignant_id`),
  KEY `FK74n3ihe1slw0dkyyalanfre5b` (`id_module`),
  CONSTRAINT `FKepfgakksoowpx0hls8gxlj13n` FOREIGN KEY (`enseignant_id`) REFERENCES `enseignant` (`enseignant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiére`
--

LOCK TABLES `matiére` WRITE;
/*!40000 ALTER TABLE `matiére` DISABLE KEYS */;
INSERT INTO `matiére` VALUES (10,3,'Volume horaire semestriel=45',10,'Mathématiques de l’Ingénieur','0.5*DS+0.5*Examen',NULL,8),(11,2,'Volume horaire semestriel=45',10,'Analyse Numérique 1','0.5*DS+0.5*Examen',NULL,8),(12,3,'Volume horaire semestriel=45',10,' Algorithmique','0.5*DS+0.5*Examen',NULL,9),(13,4,'Volume horaire semestriel=22.5',10,'Programmation','0.5*DS+0.5*Examen',NULL,9),(14,3,'Volume horaire semestriel=45',10,'Logique Formelle','0.5*DS+0.5*Examen',NULL,10),(15,3,'Volume horaire semestriel=45',10,'Génie Logiciel','0.5*DS+0.5*Examen',NULL,10),(16,2,'Volume horaire semestriel=22.5',10,' Technologies de l’information et de la communication','0.5*DS+0.5*Examen',NULL,10),(17,3,'Volume horaire semestriel=45',10,'Circuits Numériques et Eléments d’Architecture','0.5*DS+0.5*Examen',NULL,11),(18,2,'Volume horaire semestriel=45',10,'Semi-conducteurs et Electronique Analogique','0.5*DS+0.5*Examen',NULL,11),(19,2,'Volume horaire semestriel=22.5',10,' Economie de l’Entreprise','0.5*DS+0.5*Examen',NULL,12),(20,1.5,'Volume horaire semestriel=22.5',10,' Basic English','0.5*DS+0.5*Examen',NULL,12),(21,1.5,'Volume horaire semestriel=22.5',10,'Culture et Communication 1','0.5*DS+0.5*Examen',NULL,12),(22,3,'Volume horaire semestriel=45',10,'Probabilités et Processus Stochastique','0.5*DS+0.5*Examen',NULL,13),(23,2,'Volume horaire semestriel=45',10,' Analyse Numérique 2','0.5*DS+0.5*Examen',NULL,13),(24,2.5,'Volume horaire semestriel=45',10,'Structures de Données','0.5*DS+0.5*Examen',NULL,14),(25,4,'Volume horaire semestriel=45',10,' Programmation Orientée Objet','0.5*DS+0.5*Examen',NULL,14),(26,2.5,'Volume horaire semestriel=45',10,' Architecture des systèmes à Microprocesseur','0.5*DS+0.5*Examen',NULL,15),(27,2.5,'Volume horaire semestriel=45',10,'Fondement des réseaux','0.5*DS+0.5*Examen',NULL,15),(28,2.5,'Volume horaire semestriel=22.5',10,'Technologies Web','0.5*DS+0.5*Examen',NULL,15),(29,2.5,'Volume horaire semestriel=45',10,'Base de Données','0.5*DS+0.5*Examen',NULL,16),(30,3.5,'Volume horaire semestriel=45',10,'Analyse et conception des Systèmes d\'Information','0.5*DS+0.5*Examen',NULL,16),(31,2,'Volume horaire semestriel=22.',10,'Théorie des Organisations','0.5*DS+0.5*Examen',NULL,17),(32,1.5,'Volume horaire semestriel=22.5',10,'Professional English','0.5*DS+0.5*Examen',NULL,17),(33,1.5,'Volume horaire semestriel=22.5',10,'Culture et Communication 2','0.5*DS+0.5*Examen',NULL,17),(34,3,'Volume horaire semestriel=45',10,' Système d\'Exploitation','0.5*DS+0.5*Examen',NULL,18),(35,2,'Volume horaire semestriel=45',10,'Atelier de Systèmes d\'Exploitation','0.5*DS+0.5*Examen',NULL,18),(36,3.5,'Volume horaire semestriel=45',10,'Algorithmique Avancée','0.5*DS+0.5*Examen',NULL,19),(37,3.5,'Volume horaire semestriel=22.5',10,'Programmation Java','0.5*DS+0.5*Examen',NULL,19),(38,3.5,'Volume horaire semestriel=45',10,'Technologies Web avancées','0.5*DS+0.5*Examen',NULL,20),(39,3,'Volume horaire semestriel=45',10,' Réseaux d\'entreprises','0.5*DS+0.5*Examen',NULL,20),(40,3,'Volume horaire semestriel=45',10,'Recherche Opérationnelle et Optimisation','0.5*DS+0.5*Examen',NULL,21),(41,3.5,'Volume horaire semestriel=45',10,'Systèmes de Gestion des Bases des Données','0.5*DS+0.5*Examen',NULL,21),(42,2,'Volume horaire semestriel=22.5',10,'Comptabilité d’Entreprise','0.5*DS+0.5*Examen',NULL,22),(43,1.5,'Volume horaire semestriel=22.5',10,'Technical English','0.5*DS+0.5*Examen',NULL,22),(44,1.5,'Volume horaire semestriel=22.5',10,'Techniques de Recherche d’Emploi','0.5*DS+0.5*Examen',NULL,22),(45,3,'Volume horaire semestriel=45',10,'Théorie des Langages et Compilation','0.5*DS+0.5*Examen',NULL,23),(46,3,'Volume horaire semestriel=45',10,'Intelligence Artificielle','0.5*DS+0.5*Examen',NULL,23),(47,2,'Volume horaire semestriel=22.5',10,'Sécurité informatique','0.5*DS+0.5*Examen',NULL,24),(48,3.5,'Volume horaire semestriel=45',10,'Programmation et administration Système et Réseaux','0.5*DS+0.5*Examen',NULL,24),(49,2,'Volume horaire semestriel=22.5',10,'Réseaux mobiles','0.5*DS+0.5*Examen',NULL,25),(50,3,'Volume horaire semestriel=22.5',10,'Plateformes de Développement','0.5*DS+0.5*Examen',NULL,25),(51,2,'Volume horaire semestriel=22.5',10,'Routage des Réseaux','0.5*DS+0.5*Examen',NULL,25),(52,3,'Volume horaire semestriel=45',10,'Systèmes Embarqués','0.5*DS+0.5*Examen',NULL,26),(53,3,'Volume horaire semestriel=45',10,'Analyse des Données','0.5*DS+0.5*Examen',NULL,26),(54,2.5,'Volume horaire semestriel=22.5',10,'Management des Projets','0.5*DS+0.5*Examen',NULL,27),(55,1.5,'Volume horaire semestriel=22.5',10,'Business English','0.5*DS+0.5*Examen',NULL,27),(56,1.5,'Volume horaire semestriel=22.5',10,'Communication en Entreprise','0.5*DS+0.5*Examen',NULL,27),(57,3,'Volume horaire semestriel=45',10,'Service Oriented Computing (SOC)','0.5*DS+0.5*Examen',NULL,28),(58,2,'Volume horaire semestriel=22.5',10,' Ingénierie Avancée des Systèmes d’Information','0.5*DS+0.5*Examen',NULL,28),(59,3,'Volume horaire semestriel=22.5',10,'Cyber security','0.5*DS+0.5*Examen',NULL,29),(60,2,'Volume horaire semestriel=22.5',10,'TMM','0.5*DS+0.5*Examen',NULL,29),(61,3,'Volume horaire semestriel=45',10,'Fouilles des données et IA avancée','0.5*DS+0.5*Examen',NULL,30),(62,2,'Volume horaire semestriel=22.5',10,'Data Warehouse Systems & Business','0.5*DS+0.5*Examen',NULL,30),(63,2,'Volume horaire semestriel=45',10,'Spécification et vérification formelle','0.5*DS+0.5*Examen',NULL,31),(64,2,'Volume horaire semestriel=22.5',10,' Interfaces Homme-Machine','0.5*DS+0.5*Examen',NULL,31),(65,2,'Volume horaire semestriel=22.5',10,'Créativité et Entreprenariat','0.5*DS+0.5*Examen',NULL,32),(66,2,'Volume horaire semestriel=22.5',10,'Anglais','0.5*DS+0.5*Examen',NULL,32),(67,1,'Volume horaire semestriel=22.5',10,'Droit des TIC','0.5*DS+0.5*Examen',NULL,32),(68,1,'Volume horaire semestriel=22.5',10,'Computer Vision','0.5*DS+0.5*Examen',NULL,33),(69,1,'Volume horaire semestriel=22.5',10,'Data Storage','0.5*DS+0.5*Examen',NULL,33),(70,1,'Volume horaire semestriel=22.5',10,'Bases de données réparties','0.5*DS+0.5*Examen',NULL,33),(71,1,'Volume horaire semestriel=22.5',10,'ERP','0.5*DS+0.5*Examen',NULL,33),(72,1,'Volume horaire semestriel=22.5',10,'Plateformes de développement avancées','0.5*DS+0.5*Examen',NULL,33),(73,1,'Volume horaire semestriel=22.5',10,'GIS & Spatial Databases','0.5*DS+0.5*Examen',NULL,33),(74,1,'Volume horaire semestriel=22.5',10,'Cloud computing','0.5*DS+0.5*Examen',NULL,33),(75,1,'Volume horaire semestriel=22.5',10,'Big Data','0.5*DS+0.5*Examen',NULL,33),(76,1,'Volume horaire semestriel=22.5',10,'Qualité et Validation logiciels','0.5*DS+0.5*Examen',NULL,33),(77,1,'Volume horaire semestriel=22.5',10,' Développement mobile','0.5*DS+0.5*Examen',NULL,33),(78,1,'Volume horaire semestriel=22.5',10,'IOT','0.5*DS+0.5*Examen',NULL,34),(79,1,'Volume horaire semestriel=22.5',10,' Réseaux de capteurs sans fil','0.5*DS+0.5*Examen',NULL,34),(80,1,'Volume horaire semestriel=22.5',10,'Dimensionnement des réseaux','0.5*DS+0.5*Examen',NULL,34),(81,1,'Volume horaire semestriel=22.5',10,'Préparation à la certification réseaux','0.5*DS+0.5*Examen',NULL,34),(82,1,'Volume horaire semestriel=22.5',10,'Block Chain','0.5*DS+0.5*Examen',NULL,34),(83,1,'Volume horaire semestriel=22.5',10,'Réseaux de la 5G','0.5*DS+0.5*Examen',NULL,34),(84,1,'Volume horaire semestriel=22.5',10,'Techniques de virtualisation','0.5*DS+0.5*Examen',NULL,34),(85,1,'Volume horaire semestriel=22.5',10,'QoS','0.5*DS+0.5*Examen',NULL,34),(86,1,'Volume horaire semestriel=22.5',10,' Réseaux optiques','0.5*DS+0.5*Examen',NULL,34),(87,1,'Volume horaire semestriel=22.5',10,'Validation et Test SoC','0.5*DS+0.5*Examen',NULL,35),(88,1,'Volume horaire semestriel=22.5',10,'Plateformes Embarquées','0.5*DS+0.5*Examen',NULL,35),(89,1,'Volume horaire semestriel=22.5',10,'Systèmes d\'Exploitation Embarqués et Temps','0.5*DS+0.5*Examen',NULL,35),(90,1,'Volume horaire semestriel=22.5',10,'Architecture et Programmation parallèle','0.5*DS+0.5*Examen',NULL,35),(91,1,'Volume horaire semestriel=22.5',10,'Robotique','0.5*DS+0.5*Examen',NULL,35),(92,1,'Volume horaire semestriel=22.5',10,'Plateformes quantiques et QPL','0.5*DS+0.5*Examen',NULL,35),(93,1,'Volume horaire semestriel=22.5',10,'Conception électronique et microélectronique','0.5*DS+0.5*Examen',NULL,35),(94,1,'Volume horaire semestriel=22.5',10,'Conception avancée des systèmes numériques','0.5*DS+0.5*Examen',NULL,35),(95,1,'Volume horaire semestriel=22.5',10,'Fondements de l\'Informatique quantique','0.5*DS+0.5*Examen',NULL,35),(96,1.5,'Volume horaire semestriel=22.5',10,'Mathématiques Pour l’ingénieur','0.5*DS+0.5*Examen',NULL,36),(97,2.5,'Volume horaire semestriel=22.5',10,'Analyse numérique','0.5*DS+0.5*Examen',NULL,36),(98,2.5,'Volume horaire semestriel=22.5',10,' Régulation des systèmes linéaires','0.5*DS+0.5*Examen',NULL,37),(99,2.5,'Volume horaire semestriel=22.5',10,'Circuits électriques','0.5*DS+0.5*Examen',NULL,37),(100,2.5,'Volume horaire semestriel=22.5',10,'Circuits logiques','0.5*DS+0.5*Examen',NULL,37),(101,2.5,'Volume horaire semestriel=22.5',10,'Métrologie électrique','0.5*DS+0.5*Examen',NULL,38),(102,2.5,'Volume horaire semestriel=22.5',10,'Technologie des composants électroniques','0.5*DS+0.5*Examen',NULL,38),(103,4,'Volume horaire semestriel=45',10,'Algorithmique et Programmation','0.5*DS+0.5*Examen',NULL,39),(104,3,'Volume horaire semestriel=45',10,' Systèmes d’exploitation','0.5*DS+0.5*Examen',NULL,39),(105,1.5,'Volume horaire semestriel=22.5',10,'Economie de l’entreprise','0.5*DS+0.5*Examen',NULL,40),(106,1.5,'Volume horaire semestriel=22.5',10,'Culture et communication1','0.5*DS+0.5*Examen',NULL,40),(107,1.5,'Volume horaire semestriel=22.5',10,' Basic English','0.5*DS+0.5*Examen',NULL,40),(108,1,'Volume horaire semestriel=22.5',10,'Atelier de programmation/outil libre de','0.5*DS+0.5*Examen',NULL,41),(109,1,'Volume horaire semestriel=22.5',10,'CAO Circuits','0.5*DS+0.5*Examen',NULL,41),(110,1.5,'Volume horaire semestriel=22.5',10,'Probabilités et Statistiques','0.5*DS+0.5*Examen',NULL,42),(111,2.5,'Volume horaire semestriel=45',10,'Traitement du signal','0.5*DS+0.5*Examen',NULL,42),(112,3,'Volume horaire semestriel=45',10,'Electronique analogique','0.5*DS+0.5*Examen',NULL,43),(113,1.5,'Volume horaire semestriel=22.5',10,' Systèmes microprogrammes','0.5*DS+0.5*Examen',NULL,43),(114,2.5,'Volume horaire semestriel=45',10,' Systèmes échantillonnés','0.5*DS+0.5*Examen',NULL,43),(115,2.5,'Volume horaire semestriel=22.5',10,'Machines électriques 1','0.5*DS+0.5*Examen',NULL,44),(116,2.5,'Volume horaire semestriel=22.5',10,' Electronique de puissance 1','0.5*DS+0.5*Examen',NULL,44),(117,1.5,'Volume horaire semestriel=22.5',10,'Réseaux informatiques et protocoles','0.5*DS+0.5*Examen',NULL,45),(118,3,'Volume horaire semestriel=45',10,'Algorithmique avancée et structure de données','0.5*DS+0.5*Examen',NULL,45),(119,2.5,'Volume horaire semestriel=22.5',10,' Programmation Orienté Objet','0.5*DS+0.5*Examen',NULL,45),(120,1.5,'Volume horaire semestriel=22.5',10,' Culture et communication 2','0.5*DS+0.5*Examen',NULL,46),(121,1.5,'Volume horaire semestriel=22.5',10,'Professional English','0.5*DS+0.5*Examen',NULL,46),(122,2,'Volume horaire semestriel=22.5',10,'Apprentissage par projet: Développement GUI (Python)','0.5*DS+0.5*Examen',NULL,47),(123,2,'Volume horaire semestriel=22.5',10,' Apprentissage par projet: Systèmes microprogrammés &µC & PIC','0.5*DS+0.5*Examen',NULL,47),(124,2,'Volume horaire semestriel=22.5',10,' Analyse et identification des systèmes','0.5*DS+0.5*Examen',NULL,48),(125,2,'Volume horaire semestriel=22.5',10,' Architecture reconfigurable et langage de description matérielle','0.5*DS+0.5*Examen',NULL,48),(126,2,'Volume horaire semestriel=22.5',10,'contrôleur avancés','0.5*DS+0.5*Examen',NULL,48),(127,2,'Volume horaire semestriel=22.5',10,'Systèmes de Gestion de Bases de Données','0.5*DS+0.5*Examen',NULL,49),(128,1.5,'Volume horaire semestriel=22.5',10,'des de mise en oeuvre des logi','0.5*DS+0.5*Examen',NULL,49),(129,2,'Volume horaire semestriel=22.5',10,' TLA et Compilation','0.5*DS+0.5*Examen',NULL,49),(130,3,'Volume horaire semestriel=45',10,'Microélectronique et VLSI','0.5*DS+0.5*Examen',NULL,50),(131,2.5,'Volume horaire semestriel=22.5',10,'DSP','0.5*DS+0.5*Examen',NULL,50),(132,2.5,'Volume horaire semestriel=22.5',10,' Développement embarqué Mobile','0.5*DS+0.5*Examen',NULL,50),(133,2,'Volume horaire semestriel=22.5',10,'Capteurs et chaine d’acquisition','0.5*DS+0.5*Examen',NULL,50),(134,1.5,'Volume horaire semestriel=22.5',10,'Technical Englih','0.5*DS+0.5*Examen',NULL,51),(135,1.5,'Volume horaire semestriel=22.5',10,'Techniques de recherche d’emplois','0.5*DS+0.5*Examen',NULL,51),(136,1.5,'Volume horaire semestriel=22.5',10,'Comptabilité d’entreprise','0.5*DS+0.5*Examen',NULL,51),(137,2,'Volume horaire semestriel=22.5',10,'Apprentissage par projet: CAO Electronique','0.5*DS+0.5*Examen',NULL,52),(138,2,'Volume horaire semestriel=22.5',10,'Apprentissage par projet','0.5*DS+0.5*Examen',NULL,52),(139,2,'Volume horaire semestriel=22.5',10,'OS embarqués','0.5*DS+0.5*Examen',NULL,53),(140,3,'Volume horaire semestriel=45',10,'Electronique Emetteur/Récepteur','0.5*DS+0.5*Examen',NULL,53),(141,2,'Volume horaire semestriel=22.5',10,' Bus de communication et interfaces','0.5*DS+0.5*Examen',NULL,53),(142,2.5,'Volume horaire semestriel=22.5',10,'Analyse des données','0.5*DS+0.5*Examen',NULL,54),(143,2.5,'Volume horaire semestriel=45',10,' Environnements de développement','0.5*DS+0.5*Examen',NULL,54),(144,2,'Volume horaire semestriel=22.5',10,' Intelligence Artificielle','0.5*DS+0.5*Examen',NULL,54),(145,2.5,'Volume horaire semestriel=22.5',10,'Linux embarqué','0.5*DS+0.5*Examen',NULL,55),(146,3,'Volume horaire semestriel=45',10,'SoC et NoC','0.5*DS+0.5*Examen',NULL,55),(147,1.5,'Volume horaire semestriel=22.5',10,'Vérification formelle','0.5*DS+0.5*Examen',NULL,55),(148,2,'Volume horaire semestriel=22.5',10,'Electronique HF','0.5*DS+0.5*Examen',NULL,55),(149,2.5,'Volume horaire semestriel=22.5',10,' Projet de Fin d’Année','0.5*DS+0.5*Examen',NULL,56),(150,1.5,'Volume horaire semestriel=22.5',10,'Business English','0.5*DS+0.5*Examen',NULL,57),(151,1.5,'Volume horaire semestriel=22.5',10,'Expression et communication professionnelle','0.5*DS+0.5*Examen',NULL,57),(152,1.5,'Volume horaire semestriel=22.5',10,'Management de projet','0.5*DS+0.5*Examen',NULL,57),(153,3,'Volume horaire semestriel=45',10,'Prototypage et reconfiguration dynamique','0.5*DS+0.5*Examen',NULL,58),(154,3,'Volume horaire semestriel=45',10,'Internet des objets et Réseaux des capteurs','0.5*DS+0.5*Examen',NULL,58),(155,1.5,'Volume horaire semestriel=22.5',10,'Test et validation des systèmes embarqués','0.5*DS+0.5*Examen',NULL,58),(156,1.5,'Volume horaire semestriel=22.5',10,'Sécurité des systèmes embarqués','0.5*DS+0.5*Examen',NULL,58),(158,1.5,'Volume horaire semestriel=22.5',10,'Systèmes MEMS, MOEMS et Nanotechnologie','0.5*DS+0.5*Examen',NULL,59),(159,3.5,'Volume horaire semestriel=45',10,'Systèmes distribués et programmation parallèle','0.5*DS+0.5*Examen',NULL,60),(160,2.5,'Volume horaire semestriel=22.5',10,'Sécurité informatique','0.5*DS+0.5*Examen',NULL,60),(161,2,'Volume horaire semestriel=22.5',10,'Vision et traitement d’images','0.5*DS+0.5*Examen',NULL,60),(162,2,'Volume horaire semestriel=22.5',10,'Programmation modulaire : LABVIEW','0.5*DS+0.5*Examen',NULL,60),(163,2,'Volume horaire semestriel=22.5',10,'Preparation for the English certification','0.5*DS+0.5*Examen',NULL,61),(164,1,'Volume horaire semestriel=22.5',10,'Droit de travail','0.5*DS+0.5*Examen',NULL,61),(165,2,'Volume horaire semestriel=22.5',10,' Apprentissage par projet 1','0.5*DS+0.5*Examen',NULL,62),(166,2,'Volume horaire semestriel=22.5',10,' Apprentissage par projet 2','0.5*DS+0.5*Examen',NULL,62),(175,2.5,'Volume horaire semestriel=45',10,'Conception électronique multi technologies','0.5*DS+0.5*Examen',NULL,62),(176,3,'Volume horaire semestriel=45',10,'Mathématiques Pour l’Ingénieur','0.5*DS+0.5*Examen',NULL,63),(177,2,'Volume horaire semestriel=22.5',10,'Analyse numérique 1','0.5*DS+0.5*Examen',NULL,63),(178,3.5,'Volume horaire semestriel=45',10,'Régulation des systèmes linéaires','0.5*DS+0.5*Examen',NULL,64),(179,3,'Volume horaire semestriel=45',10,' Circuits de puissance','0.5*DS+0.5*Examen',NULL,64),(180,3.5,'Volume horaire semestriel=45',10,'Circuits logiques','0.5*DS+0.5*Examen',NULL,64),(181,3,'Volume horaire semestriel=45',10,'Matériaux et RDM','0.5*DS+0.5*Examen',NULL,65),(182,3,'Volume horaire semestriel=45',10,' Physique des semi-conducteurs','0.5*DS+0.5*Examen',NULL,65),(183,2.5,'Volume horaire semestriel=22.5',10,' Programmation C','0.5*DS+0.5*Examen',NULL,66),(184,2,'Volume horaire semestriel=22.5',10,'Systèmes d’exploitation LINUX','0.5*DS+0.5*Examen',NULL,66),(185,1.5,'Volume horaire semestriel=22.5',10,' Economie de l’entreprise','0.5*DS+0.5*Examen',NULL,67),(186,1.5,'Volume horaire semestriel=22.5',10,'Culture et communication1','0.5*DS+0.5*Examen',NULL,67),(187,1.5,'Volume horaire semestriel=22.5',10,'Basic English','0.5*DS+0.5*Examen',NULL,67),(188,2.5,'Volume horaire semestriel=45',10,' Probabilités et Statistiques','0.5*DS+0.5*Examen',NULL,68),(189,2.5,'Volume horaire semestriel=45',10,'Analyse numérique 2','0.5*DS+0.5*Examen',NULL,68),(190,2.5,'Volume horaire semestriel=45',10,'Transfert thermique','0.5*DS+0.5*Examen',NULL,69),(191,2.5,'Volume horaire semestriel=45',10,' MMC','0.5*DS+0.5*Examen',NULL,69),(192,2,'Volume horaire semestriel=22.5',10,' Introduction aux systèmes mécatroniques','0.5*DS+0.5*Examen',NULL,69),(193,2,'Volume horaire semestriel=22.5',10,'CFAO : Solid Works','0.5*DS+0.5*Examen',NULL,69),(194,3.5,'Volume horaire semestriel=60',10,'Electronique analogique','0.5*DS+0.5*Examen',NULL,70),(195,2.5,'Volume horaire semestriel=37.5',10,' Instrumentation industrielle','0.5*DS+0.5*Examen',NULL,70),(196,3,'Volume horaire semestriel=60',10,'Microprocesseurs et Microcontrôleurs','0.5*DS+0.5*Examen',NULL,71),(197,3,'Volume horaire semestriel=45',10,'Commande numérique','0.5*DS+0.5*Examen',NULL,71),(198,1.5,'Volume horaire semestriel=22.5',10,'Culture et communication 2','0.5*DS+0.5*Examen',NULL,72),(199,1.5,'Volume horaire semestriel=22.5',10,'Professional English','0.5*DS+0.5*Examen',NULL,72),(200,1,'Volume horaire semestriel=22.5',10,'Gestion des organisations','0.5*DS+0.5*Examen',NULL,72),(201,2,'Volume horaire semestriel=22.5',10,'Mécanique vibratoire','0.5*DS+0.5*Examen',NULL,73),(202,3,'Volume horaire semestriel=45',10,'Construction mécanique','0.5*DS+0.5*Examen',NULL,73),(203,3,'Volume horaire semestriel=45',10,'RO/Optimisation','0.5*DS+0.5*Examen',NULL,74),(204,2.5,'Volume horaire semestriel=60',10,'Modélisation des systèmes mécatroniques :ADAMS','0.5*DS+0.5*Examen',NULL,74),(205,3.5,'Volume horaire semestriel=60',10,'Conversion de l’Energie 1','0.5*DS+0.5*Examen',NULL,75),(206,3.5,'Volume horaire semestriel=37.5',10,'Fonctions électroniques','0.5*DS+0.5*Examen',NULL,75),(207,2.5,'Volume horaire semestriel=37.5',10,'Systèmes à événements discrets 1','0.5*DS+0.5*Examen',NULL,76),(208,2.5,'Volume horaire semestriel=45',10,' Circuits programmables et VHDL','0.5*DS+0.5*Examen',NULL,76),(209,3,'Volume horaire semestriel=45',10,'Traitement du signal analogique','0.5*DS+0.5*Examen',NULL,76),(210,1.5,'Volume horaire semestriel=22.5',10,'Techniques de recherche d’emplois','0.5*DS+0.5*Examen',NULL,77),(211,1.5,'Volume horaire semestriel=22.5',10,'Technical English','0.5*DS+0.5*Examen',NULL,77),(212,1.5,'Volume horaire semestriel=22.5',10,'Compatibilité d’entreprise','0.5*DS+0.5*Examen',NULL,77),(213,1.5,'Volume horaire semestriel=22.5',10,'Dynamique des systèmes mécatronique','0.5*DS+0.5*Examen',NULL,78),(214,2.5,'Volume horaire semestriel=45',10,'Technologie de fabrication','0.5*DS+0.5*Examen',NULL,78),(215,1.5,'Volume horaire semestriel=22.5',10,' Transmission Mécanique','0.5*DS+0.5*Examen',NULL,78),(216,1.5,'Volume horaire semestriel=15',10,'Atelier de Mécanique','0.5*DS+0.5*Examen',NULL,78),(217,3,'Volume horaire semestriel=60',10,'Conversion de l’Energie 2','0.5*DS+0.5*Examen',NULL,79),(218,2,'Volume horaire semestriel=30',10,'CFAO : CATIA','0.5*DS+0.5*Examen',NULL,79),(219,3,'Volume horaire semestriel=60',10,' Traitement du signal numérique','0.5*DS+0.5*Examen',NULL,80),(220,3,'Volume horaire semestriel=75',10,' Systèmes Embarqués et DSP','0.5*DS+0.5*Examen',NULL,80),(221,3,'Volume horaire semestriel=60',10,'Analyse et identification des systèmes','0.5*DS+0.5*Examen',NULL,81),(222,2,'Volume horaire semestriel=37.5',10,'Systèmes à Evènements Discrets 2','0.5*DS+0.5*Examen',NULL,81),(223,2.5,'Volume horaire semestriel=22.5',10,'Projet de Fin d’Année','0.5*DS+0.5*Examen',NULL,82),(224,1.5,'Volume horaire semestriel=22.5',10,'Expression et communication professionnelle','0.5*DS+0.5*Examen',NULL,83),(225,1.5,'Volume horaire semestriel=22.5',10,'Business English','0.5*DS+0.5*Examen',NULL,83),(226,1.5,'Volume horaire semestriel=22.5',10,'Management de projet','0.5*DS+0.5*Examen',NULL,83),(227,3.5,'Volume horaire semestriel=40',10,' Systèmes robotisés','0.5*DS+0.5*Examen',NULL,84),(228,2,'Volume horaire semestriel=22.5',10,' Mécatronique et Démarche d’Intégration','0.5*DS+0.5*Examen',NULL,84),(229,3,'Volume horaire semestriel=22.5',10,' Programmation modulaire : LabView','0.5*DS+0.5*Examen',NULL,85),(230,3,'Volume horaire semestriel=22.5',10,'OS Embarqué','0.5*DS+0.5*Examen',NULL,85),(231,3.5,'Volume horaire semestriel=40',10,' Entrainements électriques à vitesse variable','0.5*DS+0.5*Examen',NULL,86),(232,3,'Volume horaire semestriel=40',10,' Commande des systèmes complexes','0.5*DS+0.5*Examen',NULL,86),(233,1.5,'Volume horaire semestriel=22.5',10,'Antennes','0.5*DS+0.5*Examen',NULL,87),(234,3.5,'Volume horaire semestriel=40',10,'Traitement d’images et reconnaissance de formes','0.5*DS+0.5*Examen',NULL,87),(235,2,'Volume horaire semestriel=22.5',10,'Preparation for the certification','0.5*DS+0.5*Examen',NULL,88),(236,1,'Volume horaire semestriel=22.5',10,'Droit de travail','0.5*DS+0.5*Examen',NULL,88),(237,4,'Volume horaire semestriel=22.5',10,' Transmission analogique de signal','0.5*DS+0.5*Examen',NULL,89),(238,4,'Volume horaire semestriel=22.5',10,'Transmission numérique de signal','0.5*DS+0.5*Examen',NULL,89),(239,4,'Volume horaire semestriel=22.5',10,'Commande robuste','0.5*DS+0.5*Examen',NULL,89),(240,4,'Volume horaire semestriel=22.5',10,' Soft computing','0.5*DS+0.5*Examen',NULL,89),(241,4,'Volume horaire semestriel=22.5',10,'Supervision des systèmes automatisés','0.5*DS+0.5*Examen',NULL,89),(242,4,'Volume horaire semestriel=22.5',10,' Réseaux locaux industriels','0.5*DS+0.5*Examen',NULL,89),(243,4,'Volume horaire semestriel=22.5',10,'Convertisseurs multi-niveaux','0.5*DS+0.5*Examen',NULL,89),(244,4,'Volume horaire semestriel=22.5',10,'Machines spéciales','0.5*DS+0.5*Examen',NULL,89),(245,3,'Volume horaire semestriel=45',10,' Mathématiques pour l\'ingénieur','0.5*DS+0.5*Examen',NULL,90),(246,3,'Volume horaire semestriel=45',10,' Analyse numérique 1','0.5*DS+0.5*Examen',NULL,90),(247,3,'Volume horaire semestriel=45',10,' Algorithmique & programmation','0.5*DS+0.5*Examen',NULL,91),(248,2,'Volume horaire semestriel=45',10,' Atelier de Programmation','0.5*DS+0.5*Examen',NULL,91),(249,3,'Volume horaire semestriel=45',10,'Matériaux et procédés de fabrication','0.5*DS+0.5*Examen',NULL,92),(250,1.5,'Volume horaire semestriel=22.5',10,' Transfert Thermique','0.5*DS+0.5*Examen',NULL,92),(251,1.5,'Volume horaire semestriel=22.5',10,'Physique des composants électroniques','0.5*DS+0.5*Examen',NULL,93),(252,4,'Volume horaire semestriel=60',10,'Automatique générale','0.5*DS+0.5*Examen',NULL,93),(253,3,'Volume horaire semestriel=45',10,'Introduction à la gestion industrielle','0.5*DS+0.5*Examen',NULL,94),(254,3,'Volume horaire semestriel=45',10,'Economie de l\'entreprise','0.5*DS+0.5*Examen',NULL,94),(255,1.5,'Volume horaire semestriel=22.5',10,' Culture et communication 1','0.5*DS+0.5*Examen',NULL,95),(256,1.5,'Volume horaire semestriel=22.5',10,'Basic English','0.5*DS+0.5*Examen',NULL,95),(257,2,'Volume horaire semestriel=45',10,'Analyse numérique 2','0.5*DS+0.5*Examen',NULL,96),(258,3,'Volume horaire semestriel=45',10,'Probabilités et Statistiques','0.5*DS+0.5*Examen',NULL,96),(259,3,'Volume horaire semestriel=45',10,' Bases de données','0.5*DS+0.5*Examen',NULL,97),(260,2,'Volume horaire semestriel=22.5',10,' Analyse et conception des SI','0.5*DS+0.5*Examen',NULL,97),(261,3,'Volume horaire semestriel=45',10,'RO et Optimisation','0.5*DS+0.5*Examen',NULL,98),(262,2,'Volume horaire semestriel=45',10,'Graphes et Heuristique','0.5*DS+0.5*Examen',NULL,98),(263,3,'Volume horaire semestriel=45',10,' Machines thermiques','0.5*DS+0.5*Examen',NULL,99),(264,2,'Volume horaire semestriel=45',10,' Systèmes électriques','0.5*DS+0.5*Examen',NULL,99),(265,2,'Volume horaire semestriel=22.5',10,'Gestion des organisations','0.5*DS+0.5*Examen',NULL,100),(266,2,'Volume horaire semestriel=22.5',10,'Introduction à la chaine logistique','0.5*DS+0.5*Examen',NULL,100),(267,3,'Volume horaire semestriel=45',10,'Projet bibliographique','0.5*DS+0.5*Examen',NULL,100),(268,1.5,'Volume horaire semestriel=22.5',10,'Culture et communication 2','0.5*DS+0.5*Examen',NULL,101),(269,1.5,'Volume horaire semestriel=22.5',10,'Professional English','0.5*DS+0.5*Examen',NULL,101),(270,3,'Volume horaire semestriel=22.5',10,' Gestion de la production','0.5*DS+0.5*Examen',NULL,102),(271,3,'Volume horaire semestriel=60',10,'Logistique des approvisionnements','0.5*DS+0.5*Examen',NULL,102),(272,2,'Volume horaire semestriel=37.5',10,'Programmation avancée','0.5*DS+0.5*Examen',NULL,103),(273,2,'Volume horaire semestriel=45',10,'Atelier programmation avancée','0.5*DS+0.5*Examen',NULL,103),(274,3,'Volume horaire semestriel=45',10,'Bases de la qualité','0.5*DS+0.5*Examen',NULL,104),(275,3,'Volume horaire semestriel=60',10,'Métrologie et Instrumentation','0.5*DS+0.5*Examen',NULL,104),(276,3,'Volume horaire semestriel=45',10,'Conception et fabrication mécanique','0.5*DS+0.5*Examen',NULL,105),(277,3,'Volume horaire semestriel=60',10,'Automatismes Industriels','0.5*DS+0.5*Examen',NULL,105),(278,2,'Volume horaire semestriel=22.5',10,'Innovation & créativité','0.5*DS+0.5*Examen',NULL,106),(279,3,'Volume horaire semestriel=60',10,'Analyse des données et apprentissage','0.5*DS+0.5*Examen',NULL,106),(280,1.5,'Volume horaire semestriel=22.5',10,'Techniques de recherche d’emploi','0.5*DS+0.5*Examen',NULL,107),(281,1.5,'Volume horaire semestriel=22.5',10,'Technical English','0.5*DS+0.5*Examen',NULL,107),(282,2,'Volume horaire semestriel=22.5',10,'Ordonnancement des SPL','0.5*DS+0.5*Examen',NULL,108),(283,1,'Volume horaire semestriel=22.5',10,'Manufacturing Execution System','0.5*DS+0.5*Examen',NULL,108),(284,2.5,'Volume horaire semestriel=45',10,'Modélisation et simulation des flux','0.5*DS+0.5*Examen',NULL,108),(285,2,'Volume horaire semestriel=22.5',10,'Aide à la décision','0.5*DS+0.5*Examen',NULL,108),(286,1.5,'Volume horaire semestriel=22.5',10,'Business Intelligence','0.5*DS+0.5*Examen',NULL,108),(287,2,'Volume horaire semestriel=22.5',10,' Management de projet','0.5*DS+0.5*Examen',NULL,109),(288,2,'Volume horaire semestriel=22.5',10,' Management stratégique','0.5*DS+0.5*Examen',NULL,109),(289,2,'Volume horaire semestriel=22.5',10,'Comptabilité d\'entreprise','0.5*DS+0.5*Examen',NULL,109),(290,2.5,'Volume horaire semestriel=45',10,'Maintenance industrielle','0.5*DS+0.5*Examen',NULL,110),(291,25,'Volume horaire semestriel=45',10,' Systèmes à évènements discrets','0.5*DS+0.5*Examen',NULL,110),(292,2,'Volume horaire semestriel=45',10,'Maitrise de l\'énergie dans l\'industrie','0.5*DS+0.5*Examen',NULL,110),(293,1.5,'Volume horaire semestriel=22.5',10,' Expression et communication professionnelle','0.5*DS+0.5*Examen',NULL,111),(294,1.5,'Volume horaire semestriel=22.5',10,' Business English','0.5*DS+0.5*Examen',NULL,111),(295,5,'Volume horaire semestriel=22.5',10,'Projet de fin d\'année 2','0.5*DS+0.5*Examen',NULL,112),(296,3.5,'Volume horaire semestriel=45',10,'Conception des chaînes logistiques','0.5*DS+0.5*Examen',NULL,113),(297,3,'Volume horaire semestriel=45',10,' Coordination de la chaîne logistique','0.5*DS+0.5*Examen',NULL,113),(298,2.5,'Volume horaire semestriel=22.5',10,' Gestion de la relation client-fournisseur','0.5*DS+0.5*Examen',NULL,113),(299,3,'Volume horaire semestriel=45',10,' Modes et métiers du transport','0.5*DS+0.5*Examen',NULL,114),(300,3,'Volume horaire semestriel=45',10,' Logistique de distribution','0.5*DS+0.5*Examen',NULL,114),(301,3,'Volume horaire semestriel=22.5',10,' Systèmes d\'information & ERP','0.5*DS+0.5*Examen',NULL,115),(302,3,'Volume horaire semestriel=22.5',10,'Management de la qualité','0.5*DS+0.5*Examen',NULL,115),(303,3,'Volume horaire semestriel=22.5',10,'Création d\'entreprises','0.5*DS+0.5*Examen',NULL,116),(304,3,'Volume horaire semestriel=45',10,'Environnement et développement durable','0.5*DS+0.5*Examen',NULL,116),(305,2,'Volume horaire semestriel=22.5',10,'Preparation for the certification','0.5*DS+0.5*Examen',NULL,117),(306,1,'Volume horaire semestriel=22.5',10,'Droit du travail','0.5*DS+0.5*Examen',NULL,117);
/*!40000 ALTER TABLE `matiére` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `id_module` int NOT NULL AUTO_INCREMENT,
  `moyenne` float NOT NULL,
  `nom_module` varchar(255) DEFAULT NULL,
  `section_id` int DEFAULT NULL,
  `semestre` int NOT NULL,
  `coefModule` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_module`),
  KEY `FKjx7m0047fch3u2pxjbddcq2qm` (`section_id`),
  CONSTRAINT `FKjx7m0047fch3u2pxjbddcq2qm` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (8,10,'UE 1.1',2,1,5.00),(9,10,'UE 1.2',2,1,7.00),(10,10,'UE 1.3',2,1,8.00),(11,10,'UE 1.4',2,1,5.00),(12,10,'UE 1.5',2,1,5.00),(13,10,'UE 1.6',2,2,5.00),(14,10,'UE 1.7',2,2,6.50),(15,10,'UE 1.8',2,2,7.50),(16,10,'UE 1.9',2,2,6.00),(17,10,'UE 1.10',2,2,5.00),(18,10,'UE 2.1',2,3,5.00),(19,10,'UE 2.2',2,3,7.00),(20,10,'UE 2.3',2,3,6.50),(21,10,'UE 2.4',2,3,6.50),(22,10,'UE 2.5',2,3,5.00),(23,10,'UE 2.6',2,4,6.00),(24,10,'UE 2.7',2,4,5.50),(25,10,'UE 2.8',2,4,7.00),(26,10,'UE 2.9',2,4,6.00),(27,10,'UE 2.10',2,4,5.50),(28,10,'UE ISI.1',2,5,5.00),(29,10,'UE ISI.2',2,5,5.00),(30,10,'UE ISI.3',2,5,5.00),(31,10,'UE ISI.4',2,5,4.00),(32,10,'UE ISI.5',2,5,5.00),(33,10,'UE*ISI.Panier SI',2,5,6.00),(34,10,'UE*ISI.Panier Rx',2,5,6.00),(35,10,'UE*ISI.Panier Emb',2,5,6.00),(36,10,'UE 1.1',3,1,4.00),(37,10,'UE 1.2',3,1,7.50),(38,10,'UE 1.3',3,1,5.00),(39,10,'UE 1.4',3,1,7.00),(40,10,'UE 1.5',3,1,4.50),(41,10,'UE 1.6',3,1,2.00),(42,10,'UE 1.7',3,2,4.00),(43,10,'UE 1.8',3,2,7.00),(44,10,'UE 1.9',3,2,5.00),(45,10,'UE 1.10',3,2,7.00),(46,10,'UE 1.11',3,2,3.00),(47,10,'UE 1.12',3,2,4.00),(48,10,'UE 2.1',3,3,6.00),(49,10,'UE 2.2',3,3,5.50),(50,10,'UE 2.3',3,3,10.00),(51,10,'UE 2.4',3,3,4.50),(52,10,'UE 2.5',3,3,4.00),(53,10,'UE 2.6',3,4,7.00),(54,10,'UE 2.7',3,4,7.00),(55,10,'UE 2.8',3,4,9.00),(56,10,'UE 2.9',3,4,2.50),(57,10,'UE 2.10',3,4,4.50),(58,10,'UE 3.1',3,5,9.00),(59,10,'UE 3.2',3,5,4.00),(60,10,'UE 3.3',3,5,10.00),(61,10,'UE 3.4',3,5,3.00),(62,10,'UE 3.5',3,5,4.00),(63,10,'UE 1.1',4,1,5.00),(64,10,'UE 1.2',4,1,10.00),(65,10,'UE 1.3',4,1,6.00),(66,10,'UE 1.4',4,1,4.50),(67,10,'UE 1.5',4,1,4.50),(68,10,'UE 1.6',4,2,5.00),(69,10,'UE 1.7',4,2,9.00),(70,10,'UE 1.8',4,2,6.00),(71,10,'UE 1.9',4,2,6.00),(72,10,'UE 1.10',4,2,4.00),(73,10,'UE 2.1',4,3,5.00),(74,10,'UE 2.2',4,3,5.50),(75,10,'UE 2.3',4,3,7.00),(76,10,'UE 2.4',4,3,8.00),(77,10,'UE 2.5',4,3,4.50),(78,10,'UE 2.6',4,4,7.00),(79,10,'UE 2.7',4,4,5.00),(80,10,'UE 2.8',4,4,6.00),(81,10,'UE 2.9',4,4,5.00),(82,10,'UE 2.10',4,4,2.50),(83,10,'UE 2.11',4,4,4.50),(84,10,'UE 3.1',4,5,5.50),(85,10,'UE 3.2',4,5,6.00),(86,10,'UE 3.3',4,5,6.50),(87,10,'UE 3.4',4,5,5.00),(88,10,'UE 3.5',4,5,3.00),(89,10,'UE 3.6*',4,5,4.00),(90,10,'UE 1.1',5,1,6.00),(91,10,'UE 1.2',5,1,5.00),(92,10,'UE 1.3',5,1,4.50),(93,10,'UE 1.4',5,1,5.50),(94,10,'UE 1.5',5,1,6.00),(95,10,'UE 1.6',5,1,3.00),(96,10,'UE 1.7',5,2,5.00),(97,10,'UE 1.8',5,2,5.00),(98,10,'UE 1.9',5,2,5.00),(99,10,'UE 1.10',5,2,5.00),(100,10,'UE 1.11',5,2,7.00),(101,10,'UE 1.12',5,2,3.00),(102,10,'UE 2.1',5,2,6.00),(103,10,'UE 2.2',5,3,4.00),(104,10,'UE 2.3',5,3,6.00),(105,10,'UE 2.4',5,3,6.00),(106,10,'UE 2.5',5,3,5.00),(107,10,'UE 2.6',5,3,3.00),(108,10,'UE 2.7',5,4,9.00),(109,10,'UE 2.8',5,4,6.00),(110,10,'UE 2.9',5,4,7.00),(111,10,'UE 2.10',5,4,3.00),(112,10,'UE 2.11',5,4,5.00),(113,10,'UE.LOG.1',5,5,9.00),(114,10,'UE.LOG.2',5,5,6.00),(115,10,'UE.LOG.3',5,5,6.00),(116,10,'UE.LOG.4',5,5,6.00),(117,10,'UE.LOG.5',5,5,3.00);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` bigint NOT NULL AUTO_INCREMENT,
  `nom_string` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrateur'),(2,'Etudiant'),(3,'Enseignant');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `section_id` int NOT NULL AUTO_INCREMENT,
  `nom_section` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (2,'Génie informatique'),(3,'Infotronique'),(4,'Mécatroique'),(5,'GSIl');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'evaltrack'
--

--
-- Dumping routines for database 'evaltrack'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-01 14:04:20
