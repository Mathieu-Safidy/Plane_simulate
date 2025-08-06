package entity.event;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "promotion")
public class Promotion extends Entity {

    @Column(name = "id_promotion")
    String idPromotion;

    @Column(name = "valeur")
    Double valeur;

    @Column(name = "date_promotion")
    Date datePromotion;

    @Column(name = "date_fin")
    Timestamp dateFin;

    @Column(name = "nombre")
    Double nombre;

    @Column(name = "id_type")
    String idType;

    @Column(name = "id_vols")
    String idVols;

    public Promotion() throws Exception {
        initiate();
    }

    public Promotion(Double valeur, Date datePromotion, Timestamp dateFin ,  Double nombre, String idType, String idVols) throws Exception {
        this.valeur = valeur;
        this.datePromotion = datePromotion;
        this.dateFin = dateFin;
        this.nombre = nombre;
        this.idType = idType;
        this.idVols = idVols;
        initiate();
    }

    public Promotion(String idPromotion, Double valeur, Date datePromotion, Timestamp dateFin ,  Double nombre, String idType,
            String idVols) throws Exception {
        this.idPromotion = idPromotion;
        this.valeur = valeur;
        this.datePromotion = datePromotion;
        this.dateFin = dateFin;
        this.nombre = nombre;
        this.idType = idType;
        this.idVols = idVols;
        initiate();
    }

    public String getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(String idPromotion) {
        this.idPromotion = idPromotion;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Date getDatePromotion() {
        return datePromotion;
    }

    public void setDatePromotion(Date datePromotion) {
        this.datePromotion = datePromotion;
    }

    public Double getNombre() {
        return nombre;
    }

    public void setNombre(Double nombre) {
        this.nombre = nombre;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    

    public List<Promotion> convertToPromotion(Object[] obj) {
        List<Promotion> prods = new ArrayList<>();
        for (Object prod : obj) {
            Promotion product = (Promotion) prod;
            // product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

}
