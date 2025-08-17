CREATE OR REPLACE FUNCTION assigner_id() 
RETURNS TRIGGER AS $$
DECLARE
    sequence_name TEXT;
    sequence_preset TEXT;
    generated_id TEXT;
BEGIN
    -- Déterminer la séquence et le préfixe correspondant à la table
    IF TG_TABLE_NAME = 'ville' THEN
        sequence_name := 'ville_seq';
        sequence_preset := 'VIL';
    ELSIF TG_TABLE_NAME = 'type_siege' THEN
        sequence_name := 'type_siege_seq';
        sequence_preset := 'TYS';
    ELSIF TG_TABLE_NAME = 'model' THEN
        sequence_name := 'model_seq';
        sequence_preset := 'MOD';
    ELSIF TG_TABLE_NAME = 'role' THEN
        sequence_name := 'role_seq';
        sequence_preset := 'ROL';
    ELSIF TG_TABLE_NAME = 'avion' THEN
        sequence_name := 'avion_seq';
        sequence_preset := 'AVN';
    ELSIF TG_TABLE_NAME = 'vols' THEN
        sequence_name := 'vols_seq';
        sequence_preset := 'VOL';
    ELSIF TG_TABLE_NAME = 'users' THEN
        sequence_name := 'users_seq';
        sequence_preset := 'USE';
    ELSIF TG_TABLE_NAME = 'reservation_mere' THEN
        sequence_name := 'reservation_mere_seq';
        sequence_preset := 'REM';
    ELSIF TG_TABLE_NAME = 'promotion' THEN
        sequence_name := 'promotion_seq';
        sequence_preset := 'PRO';
    ELSIF TG_TABLE_NAME = 'place' THEN
        sequence_name := 'place_seq';
        sequence_preset := 'PLC';
    ELSIF TG_TABLE_NAME = 'reservation' THEN
        sequence_name := 'reservation_seq';
        sequence_preset := 'RES';
    ELSIF TG_TABLE_NAME = 'place_reserve' THEN
        sequence_name := 'place_reserve_seq';
        sequence_preset := 'PLR';
    ELSIF TG_TABLE_NAME = 'parametre' THEN
        sequence_name := 'parametre_seq';
        sequence_preset := 'PRM';
    ELSIF TG_TABLE_NAME = 'type_param' THEN
        sequence_name := 'type_param_seq';
        sequence_preset := 'TPR';
    ELSIF TG_TABLE_NAME = 'passport' THEN
        sequence_name := 'passport_seq';
        sequence_preset := 'PASS';
    ELSE
        RETURN NEW;
    END IF;
    
    -- Générer l'ID unique
    EXECUTE format('SELECT %L || LPAD(nextval(''%I'')::TEXT, 6, ''0'')', sequence_preset, sequence_name)
    INTO generated_id;

    -- Assigner l'ID au bon champ
    IF TG_TABLE_NAME = 'ville' THEN
        NEW.id_ville := generated_id;
    ELSIF TG_TABLE_NAME = 'type_siege' THEN
        NEW.id_type := generated_id;
    ELSIF TG_TABLE_NAME = 'model' THEN
        NEW.id_model := generated_id;
    ELSIF TG_TABLE_NAME = 'role' THEN
        NEW.id_role := generated_id;
    ELSIF TG_TABLE_NAME = 'avion' THEN
        NEW.id_avion := generated_id;
    ELSIF TG_TABLE_NAME = 'vols' THEN
        NEW.id_vols := generated_id;
    ELSIF TG_TABLE_NAME = 'users' THEN
        NEW.id_users := generated_id;
    ELSIF TG_TABLE_NAME = 'reservation_mere' THEN
        NEW.id_reservation_mere := generated_id;
    ELSIF TG_TABLE_NAME = 'promotion' THEN
        NEW.id_promotion := generated_id;
    ELSIF TG_TABLE_NAME = 'place' THEN
        NEW.id_place := generated_id;
    ELSIF TG_TABLE_NAME = 'reservation' THEN
        NEW.id_reservation := generated_id;
    ELSIF TG_TABLE_NAME = 'place_reserve' THEN
        NEW.id_place := generated_id;
    ELSIF TG_TABLE_NAME = 'parametre' THEN
        NEW.id_parametre := generated_id;
    ELSIF TG_TABLE_NAME = 'type_param' THEN
        NEW.id_type := generated_id;
    ELSIF TG_TABLE_NAME = 'passport' THEN
        NEW.id_passport := generated_id;    
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


DROP FUNCTION IF EXISTS assigner_id();
DROP FUNCTION IF EXISTS assigner_id() CASCADE;
