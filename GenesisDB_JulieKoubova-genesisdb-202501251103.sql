-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: genesisdb
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `genesisusers`
--

DROP TABLE IF EXISTS `genesisusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genesisusers` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Surname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `PersonID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Uuid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NewTable_unique` (`PersonID`),
  UNIQUE KEY `NewTable_unique_1` (`Uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genesisusers`
--

LOCK TABLES `genesisusers` WRITE;
/*!40000 ALTER TABLE `genesisusers` DISABLE KEYS */;
INSERT INTO `genesisusers` VALUES (1,'Michael','Powell','jXa4g3H7oPq2','870c6cff-cec1-11ef-ba43-00bb60a14d6f'),(7,'Roman','Stanek','vB1fX4rH7iNt','a622371f-5ff7-4c2c-83da-36de22ef0f6d'),(9,'Elena','Svetlyakova','rU8nA9eT2bYh','23fe66c7-e122-42bf-ba38-3ab5d3abaa9e'),(10,'Emanuelle','Novakova','eI1oY6tQ9dKj','94bf2c18-03cf-49f9-8286-63ad39e2f0ab'),(11,'Taras','Mammak','kR0aZ7vW2nDl','3082d40d-01b7-4806-9d39-ab73996c41d1'),(13,'Anabela','Capkova','nS7tJ0qR5wGh','45d95a2c-862a-415b-9333-5dc4c31ab687'),(17,'Helen','Smith','aO8kP3mZ6dIw','5d07a003-8a36-4027-a6de-c408f49b6ff1'),(18,'Eva','Buiqick','bG2zC7jR9xVp','d8e44ca0-4dcc-4dfc-a375-ffec0039b4d5'),(21,'Petr','Pavel','qE3lY6uT0vKd','19a7e75f-1e63-444d-8854-f5d918616225'),(23,'Jan','Zdenek','dW9pL2eU1yNc','cdc77b1b-ea41-472d-934b-3798efe661d4'),(25,'Ing','Young','sL4gN9dC3bXz','4840c722-5fae-4174-8516-3a2a47f841d7'),(32,'Adam','Gerstl','yB9fR6tK0wLm','5aff9a9b-fcc9-43fe-a2b1-e9d9f67924e5'),(69,'Constantin','Palermo','iM5sO6zXcW7v','b4c8f256-accd-4aa5-bed2-a0d2f2fda264'),(92,'Ellen','Mitchell','gT4cR7wS0lVx','38a9408e-5931-4309-850d-2ec85246f55e');
/*!40000 ALTER TABLE `genesisusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'genesisdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-25 11:03:38
