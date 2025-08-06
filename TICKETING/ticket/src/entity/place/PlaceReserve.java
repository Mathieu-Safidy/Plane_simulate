package entity.place;

import java.util.ArrayList;
import java.util.List;

import entity.event.Promotion;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "place_reserve")
public class PlaceReserve extends Entity {

    @Column(name = "id_place")
    String idPlace;

    @Column(name = "prix")
    Double prix;

    @Column(name = "id_promotion")
    String idPromotion;

    @Column(name = "id_reservation")
    String idReservation;

    @Column(name = "id_type")
    String idType;

    @Ignore
    Promotion promotion;

    @Ignore
    double prixPromotion;

    public double getPrixPromotion() {
        return prixPromotion;
    }

    public void setPrixPromotion() {
        if(promotion != null) {
            this.prixPromotion = this.getPrix()-(promotion.getValeur()*getPrix());
        }
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public PlaceReserve() throws Exception {
        initiate();
    }

    public PlaceReserve(Double prix, String idPromotion, String idReservation, String idType) throws Exception {
        this.prix = prix;
        this.idPromotion = idPromotion;
        this.idReservation = idReservation;
        this.idType = idType;
        initiate();
    }

    public PlaceReserve(String idPlace, Double prix, String idPromotion, String idReservation, String idType)
            throws Exception {
        this.idPlace = idPlace;
        this.prix = prix;
        this.idPromotion = idPromotion;
        this.idReservation = idReservation;
        this.idType = idType;
        initiate();
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(String idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
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
        if(this.idPromotion != null) {
            this.setPromotionBase();
            this.setPrixPromotion();
        }
    }

    public List<PlaceReserve> convertToPlaceReserve(Object[] obj) {
        List<PlaceReserve> prods = new ArrayList<>();
        for (Object prod : obj) {
            PlaceReserve product = (PlaceReserve) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

}
