-- Insérer des villes
INSERT INTO ville (id_ville, nom) VALUES
('VIL000001', 'Paris'),
('VIL000002', 'New York'),
('VIL000003', 'Tokyo'),
('VIL000004', 'Londres'),
('VIL000005', 'Berlin');

-- Insérer des modèles d'avions
INSERT INTO model (id_model, nom) VALUES
('MOD000001', 'Boeing 747'),
('MOD000002', 'Airbus A320'),
('MOD000003', 'Embraer E190');

-- Insérer des avions
INSERT INTO avion (id_avion, date_fabrication, id_model) VALUES
('AVN000001', '2010-05-20', 'MOD000001'),
('AVN000002', '2015-08-15', 'MOD000002'),
('AVN000003', '2018-12-10', 'MOD000003');

-- Insérer des vols
INSERT INTO vols (id_vols, date_vol, id_ville_arrive, id_ville_depart, id_avion) VALUES
('VOL000001', '2025-03-10 08:00:00', 'VIL000002', 'VIL000001', 'AVN000001'),
('VOL000002', '2025-03-11 14:30:00', 'VIL000003', 'VIL000002', 'AVN000002'),
('VOL000003', '2025-03-12 19:45:00', 'VIL000001', 'VIL000003', 'AVN000003');

-- Insérer des types de sièges
INSERT INTO type_siege (id_type, nom, niveau) VALUES
('TYS000001', 'Economie', 1),
('TYS000002', 'Business', 2),
('TYS000003', 'Première Classe', 3);

-- Insérer des rôles
INSERT INTO role (id_role, label) VALUES
('ROL000001', 'Admin'),
('ROL000002', 'Client'),
('ROL000003', 'Employé');

-- Insérer des utilisateurs
INSERT INTO users (id_users, nom, email, date_naissance, password, adresse, CIN, id_role) VALUES
('USE000001', 'Dupont', 'jean.dupont@example.com', '1990-05-15', 'pass123', '10 Rue de Paris', '123456789', 'ROL000002'),
('USE000002', 'Martin', 'sophie.martin@example.com', '1985-08-21', 'securepass', '20 Avenue NYC', '987654321', 'ROL000003'),
('USE000003', 'Durand', 'paul.durand@example.com', '1988-12-10', 'paulpass', '30 Blvd Tokyo', '192837465', 'ROL000001'),
('USE000004', 'Mathieu', 'safidy800@gmail.com', '2004-12-1', 'test', '30 Blvd Tokyo', '101251238007', 'ROL000002');

-- Insérer des réservations mères
INSERT INTO reservation_mere (id_reservation_mere, id_vols) VALUES
('REM000001', 'VOL000001'),
('REM000002', 'VOL000002');

-- Insérer des promotions
INSERT INTO promotion (id_promotion, valeur, date_promotion, nombre, date_fin, id_type, id_vols) VALUES
('PROMO000001', 10.00, '2025-01-1', 100, '2025-03-1', 'TYS000001', 'VOL000001'),
('PROMO000002', 20.00, '2025-02-5', 50, '2025-04-15', 'TYS000002', 'VOL000002');

-- Insérer des places
INSERT INTO place (id_place, id_type, id_avion) VALUES
('PLC000001', 'TYS000001', 'AVN000001'),
('PLC000002', 'TYS000002', 'AVN000002'),
('PLC000003', 'TYS000003', 'AVN000003');

-- Insérer des réservations
INSERT INTO reservation (id_reservation, date_reservation, id_reservation_mere, id_type, id_users) VALUES
('RES000001', '2025-03-01 10:30:00', 'REM000001', 'TYS000001', 'USE000001'),
('RES000002', '2025-03-02 13:00:00', 'REM000002', 'TYS000002', 'USE000002');

-- Insérer des places réservées
INSERT INTO place_reserve (id_place, prix, id_promotion, id_reservation, id_type) VALUES
('PLC000001', 150.00, null , 'RES000001', 'TYS000001'),
('PLC000002', 300.00, null , 'RES000002', 'TYS000002');

-- Insérer des détails de siège
INSERT INTO detail_siege (id_type, id_reservation_mere, nombre, prix) VALUES
('TYS000001', 'REM000001', 1, 150.00),
('TYS000002', 'REM000002', 1, 300.00);

-- Inserer type 
INSERT INTO type_param (libele) VALUES
('saved_time'),
('cancel_time');

-- Inserer des parametre
INSERT INTO parametre (valeur,date_ajout,id_type) VALUES 
(1440,'2025-03-1','TPR000002'),
(20,'2025-03-1','TPR000001');

-- select * from v_detail_reservation where 1=1 and id_type = 'TYS000001' and id_reservation_mere = 'REM000001' and id_vols = 'VOL000009';