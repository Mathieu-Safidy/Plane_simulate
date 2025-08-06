package entity.place;

import java.util.ArrayList;
import java.util.List;

import entity.event.ReservationMere;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "detail_siege")
public class DetailSiege extends Entity{

    @Column(name = "id_type")
    String idType;

    @Column(name = "id_reservation_mere")
    String idReservationMere;

    @Column(name = "nombre")
    Integer nombre;

    @Column(name = "prix")
    Double prix;

    @Ignore
    ReservationMere resaMere;

    public DetailSiege() throws Exception{
        initiate();
    }

    public DetailSiege(String idReservationMere, Integer nombre, Double prix) throws Exception{
        this.idReservationMere = idReservationMere;
        this.nombre = nombre;
        this.prix = prix;
        initiate();
    }

    public DetailSiege(String idType, String idReservationMere, Integer nombre, Double prix) throws Exception{
        this.idType = idType;
        this.idReservationMere = idReservationMere;
        this.nombre = nombre;
        this.prix = prix;
        initiate();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdReservationMere() {
        return idReservationMere;
    }

    public void setIdReservationMere(String idReservationMere) {
        this.idReservationMere = idReservationMere;
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

    public ReservationMere getResaMere() {
        return resaMere;
    }

    public void setResaMere(ReservationMere resaMere) {
        this.resaMere = resaMere;
    }
    
     public List<DetailSiege> convertToDetailSiege(Object[] obj) {
        List<DetailSiege> prods = new ArrayList<>();
        for (Object prod : obj) {
            DetailSiege product = (DetailSiege) prod;
            // product.manyToOne();
            prods.add(product);
        }
        return prods;
    }


    
    

    
}
