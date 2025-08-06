
-- req1 ==  detail_siege -> join -> reservation_mere -> getByType -> geNombre
CREATE OR REPLACE VIEW v_detail_reservation as (
select detail_siege.id_type , detail_siege.nombre , prix , reservation_mere.id_reservation_mere , reservation_mere.id_vols , id_promotion from detail_siege 
join 
reservation_mere 
on 
detail_siege.id_reservation_mere = reservation_mere.id_reservation_mere
left join 
promotion 
on 
reservation_mere.id_vols = promotion.id_vols and detail_siege.id_type = promotion.id_type
);

-- req2 == place_reserve -> join -> reservation -> count by reservation_mere , id_type 
-- CREATE OR REPLACE VIEW v_nombre_reserver as (
-- select quantite.id_reservation_mere , quantite.id_type , quantite.nombre 
-- from place_reserve join
-- (
--     select distinct id_reservation_mere , reservation.id_type , COALESCE(COUNT(id_place), 0) AS nombre from place_reserve 
--     join reservation on place_reserve.id_reservation = reservation.id_reservation
--     group by id_reservation_mere , reservation.id_type
-- ) as quantite
-- on place_reserve.id_type = quantite.id_type 
-- );

CREATE OR REPLACE VIEW v_nombre_reserver AS (
  SELECT 
    ds.id_reservation_mere,
    ds.id_type,
    COALESCE(COUNT(pr.id_place), 0) AS nombre
  FROM detail_siege ds
  LEFT JOIN reservation r ON r.id_reservation_mere = ds.id_reservation_mere AND r.id_type = ds.id_type
  LEFT JOIN place_reserve pr ON pr.id_reservation = r.id_reservation
  GROUP BY ds.id_reservation_mere, ds.id_type
);


-- req3 == req1 >  join by type -> req2 
-- select quantite.id_reservation_mere , id_vols , quantite.id_type , detail.nombre as nombre_total , quantite.nombre as nombre_reserver from v_detail_reservation as detail 
-- join v_nombre_reserver as quantite on detail.id_type = quantite.id_type 


-- vue == req3(nombretotal(req1) - nombretotal(req2))
-- CREATE OR REPLACE VIEW v_quantite_place_restant as (
-- select id_reservation_mere , id_vols , id_type , (nombre_total - nombre_reserver) as nombre from
-- (
--         select distinct quantite.id_reservation_mere , id_vols , quantite.id_type , detail.nombre as nombre_total , quantite.nombre as nombre_reserver from v_detail_reservation as detail 
--         left join v_nombre_reserver as quantite on detail.id_type = quantite.id_type 
-- ) 
-- as counter );

CREATE OR REPLACE VIEW v_quantite_place_restant AS (
  SELECT 
    detail.id_reservation_mere,
    detail.id_vols,
    detail.id_type,
    detail.nombre - COALESCE(quantite.nombre, 0) AS nombre
  FROM v_detail_reservation AS detail
  LEFT JOIN v_nombre_reserver AS quantite 
    ON detail.id_reservation_mere = quantite.id_reservation_mere 
   AND detail.id_type = quantite.id_type
);


CREATE OR REPLACE VIEW v_vols_offre as (
select * from (select sum(nombre) as quantite , id_vols from v_quantite_place_restant group by id_vols) as mitady where mitady.quantite > 0  
);

-- select distinct id_ville_arrive , id_ville_depart from v_detail_vols_dispo;

CREATE OR REPLACE VIEW v_detail_vols as 
(
    select vols.id_vols , id_reservation_mere , date_vol , id_ville_arrive , id_ville_depart , id_avion , id_type , nombre
    from v_quantite_place_restant join vols on v_quantite_place_restant.id_vols = vols.id_vols
);

CREATE OR REPLACE VIEW v_detail_vols_dispo as 
(
    select quantite::INTEGER , v_detail_vols.id_vols , id_reservation_mere , date_vol , id_ville_arrive , id_ville_depart , id_avion , id_type 
    from v_vols_offre join v_detail_vols on v_vols_offre.id_vols = v_detail_vols.id_vols
);


-- view pour prendre les dernier parametre