-- Insertion de types de composants
INSERT INTO TypeComposant (libelle) VALUES ('Carte graphique');
INSERT INTO TypeComposant (libelle) VALUES ('Processeur');
INSERT INTO TypeComposant (libelle) VALUES ('Mémoire RAM');
INSERT INTO TypeComposant (libelle) VALUES ('Disque dur');
INSERT INTO TypeComposant (libelle) VALUES ('Carte mère');
INSERT INTO typeComposant (idtype, libelle) VALUES (6, 'Ecran');


INSERT INTO MvtStock (libelle_, date_ens)
VALUES ('Mouvement de stock initial', CURRENT_DATE);


-- Insertion de mouvements de stock pour chaque composant
INSERT INTO MvtStockFille (entree, sortie, idMvtStock, idComposant)
VALUES
    -- NVIDIA GeForce RTX 4060
    (50, 10, 1, 1),
    -- AMD Radeon RX 6800
    (40, 5, 1, 2),
    -- Intel Core i9
    (30, 8, 1, 3),
    -- AMD Ryzen 7
    (25, 12, 1, 4),
    -- Corsair Vengeance
    (60, 20, 1, 5),
    -- G.Skill Trident Z
    (70, 15, 1, 6),
    -- Kingston Fury Beast
    (80, 10, 1, 7),
    -- TeamGroup T-Force
    (90, 25, 1, 8),
    -- Samsung SSD
    (100, 20, 1, 9),
    -- WD Black HDD
    (55, 5, 1, 10),
    -- MSI MPG Z690
    (35, 10, 1, 11),
    -- ASUS Prime B660
    (20, 5, 1, 12),
    -- Gigabyte Aorus Elite
    (30, 7, 1, 13),
    -- ASRock Steel Legend
    (40, 10, 1, 14),
    -- LG UltraGear
    (15, 5, 1, 15),
    -- Dell Alienware
    (10, 3, 1, 16),
    -- ASUS ROG Swift
    (5, 2, 1, 17),
    -- Samsung Odyssey
    (12, 4, 1, 18);

INSERT INTO Client (nom, email, numero) VALUES
('Jean Dupont', 'jean.dupont@email.com', 123456789),
('Marie Curie', 'marie.curie@email.com', 987654321),
('Paul Durand', 'paul.durand@email.com', 112233445),
('Sophie Martin', 'sophie.martin@email.com', 556677889),
('Luc Bernard', 'luc.bernard@email.com', 998877665);


INSERT INTO Technicien (idTechnicien ,  nom, email, password , idSexe ) VALUES
( 1  , 'Alice Robert', 'alice.robert@email.com', 'pass1234' ,1 ),
( 2, 'Michel Leroy', 'michel.leroy@email.com', 'secure5678' ,1 ),
( 3 , 'Claire Fontaine', 'claire.fontaine@email.com', 'clairePass' , 2 ),
( 4 , 'Pierre Rousseau', 'pierre.rousseau@email.com', 'pierre2024'  , 2),
( 5 , 'Laura Martin', 'laura.martin@email.com', 'lauraPass!' , 1);



INSERT INTO LiaisonOrdiComposant ( idordinateur, idcomposant, libelle)
VALUES 
( 1, 16, 'Ecran fissure'),
(2, 17, 'Affichage deforme'),
(3, 18, 'Pixels morts sur l ecran');


-- Insérer des types d'ordinateurs
INSERT INTO typeOrdi (Libelle) VALUES ('Portable');
INSERT INTO typeOrdi (Libelle) VALUES ('Bureau');

-- Insérer des ordinateurs
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('Dell XPS 13', 1, 1);
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('HP Pavilion', 2, 1);
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('Lenovo ThinkCentre', 3, 2);
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('iMac 24', 4, 2);
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('Dell PowerEdge', 5, 2);
INSERT INTO Ordinateur (Libelle, idClient, idType) VALUES ('MAC BOOK 14 pro', 5, 1);


INSERT INTO Sexe (idSexe, nom) VALUES
    (1, 'Masculin'),
    (2, 'Féminin'),
    (3, 'Autre');
    

-- Modèles spécifiques pour chaque type de composant
INSERT INTO Model (idModel, libelle) VALUES
(1, 'RTX 4060'),
(2, 'RX 6800'),
(3, 'i9 12900K'),
(4, '5800X'),
(5, 'DDR4 32GB'),
(6, 'DDR4 16GB'),
(7, 'DDR5 16GB'),
(8, 'DDR5 32GB'),
(9, '980 PRO 1TB'),
(10, '4TB HDD'),
(11, 'Z690 Tomahawk'),
(12, 'B660 PLUS'),
(13, 'X570 Elite'),
(14, 'B550M'),
(15, '27GN950 B'),
(16, 'AW3423DW'),
(17, 'PG32UQX'),
(18, 'G7 32');

--Etat 1 Atelier Etat 0 client ; 

-- Cartes graphiques
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('NVIDIA GeForce RTX 4060', 1, 1, 500.00, 450.00, 1),
('AMD Radeon RX 6800', 2, 1, 550.00, 500.00, 1);          


-- Cartes graphiques
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('NVIDIA GeForce RTX 5090', 1, 1, 500.00, 450.00, 1); 
     


-- Processeurs
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('Intel Core i9', 3, 2, 600.00, 550.00, 1),
('AMD Ryzen 7', 4, 2, 350.00, 300.00, 1);

-- Mémoire RAM
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('Corsair Vengeance', 5, 3, 120.00, 100.00, 1),
('G.Skill Trident Z', 6, 3, 85.00, 70.00, 1),
('Kingston Fury Beast', 7, 3, 150.00, 130.00, 1),
('TeamGroup T-Force', 8, 3, 180.00, 160.00, 1);

-- Disques durs
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('Samsung SSD', 9, 4, 150.00, 130.00, 1),
('WD Black HDD', 10, 4, 110.00, 90.00, 1);

-- Cartes mères
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('MSI MPG Z690', 11, 5, 300.00, 250.00, 1),
('ASUS Prime B660', 12, 5, 200.00, 170.00, 1),
('Gigabyte Aorus Elite', 13, 5, 250.00, 220.00, 1),
('ASRock Steel Legend', 14, 5, 180.00, 150.00, 1);

-- Écrans
INSERT INTO Composant (nom, idModel, idType, prixVente, prixAchat, etat) VALUES 
('LG UltraGear', 15, 6, 800.00, 750.00, 1),
('Dell Alienware', 16, 6, 1300.00, 1200.00, 1),
('ASUS ROG Swift', 17, 6, 2000.00, 1900.00, 1),
('Samsung Odyssey', 18, 6, 700.00, 650.00, 1);

INSERT INTO MvtStock (libelle_, date_ens)
VALUES 
('Réception initiale', '2025-01-10'),
('Sortie pour commande', '2025-01-12'),
('Réapprovisionnement', '2025-01-14');

INSERT INTO MvtStockFille (entree, sortie, idMvtStock, idComposant)
VALUES 
(100, 0, 1, 1), 
(50, 0, 1, 2),   
(0, 10, 2, 1),   
(0, 5, 2, 2),    
(200, 0, 3, 3),  
(150, 0, 3, 4);  


INSERT INTO LiaisonOrdiComposant (idOrdinateur, idComposant, Libelle, idService) VALUES
(1, 1, 'Réparation RTX 4060', 1),
(1, 9, 'Réparation SSD 1TB', 1),
(2, 2, 'Upgrade RX 6800', 2),
(2, 10, 'Upgrade HDD 4TB', 2),
(3, 3, 'Réparation i9', 1),
(3, 5, 'Réparation Corsair DDR4 32GB', 1),
(4, 4, 'Upgrade Ryzen 7', 2),
(4, 7, 'Upgrade Kingston DDR5 16GB', 2),
(5, 11, 'Réparation MSI Z690', 1),
(5, 13, 'Réparation Gigabyte X570', 1),
(6, 16, 'Upgrade Alienware 3423DW', 2),
(6, 12, 'Upgrade ASUS B660', 2);


INSERT INTO ServiceClient ( libelle ) VALUES
('Reparation'),
('Upgrade') ; 



INSERT INTO MoisComposant (idMois, idComposant, annee) 
VALUES 
(1, 1, 2025),   -- NVIDIA GeForce RTX 4060 en janvier 2025
(1, 2, 2025),   -- AMD Radeon RX 6800 en janvier 2025
(2, 3, 2025),   -- Intel Core i9 en février 2025
(3, 4, 2025),   -- AMD Ryzen 7 en mars 2025
(4, 5, 2025),   -- Corsair Vengeance en avril 2025
(5, 6, 2025),   -- G.Skill Trident Z en mai 2025
(6, 7, 2025),   -- Kingston Fury Beast en juin 2025
(7, 8, 2025),   -- TeamGroup T-Force en juillet 2025
(8, 9, 2025),   -- Samsung SSD en août 2025
(9, 10, 2025),  -- WD Black HDD en septembre 2025
(10, 11, 2025), -- MSI MPG Z690 en octobre 2025
(11, 12, 2025), -- ASUS Prime B660 en novembre 2025
(12, 13, 2025), -- Gigabyte Aorus Elite en décembre 2025
(1, 14, 2026),  -- ASRock Steel Legend en janvier 2026
(2, 15, 2026),  -- LG UltraGear en février 2026
(3, 16, 2026),  -- Dell Alienware en mars 2026
(4, 17, 2026),  -- ASUS ROG Swift en avril 2026
(5, 18, 2026);  -- Samsung Odyssey en mai 2026


-- Mettre à jour les prix pour chaque enregistrement
UPDATE serviceFille SET prix_reparation = 50000.00, prix_commision = 5000.00 WHERE idservicefille = 14;
UPDATE serviceFille SET prix_reparation = 30000.00, prix_commision = 3000.00 WHERE idservicefille = 15;
UPDATE serviceFille SET prix_reparation = 40000.00, prix_commision = 4000.00 WHERE idservicefille = 16;
UPDATE serviceFille SET prix_reparation = 20000.00, prix_commision = 2000.00 WHERE idservicefille = 17;
UPDATE serviceFille SET prix_reparation = 35000.00, prix_commision = 3500.00 WHERE idservicefille = 18;
UPDATE serviceFille SET prix_reparation = 45000.00, prix_commision = 4500.00 WHERE idservicefille = 19;
UPDATE serviceFille SET prix_reparation = 25000.00, prix_commision = 2500.00 WHERE idservicefille = 20;


-- Insertion des données d'historique pour NVIDIA GeForce RTX 4060
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (1, '2025-01-15', 450.00);
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (1, '2025-01-20', 500.00);

-- Insertion des données d'historique pour AMD Radeon RX 6800
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (2, '2025-01-10', 500.00);
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (2, '2025-01-22', 550.00);

-- Insertion des données d'historique pour Intel Core i9
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (3, '2025-01-18', 550.00);
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (3, '2025-01-25', 600.00);

-- Insertion des données d'historique pour AMD Ryzen 7
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (4, '2025-01-12', 300.00);
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix)
VALUES (4, '2025-01-28', 350.00);

-- Insertion des données d'historique pour Corsair Vengeance
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix )
VALUES (5, '2025-01-14', 100.00);
INSERT INTO HistoriquePrixComposant (idcomposant, DateHistorique, prix )
VALUES (5, '2025-01-27', 120.00);
