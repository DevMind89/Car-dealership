CREATE DATABASE IF NOT EXISTS concesionario;

USE concesionario;

/*-----------------*/
/* CREACION TABLAS */
/*-----------------*/

CREATE TABLE IF NOT EXISTS clientes
(
	codCliente tinyint(4) PRIMARY KEY,
	nombre varchar(30) NOT NULL,
	apellidos varchar(30) NOT NULL,
	direccion varchar(30) NOT NULL,
	poblacion varchar(30) NOT NULL,
	codPostal varchar(6) NOT NULL,
	provincia varchar(20) NOT NULL,
	telefono varchar(12) NOT NULL,
	fechaNac date NOT NULL			
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
CREATE TABLE IF NOT EXISTS coches
(
	matricula varchar(7) PRIMARY KEY,
	marca varchar(30) NOT NULL,
	modelo varchar(30) NOT NULL,
	color varchar(10) NOT NULL,
	precio decimal(8,2) NOT NULL,
	codCliente tinyint(4) NOT NULL,
	fotografia varchar(30) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
CREATE TABLE IF NOT EXISTS revisiones
(
	numRevision tinyint(6) PRIMARY KEY,
	cambAceite boolean NOT NULL,
	cambFiltro boolean NOT NULL,
	revFrenos boolean NOT NULL,
	matricula varchar(7) NOT NULL
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

/*-----------------*/	
/*   RELACIONES    */
/*-----------------*/
	
ALTER TABLE coches
	ADD CONSTRAINT fk_coches_clientes FOREIGN KEY (codCliente) REFERENCES clientes (codCliente);

ALTER TABLE revisiones
	ADD CONSTRAINT fk_revisiones_clientes FOREIGN KEY (matricula) REFERENCES coches (matricula);
	

/*-----------------*/	
/*INTRODUCIR DATOS*/
/*-----------------*/

INSERT INTO clientes (codCliente, nombre, apellidos, direccion, poblacion, codPostal, provincia, telefono, fechaNac) VALUES
(1, 'Felipe', 'Carrasco Pérez', 'calle Nueva', 'Badajoz', '06007', 'Badajoz', '924 24 24 24', '1975-12-12'),
(2, 'Juan', 'Pérez Caminero', 'calle Vieja', 'Badajoz', '06009', 'Badajoz', '924 22 24 22', '1980-10-22'),
(3, 'Ana', 'Ramos García', 'calle Mayor', 'Badajoz', '06001', 'Badajoz',	'924 21 21 21',	'1970-08-30');


INSERT INTO coches (matricula, marca, modelo, color, precio, codCliente, fotografia) VALUES
('3232QQR', 'Honda', 'Civic', 'Azul', 15000, 1,''),
('2424TTY', 'Renault', 'Espace', 'Rojo', 15150, 1,''),
('4545YYR', 'Citroen', 'c3', 'Azul', 12254, 3,'');


INSERT INTO revisiones (numRevision, cambAceite, cambFiltro, revFrenos, matricula) VALUES
(1, 'true', 'false', 'false', '3232QQR'),
(2, 'true', 'true', 'false', '2424TTY');
