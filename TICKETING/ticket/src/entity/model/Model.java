package entity.model;

import java.util.ArrayList;
import java.util.List;

import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "model")
public class Model extends Entity {

    @Column(name = "id_model")
    String idModel;

    @Column(name = "nom")
    String nom;

    public Model() throws Exception {
        initiate();
    }

    public Model(String nom) throws Exception {
        this.nom = nom;
        initiate();
    }

    public Model(String idModel, String nom) throws Exception {
        this.idModel = idModel;
        this.nom = nom;
        initiate();
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Model> convertToModel(Object[] obj) {
        List<Model> prods = new ArrayList<>();
        for (Object prod : obj) {
            Model product = (Model) prod;
            prods.add(product);
        }
        return prods;
    }

}
