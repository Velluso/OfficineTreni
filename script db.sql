CREATE TABLE `utente` (
  `username` varchar(50) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(35) NOT NULL,
  `email` varchar(70) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` boolean NOT NULL DEFAULT 1,
  `budget` double DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `authorities` (
  `username` varchar(45) NOT NULL,
  `authority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `utente` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `treno` (
  `idTreno` int NOT NULL,
  `stato` varchar(45) NOT NULL,
  `sigla` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`idTreno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `vagone` (
  `idVagone` int NOT NULL,
  `peso` int DEFAULT NULL,
  `prezzo` int DEFAULT NULL,
  `lunghezza` int DEFAULT NULL,
  `tipoVagone` varchar(45) NOT NULL,
  `compagnia` varchar(45) DEFAULT NULL,
  `numPasseggeri` int DEFAULT NULL,
  `pesoTrainante` int DEFAULT NULL,
  `capacitaCarico` int DEFAULT NULL,
  `copertiDisponibili` int DEFAULT NULL,
  `idTreno` int DEFAULT NULL,
  `posizione` int DEFAULT NULL,
  PRIMARY KEY (`idVagone`),
  CONSTRAINT `idTreno` FOREIGN KEY (`idTreno`) REFERENCES `treno` (`idTreno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `ordine` (
  `idOrdine` int NOT NULL,
  `idTreno` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `dataCreazione` date NOT NULL,
  `dataConclusione` date DEFAULT NULL,
  `stato` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idOrdine`),
  CONSTRAINT `idTreno2` FOREIGN KEY (`idTreno`) REFERENCES `treno` (`idTreno`),
  CONSTRAINT `username2` FOREIGN KEY (`username`) REFERENCES `utente` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE VIEW `new_view` AS 
SELECT t.idTreno, sum(prezzo) as Prezzo, sum(lunghezza) as Lunghezza, sum(peso) as Peso,
	t.sigla, t.stato, t.nome
FROM treno t, vagone v
WHERE t.idTreno=v.idTreno
GROUP BY t.idTreno;


