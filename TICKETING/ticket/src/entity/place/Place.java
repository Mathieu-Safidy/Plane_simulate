package entity.place;

import java.util.ArrayList;
import java.util.List;

import entity.avion.Avion;
import entity.event.Vols;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "place")
public class Place extends Entity {

    @Column(name = "id_place")
    String idPlace;

    @Column(name = "id_type")
    String idType;

    @Column(name = "id_avion")
    String idAvion;

    @Ignore
    TypeSiege typeSiege;

    @Ignore
    Avion avion;


    public Place() throws Exception {
        initiate();
    }

    public Place(String idType, String idAvion) throws Exception {
        this.idType = idType;
        this.idAvion = idAvion;
        initiate();
    }

    public Place(String idPlace, String idType, String idAvion) throws Exception {
        this.idPlace = idPlace;
        this.idType = idType;
        this.idAvion = idAvion;
        initiate();
    }

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public void setAvionBase() throws Exception {
        if(this.idAvion != null) {
            Avion avion = new Avion();
            avion.setIdAvion(idAvion);
            this.avion = (Avion)avion.find(null, null)[0];
        }
    }
    public void setTypeBase() throws Exception {
        if(this.idType != null) {
            TypeSiege type = new TypeSiege();
            type.setIdType(idType); 
            this.typeSiege = (TypeSiege)typeSiege.find(null, null)[0];
        }
    }



    public void manyToOne() {
        try {
            setAvionBase();
            setTypeBase();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Place> convertToPlace(Object[] obj) {
        List<Place> prods = new ArrayList<>();
        for (Object prod : obj) {
            Place product = (Place) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

}
