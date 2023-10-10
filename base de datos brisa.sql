CREATE TABLE `cliente` (
  `idpersona` int NOT NULL,
  `idcliente` int NOT NULL,
  PRIMARY KEY (`idcliente`),
  KEY `idpersona` (`idpersona`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `detalleventas` (
  `Iddetalle` int NOT NULL,
  `Idventa` int NOT NULL,
  `Idproducto` int NOT NULL,
  `cantidad` int NOT NULL,
  `subtotal` float NOT NULL,
  PRIMARY KEY (`Iddetalle`),
  KEY `Idventa` (`Idventa`),
  KEY `Idproducto` (`Idproducto`),
  CONSTRAINT `detalleventas_ibfk_1` FOREIGN KEY (`Idventa`) REFERENCES `ventas` (`Idventa`),
  CONSTRAINT `detalleventas_ibfk_2` FOREIGN KEY (`Idproducto`) REFERENCES `producto` (`Idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `domicilio` (
  `idDomicilio` int NOT NULL,
  `idPersona` int NOT NULL,
  `calle` varchar(30) NOT NULL,
  `altura` int NOT NULL,
  `ciudad` varchar(30) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `país` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idDomicilio`),
  KEY `idPersona` (`idPersona`),
  CONSTRAINT `domicilio_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pago` (
  `idpago` int NOT NULL,
  `medio` varchar(30) DEFAULT NULL,
  `idpersona` int NOT NULL,
  PRIMARY KEY (`idpago`),
  KEY `idpersona` (`idpersona`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `personas` (
  `idpersona` int NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `tipousuario` enum('vendedor','administrador','cliente') DEFAULT NULL,
  `discapacidad` varchar(30) DEFAULT NULL,
  `empresa` varchar(30) DEFAULT NULL,
  `mail` varchar(30) NOT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `producto` (
  `Idproducto` int NOT NULL,
  `precio` float NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `marca` varchar(20) NOT NULL,
  PRIMARY KEY (`Idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `productovendedores` (
  `idvendedor` int NOT NULL,
  `idproducto` int NOT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `idvendedor` (`idvendedor`),
  CONSTRAINT `productovendedores_ibfk_1` FOREIGN KEY (`idvendedor`) REFERENCES `vendedor` (`idvendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `telefono` (
  `idtelefono` int NOT NULL,
  `numero` varchar(20) NOT NULL,
  `idpersona` int NOT NULL,
  PRIMARY KEY (`idtelefono`),
  KEY `idpersona` (`idpersona`),
  CONSTRAINT `telefono_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vendedor` (
  `idpersona` int NOT NULL,
  `idvendedor` int NOT NULL,
  PRIMARY KEY (`idvendedor`),
  KEY `idpersona` (`idpersona`),
  CONSTRAINT `vendedor_ibfk_1` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ventas` (
  `Idventa` int NOT NULL,
  `idcliente` int NOT NULL,
  `fecha` date NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`Idventa`),
  KEY `idcliente` (`idcliente`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
