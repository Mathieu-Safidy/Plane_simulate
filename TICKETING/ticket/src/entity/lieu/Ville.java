package entity.lieu;

import java.util.ArrayList;
import java.util.List;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Table;

@Table(name = "ville")
public class Ville extends Entity {

    @Column(name = "id_ville")
    String idVille;

    @Column(name = "nom")
    String nom;

    public Ville() throws Exception {
        initiate();
    }

    public Ville(String idVille, String nom) throws Exception {
        this.idVille = idVille;
        this.nom = nom;
        initiate();
    }

    public Ville(String nom) throws Exception {
        this.nom = nom;
        initiate();
    }

    public String getIdVille() {
        return idVille;
    }

    public void setIdVille(String idVille) {
        this.idVille = idVille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ville> convertToVille(Object[] obj) {
        List<Ville> prods = new ArrayList<>();
        for (Object prod : obj) {
            Ville product = (Ville) prod;
            prods.add(product);
        }
        return prods;
    }

}
