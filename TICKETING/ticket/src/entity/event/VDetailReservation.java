package entity.event;

import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "v_detail_reservation")
public class VDetailReservation extends Entity {

    @Column(name = "id_type")
    String idType;

    @Column(name = "nombre")
    Integer nombre;

    @Column(name = "prix")
    Double prix;

    @Column(name = "id_reservation_mere")
    String idReservationMere;

    @Column(name = "id_vols")
    String idVols;

    @Column(name = "id_promotion")
    String idPromotion;

    @Ignore
    ReservationMere mere;

    @Ignore
    Vols vols;

    @Ignore
    Promotion promotion;

    public String getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(String idPromotion) {
        this.idPromotion = idPromotion;
    }

    public VDetailReservation() throws Exception {
        initiate();
    }

    public VDetailReservation(Integer nombre, Double prix, String idReservationMere, String idVols) throws Exception {
        this.nombre = nombre;
        this.prix = prix;
        this.idReservationMere = idReservationMere;
        this.idVols = idVols;
        initiate();
    }

    public VDetailReservation(String idType, Integer nombre, Double prix, String idReservationMere, String idVols)
            throws Exception {
        this.idType = idType;
        this.nombre = nombre;
        this.prix = prix;
        this.idReservationMere = idReservationMere;
        this.idVols = idVols;
        initiate();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getIdReservationMere() {
        return idReservationMere;
    }

    public void setIdReservationMere(String idReservationMere) {
        this.idReservationMere = idReservationMere;
    }

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    public void setVolsBase() {
        try {
            Vols vol = new Vols();
            vol.setIdVols(idVols);
            this.vols = (Vols)vol.find(null, null)[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReservationMereBase() {
        try {
            ReservationMere mere = new ReservationMere();
            mere.setIdReservationMere(idReservationMere);
            this.mere = (ReservationMere) mere.find(null, null)[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPromotionBase() {
        try {
            Promotion promotion = new Promotion();
            promotion.setIdPromotion(idPromotion);
            this.promotion = (Promotion)promotion.find(null, null)[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manyToOne() {
        if(this.idVols != null){
            this.setVolsBase();
        }
        if (this.idReservationMere != null) {
            this.setReservationMereBase();
        }
        if (this.idPromotion != null) {
            this.setPromotionBase();
        }
    }

    public List<VDetailReservation> convertToVDetailReservation(Object[] obj) {
        List<VDetailReservation> prods = new ArrayList<>();
        for (Object prod : obj) {
            VDetailReservation product = (VDetailReservation) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    public ReservationMere getMere() {
        return mere;
    }

    public void setMere(ReservationMere mere) {
        this.mere = mere;
    }

    public Vols getVols() {
        return vols;
    }

    public void setVols(Vols vols) {
        this.vols = vols;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

}
