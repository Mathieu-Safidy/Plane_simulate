CREATE TRIGGER ville_trigger
BEFORE INSERT ON ville
FOR EACH ROW
WHEN (NEW.id_ville IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER type_siege_trigger
BEFORE INSERT ON type_siege
FOR EACH ROW
WHEN (NEW.id_type IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER model_trigger
BEFORE INSERT ON model
FOR EACH ROW
WHEN (NEW.id_model IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER role_trigger
BEFORE INSERT ON role
FOR EACH ROW
WHEN (NEW.id_role IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER avion_trigger
BEFORE INSERT ON avion
FOR EACH ROW
WHEN (NEW.id_avion IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER vols_trigger
BEFORE INSERT ON vols
FOR EACH ROW
WHEN (NEW.id_vols IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER users_trigger
BEFORE INSERT ON users
FOR EACH ROW
WHEN (NEW.id_users IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER reservation_mere_trigger
BEFORE INSERT ON reservation_mere
FOR EACH ROW
WHEN (NEW.id_reservation_mere IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER promotion_trigger
BEFORE INSERT ON promotion
FOR EACH ROW
WHEN (NEW.id_promotion IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER place_trigger
BEFORE INSERT ON place
FOR EACH ROW
WHEN (NEW.id_place IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER reservation_trigger
BEFORE INSERT ON reservation
FOR EACH ROW
WHEN (NEW.id_reservation IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER place_reserve_trigger
BEFORE INSERT ON place_reserve
FOR EACH ROW
WHEN (NEW.id_place IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER parametre_trigger
BEFORE INSERT ON parametre
FOR EACH ROW
WHEN (NEW.id_parametre IS NULL)
EXECUTE FUNCTION assigner_id();

CREATE TRIGGER type_param_trigger
BEFORE INSERT ON type_param
FOR EACH ROW
WHEN (NEW.id_type IS NULL)
EXECUTE FUNCTION assigner_id();


-- 

DROP TRIGGER IF EXISTS ville_trigger ON ville;
DROP TRIGGER IF EXISTS type_siege_trigger ON type_siege;
DROP TRIGGER IF EXISTS model_trigger ON model;
DROP TRIGGER IF EXISTS role_trigger ON role;
DROP TRIGGER IF EXISTS avion_trigger ON avion;
DROP TRIGGER IF EXISTS vols_trigger ON vols;
DROP TRIGGER IF EXISTS users_trigger ON users;
DROP TRIGGER IF EXISTS reservation_mere_trigger ON reservation_mere;
DROP TRIGGER IF EXISTS promotion_trigger ON promotion;
DROP TRIGGER IF EXISTS place_trigger ON place;
DROP TRIGGER IF EXISTS reservation_trigger ON reservation;
DROP TRIGGER IF EXISTS place_reserve_trigger ON place_reserve;
