CREATE TABLE ville(
   id_ville VARCHAR(50) ,
   nom VARCHAR(50) ,
   PRIMARY KEY(id_ville)
);

CREATE TABLE type_siege(
   id_type VARCHAR(50) ,
   nom VARCHAR(50) ,
   niveau VARCHAR(50) ,
   PRIMARY KEY(id_type)
);

CREATE TABLE model(
   id_model VARCHAR(50) ,
   nom VARCHAR(50) ,
   PRIMARY KEY(id_model)
);

CREATE TABLE role(
   id_role VARCHAR(50) ,
   label VARCHAR(50) ,
   PRIMARY KEY(id_role)
);

CREATE TABLE type_param(
   id_type VARCHAR(50) ,
   libele VARCHAR(50) ,
   PRIMARY KEY(id_type)
);

CREATE TABLE avion(
   id_avion VARCHAR(50) ,
   date_fabrication DATE NOT NULL,
   id_model VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_avion),
   FOREIGN KEY(id_model) REFERENCES model(id_model)
);

CREATE TABLE vols(
   id_vols VARCHAR(50) ,
   date_vol TIMESTAMP NOT NULL,
   time_cancel NUMERIC(5,1)  ,
   id_ville_arrive VARCHAR(50)  NOT NULL,
   id_ville_depart VARCHAR(50)  NOT NULL,
   id_avion VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_vols),
   FOREIGN KEY(id_ville_arrive) REFERENCES ville(id_ville),
   FOREIGN KEY(id_ville_depart) REFERENCES ville(id_ville),
   FOREIGN KEY(id_avion) REFERENCES avion(id_avion)
);

CREATE TABLE users(
   id_users VARCHAR(50) ,
   nom VARCHAR(100) ,
   date_naissance DATE NOT NULL,
   email VARCHAR(100) ,
   password VARCHAR(50) ,
   adresse VARCHAR(50) ,
   CIN VARCHAR(50) ,
   id_role VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_users),
   FOREIGN KEY(id_role) REFERENCES role(id_role)
);

CREATE TABLE reservation_mere(
   id_reservation_mere CHAR(50) ,
   id_vols VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_reservation_mere),
   FOREIGN KEY(id_vols) REFERENCES vols(id_vols)
);

CREATE TABLE promotion(
   id_promotion VARCHAR(50) ,
   valeur NUMERIC(5,2)   NOT NULL,
   date_promotion DATE NOT NULL,
   nombre INTEGER,
   date_fin TIMESTAMP NOT NULL,
   id_type VARCHAR(50)  NOT NULL,
   id_vols VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_promotion),
   FOREIGN KEY(id_type) REFERENCES type_siege(id_type),
   FOREIGN KEY(id_vols) REFERENCES vols(id_vols)
);

CREATE TABLE place(
   id_place VARCHAR(50) ,
   id_type VARCHAR(50)  NOT NULL,
   id_avion VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_place),
   FOREIGN KEY(id_type) REFERENCES type_siege(id_type),
   FOREIGN KEY(id_avion) REFERENCES avion(id_avion)
);

CREATE TABLE parametre(
   id_parametre VARCHAR(50) ,
   valeur NUMERIC(8,2)  ,
   date_ajout DATE,
   id_type VARCHAR(50) ,
   PRIMARY KEY(id_parametre),
   FOREIGN KEY(id_type) REFERENCES type_param(id_type)
);

CREATE TABLE reservation(
   id_reservation VARCHAR(50) ,
   date_reservation TIMESTAMP,
   id_reservation_mere CHAR(50)  NOT NULL,
   id_type VARCHAR(50)  NOT NULL,
   id_users VARCHAR(50) ,
   PRIMARY KEY(id_reservation),
   FOREIGN KEY(id_reservation_mere) REFERENCES reservation_mere(id_reservation_mere),
   FOREIGN KEY(id_type) REFERENCES type_siege(id_type),
   FOREIGN KEY(id_users) REFERENCES users(id_users)
);

CREATE TABLE place_reserve(
   id_place VARCHAR(50) ,
   prix NUMERIC(15,2)   NOT NULL,
   id_promotion VARCHAR(50) ,
   id_reservation VARCHAR(50)  NOT NULL,
   id_type VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_place),
   FOREIGN KEY(id_promotion) REFERENCES promotion(id_promotion),
   FOREIGN KEY(id_reservation) REFERENCES reservation(id_reservation),
   FOREIGN KEY(id_type) REFERENCES type_siege(id_type)
);

CREATE TABLE detail_siege(
   id_type VARCHAR(50) ,
   id_reservation_mere CHAR(50) ,
   nombre INTEGER,
   prix NUMERIC(15,2)  ,
   PRIMARY KEY(id_type, id_reservation_mere),
   FOREIGN KEY(id_type) REFERENCES type_siege(id_type),
   FOREIGN KEY(id_reservation_mere) REFERENCES reservation_mere(id_reservation_mere)
);

CREATE TABLE passport (
   id_passport VARCHAR(50) ,
   id_users VARCHAR(50)  NOT NULL,
   image_passport VARCHAR(255) ,
   PRIMARY KEY(id_passport),
   FOREIGN KEY(id_users) REFERENCES users(id_users)
);