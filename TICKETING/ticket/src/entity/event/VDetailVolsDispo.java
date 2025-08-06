package entity.event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.avion.Avion;
import entity.lieu.Ville;
import entity.place.TypeSiege;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "v_detail_vols_dispo") // Correspondance avec la vue
public class VDetailVolsDispo extends Entity {
    
    @Column(name = "id_vols")
    String idVols;

    @Column(name = "date_vol")
    Timestamp dateVol;

    @Column(name = "id_ville_arrive")
    String idVilleArrive;

    @Column(name = "id_ville_depart")
    String idVilleDepart;

    @Column(name = "id_avion")
    String idAvion;

    @Column(name = "quantite")
    Integer quantite;

    @Column(name = "id_reservation_mere")
    String idReservationMere;

    @Column(name = "id_type")
    String idType;

    @Ignore
    Ville villeDepart;

    @Ignore
    Ville villeArrive;

    @Ignore
    Avion avion;

    @Ignore
    VDetailReservation detail;
    
    @Ignore
    Integer placeDispo;

    @Ignore
    TypeSiege siegeDetail;

    public TypeSiege getSiegeDetail() {
        return siegeDetail;
    }

    public void setSiegeDetail(TypeSiege siegeDetail) {
        this.siegeDetail = siegeDetail;
    }

    public Integer getPlaceDispo() {
        return placeDispo;
    }

    public void setPlaceDispo(Integer placeDispo) {
        this.placeDispo = placeDispo;
    }

    public VDetailReservation getDetail() {
        return detail;
    }

    public void setDetail(VDetailReservation detail) {
        this.detail = detail;
    }

    public VDetailVolsDispo() throws Exception {
        initiate();
    }

    public VDetailVolsDispo(String idVols, Timestamp dateVol, String idVilleArrive, String idVilleDepart, 
                            String idAvion, Integer quantite, String idReservationMere, String idType) throws Exception {
        this.idVols = idVols;
        this.dateVol = dateVol;
        this.idVilleArrive = idVilleArrive;
        this.idVilleDepart = idVilleDepart;
        this.idAvion = idAvion;
        this.quantite = quantite;
        this.idReservationMere = idReservationMere;
        this.idType = idType;
        initiate();
    }

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    public Timestamp getDateVol() {
        return dateVol;
    }

    public void setDateVol(Timestamp dateVol) {
        this.dateVol = dateVol;
    }

    public String getIdVilleArrive() {
        return idVilleArrive;
    }

    public void setIdVilleArrive(String idVilleArrive) {
        this.idVilleArrive = idVilleArrive;
    }

    public String getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(String idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public String getIdReservationMere() {
        return idReservationMere;
    }

    public void setIdReservationMere(String idReservationMere) {
        this.idReservationMere = idReservationMere;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(Ville villeArrive) {
        this.villeArrive = villeArrive;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void setVilleArriveBase() {
        try {
            Ville arrive = new Ville();
            arrive.setIdVille(this.getIdVilleArrive());
            this.setVilleArrive((Ville) arrive.find(null, null)[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVilleDepartBase() {
        try {
            Ville depart = new Ville();
            depart.setIdVille(this.getIdVilleDepart());
            this.setVilleDepart((Ville) depart.find(null, null)[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAvionBase() {
        try {
            Avion avion = new Avion();
            avion.setIdAvion(this.getIdAvion());
            this.setAvion((Avion) avion.find(null, null)[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTypeBase() {
        try {
            TypeSiege siege = new TypeSiege();
            siege.setIdType(this.getIdType());
            this.setSiegeDetail((TypeSiege)siege.find(null, null)[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDetailBase() {
        try {
            VDetailReservation det = new VDetailReservation();
            det.setIdType(idType);
            det.setIdVols(idVols);
            det.setIdReservationMere(idReservationMere);
            det = (VDetailReservation)det.find(null, null)[0];
            this.setDetail(det);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void manyToOne() {
        try {
            if (this.idVilleArrive != null) {
                this.setVilleArriveBase();
            }
            if (this.idVilleDepart != null) {
                this.setVilleDepartBase();
            }
            if (this.idAvion != null) {
                this.setAvionBase();
            }
            if(this.idType != null) {
                this.setTypeBase();
            }
            if (this.idType!= null && this.idVols!= null && this.idReservationMere!= null) {
                this.setDetailBase();
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation des relations : " + e.getMessage());
        }
    }

    public List<VDetailVolsDispo> convertToVDetailVolsDispo(Object[] obj) {
        List<VDetailVolsDispo> prods = new ArrayList<>();
        for (Object prod : obj) {
            VDetailVolsDispo product = (VDetailVolsDispo) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }
    public List<VDetailVolsDispo> convertToVDetailVolsDispo(List<VDetailVolsDispo> obj) {
        List<VDetailVolsDispo> prods = new ArrayList<>();
        for (VDetailVolsDispo prod : obj) {
            VDetailVolsDispo product = prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

}
