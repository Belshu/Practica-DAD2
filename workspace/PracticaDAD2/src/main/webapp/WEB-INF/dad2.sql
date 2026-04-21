

-- =========================
-- USUARIOS DEL SISTEMA
-- =========================
CREATE TABLE Users (
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	type VARCHAR(20) NOT NULL,
	PRIMARY KEY (username),
	CONSTRAINT chk_user_type CHECK (type IN ('ADMIN', 'STUDENT'))
);

-- =========================
-- TITULACIONES
-- =========================
CREATE TABLE Titulations (
    id VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_titulation_nombre (nombre)
);


-- Datos opcionales de prueba
INSERT INTO Users (username, password, type)
VALUES ('admin', 'admin', 'ADMIN');
INSERT INTO Users (username, password, type)
VALUES ('user1', 'user1', 'STUDENT');

INSERT INTO Titulations (id, nombre)
VALUES 
    ('DAD2', 'Desarrollo de Aplicaciones Distribuidas 2'),
    ('MS', 'Modelado de Software'),
    ('PW', 'Programacion Web'),
    ('CSI', 'Seguridad de la informacion');
   

    
    
    
    
    