package entity.place;

import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "type_siege")
public class TypeSiege extends Entity{
    @Column(name = "id_type")
    String idType;
    
    @Column(name = "nom")
    String nom;

    @Column(name = "niveau")
    Integer niveau;

    public TypeSiege() throws Exception {
        initiate();
    }

    public TypeSiege(String nom, Integer niveau) throws Exception {
        this.nom = nom;
        this.niveau = niveau;
        initiate();
    }

    public TypeSiege(String idType, String nom, Integer niveau) throws Exception {
        this.idType = idType;
        this.nom = nom;
        this.niveau = niveau;
        initiate();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

     public List<TypeSiege> convertToTypeSiege(Object[] obj) {
        List<TypeSiege> prods = new ArrayList<>();
        for (Object prod : obj) {
            TypeSiege product = (TypeSiege) prod;
            prods.add(product);
        }
        return prods;
    }


    
    
}
