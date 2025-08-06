CREATE TABLE TypeComposant(
   idType SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL
);


CREATE TABLE Model ( 
   idModel SERIAL PRIMARY KEY, 
   libelle VARCHAR(255) NOT NULL 
) ; 

CREATE TABLE Composant(
   idComposant SERIAL PRIMARY KEY,
   nom VARCHAR(255), -- NULL par défaut
   idModel INTEGER NOT NULL ,
   idType INTEGER NOT NULL,
   prixVente DECIMAL(10,2), -- NULL par défaut
   prixAchat DECIMAL(10,2), -- NULL par défaut
   etat INTEGER NOT NULL ,  --etat 0 client etat 1 atelier
   FOREIGN KEY(idType) REFERENCES TypeComposant(idType) , 
   FOREIGN KEY(idModel) REFERENCES Model(idModel)
);

CREATE TABLE HistoriquePrixComposant( 
   idHistoriquePrixComposant SERIAL PRIMARY KEY,
   prix DECIMAL(15,2) NOT NULL,
   idComposant INTEGER NOT NULL ,
   DateHistorique DATE NOT NULL,
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
) ; 

CREATE TABLE Client(
   idClient SERIAL PRIMARY KEY,
   nom VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   numero INTEGER NOT NULL
);

CREATE TABLE PrixReparation(
   idPrix SERIAL PRIMARY KEY,
   prix DECIMAL(15,2) NOT NULL
   idComposant INTEGER NOT NULL ,
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant) 
);

CREATE TABLE MvtStock(
   idMvtStock SERIAL PRIMARY KEY,
   libelle_ VARCHAR(255) NOT NULL,
   date_ens DATE NOT NULL
);

CREATE TABLE Atelier(
   idAtelier SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL
);

CREATE TABLE Achat(
   idAchat SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL,
   dateAchat DATE NOT NULL
);

CREATE TABLE Technicien(
   idTechnicien SERIAL PRIMARY KEY,
   nom VARCHAR(255),
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL , 
   idSexe INTEGER NOT NULL 
   FOREIGN KEY(idSexe) REFERENCES Sexe(idSexe)
);

CREATE TABLE Sexe ( 
   idSexe INTEGER NOT NULL , 
   nom  VARCHAR(255) NOT NULL 
)  ; 


ALTER TABLE Technicien
ADD COLUMN idSexe INTEGER;

CREATE TABLE Admin(
   idAdmin SERIAL PRIMARY KEY,
   nom VARCHAR(255) NOT NULL,
   password VARCHAR(50) NOT NULL,
   email VARCHAR(255) NOT NULL
);


CREATE TABLE AchatDetail(
   idAchatDetail SERIAL PRIMARY KEY,
   qte INTEGER NOT NULL,
   pu DECIMAL(15,2) NOT NULL,
   libelle VARCHAR(255) NOT NULL,
   idAchat INTEGER NOT NULL,
   idComposant INTEGER NOT NULL,
   FOREIGN KEY(idAchat) REFERENCES Achat(idAchat),
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
);

CREATE TABLE Panier( 
   idPanier SERIAL PRIMARY KEY , 
   libelle VARCHAR(255) NOT NULL,
   idComposant INTEGER NOT NULL, 
   idTypeComposant INTEGER NOT NULL, 
   idClient INTEGER NOT NULL,
   idTechnicien INTEGER NOT NULL,
   idOrdinateur INTEGER NOT NULL, 
   FOREIGN KEY(idTechnicien) REFERENCES Technicien(idTechnicien),
   FOREIGN KEY(idClient) REFERENCES Client(idClient),
   FOREIGN KEY(idTypeComposant) REFERENCES TypeComposant(idType)
) ; 

CREATE TABLE ServiceClient ( 
   idService SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL 
); 

CREATE TABLE ServiceMere(                -- Type d'service ex : upgrade , reparation , analys  
   idServiceMere SERIAL PRIMARY KEY,
   dateDebut DATE,
   dateFin DATE,
   libelle VARCHAR(255) NOT NULL
);

CREATE TABLE ServiceFille(
   idServiceFille SERIAL PRIMARY KEY,
   dateDebut DATE ,
   dateFin DATE,
   libelle VARCHAR(255) NOT NULL,
   idTechnicien INTEGER NOT NULL,
   idServiceMere INTEGER NOT NULL,
   idClient INTEGER NOT NULL,
   idComposant INTEGER NOT NULL,
   idTypeService INTEGER NOT NULL, 
   prix_reparation DECIMAL NOT NULL , 
   prix_commision DECIMAL NOT NULL , 
   FOREIGN KEY(idTypeService) REFERENCES ServiceClient(idService) , 
   FOREIGN KEY(idServiceMere) REFERENCES ServiceMere(idServiceMere),
   FOREIGN KEY(idTechnicien) REFERENCES Technicien(idTechnicien),
   FOREIGN KEY(idClient) REFERENCES Client(idClient),
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
);

CREATE TABLE Vente(
   idVente SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL,
   dateVente DATE,
   idAtelier INTEGER NOT NULL,
   idClient INTEGER NOT NULL
   FOREIGN KEY(idAtelier) REFERENCES Atelier(idAtelier),
   FOREIGN KEY(idClient) REFERENCES Client(idClient)
);

CREATE TABLE MvtStockFille(
   id SERIAL PRIMARY KEY,
   entree int NOT NULL,
   sortie int NOT NULL,
   idMvtStock INTEGER NOT NULL,
   idComposant INTEGER NOT NULL,
   FOREIGN KEY(idMvtStock) REFERENCES MvtStock(idMvtStock),
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
);

CREATE TABLE VenteDetail(
   idVenteDetail SERIAL PRIMARY KEY,
   qte INTEGER NOT NULL,
   pu DECIMAL(15,2) NOT NULL,
   libelle VARCHAR(255) NOT NULL,
   idVente INTEGER NOT NULL,
   idComposant INTEGER NOT NULL,
   FOREIGN KEY(idVente) REFERENCES Vente(idVente),
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
);

CREATE TABLE typeOrdi(
   idType SERIAL PRIMARY KEY, 
   Libelle VARCHAR(255) NOT NULL
) ;

CREATE TABLE Ordinateur (
   idOrdinateur SERIAL PRIMARY KEY,
   Libelle VARCHAR(50) NOT NULL,            
   idClient INTEGER NOT NULL,               
   idType INTEGER NOT NULL,                 
   FOREIGN KEY(idType) REFERENCES typeOrdi(idType),
   FOREIGN KEY(idClient) REFERENCES Client(idClient)
);


CREATE TABLE Mois( 
   idMois SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL
) ; 

CREATE TABLE MoisComposant(
   idLiaison SERIAL PRIMARY KEY,
   idMois INTEGER NOT NULL,
   idComposant INTEGER NOT NULL,
   annee INTEGER NOT NULL,
   FOREIGN KEY(idMois) REFERENCES Mois(idMois),
   FOREIGN KEY(idComposant) REFERENCES Composant(idComposant)
);


ALTER TABLE ServiceFille

ADD COLUMN idsexe INT;