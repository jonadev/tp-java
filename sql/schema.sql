CREATE SCHEMA concesionaria;
USE concesionaria;

CREATE TABLE vehiculo (
   id_vehiculo bigint unsigned not null AUTO_INCREMENT,
   tipo varchar(5) not null,
   ruedas int,
   anio smallint,
   color varchar(20),
   cajaAutomatica boolean,
   tipoCombustible varchar(20),
   cantidadKilometros int,
   cilindrada int,
   patente varchar(15),
   marca varchar(25),
   modelo varchar(30),
   precio double,
   PRIMARY KEY(id_vehiculo)
);
   
CREATE TABLE auto (
    id_auto bigint unsigned not null,
    puertas int,
    litrosBaul int,
    cantidadAirbags int,
    PRIMARY KEY (id_auto),
    FOREIGN KEY (id_auto) REFERENCES vehiculo(id_vehiculo)   
 );
       
CREATE TABLE moto (
    id_moto bigint unsigned not null,
    cascoIncluido boolean,
    cantidadTiempoMotor int,
    PRIMARY KEY (id_moto),
    FOREIGN KEY (id_moto) REFERENCES vehiculo(id_vehiculo)
 );