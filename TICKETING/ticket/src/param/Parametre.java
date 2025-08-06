package param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entity.event.Vols;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "parametre")
public class Parametre extends Entity {
    
    @Column(name = "id_parametre")
    String idParametre;

    @Column(name = "valeur")
    Double valeur;

    @Column(name = "date_ajout")
    Date dateajout;

    @Column(name = "id_type")
    String idType;

    @Ignore
    TypeParam type;

    public Parametre() throws Exception {
        initiate();
    }

    public Parametre(Double valeur, Date dateajout, String idType, TypeParam type) throws Exception {
        this.valeur = valeur;
        this.dateajout = dateajout;
        this.idType = idType;
        this.type = type;
        initiate();
    }

    public Parametre(String idParametre, Double valeur, Date dateajout, String idType, TypeParam type) throws Exception {
        this.idParametre = idParametre;
        this.valeur = valeur;
        this.dateajout = dateajout;
        this.idType = idType;
        this.type = type;
        initiate();
    }

    public String getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(String idParametre) {
        this.idParametre = idParametre;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Date getDateajout() {
        return dateajout;
    }

    public void setDateajout(Date dateajout) {
        this.dateajout = dateajout;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public TypeParam getType() {
        return type;
    }

    public void setType(TypeParam type) {
        this.type = type;
    }

    public void setTypeBase() {
        try {
            TypeParam typeParameter = new TypeParam();
            typeParameter.setIdType(idType);
            typeParameter = (TypeParam)typeParameter.find(null, null)[0];
            this.setType(typeParameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manyToOne() throws Exception {
        if(this.idType != null){
           setTypeBase();
        }
    }

    public List<Parametre> convertToParametre(Object[] obj) throws Exception {
        List<Parametre> prods = new ArrayList<>();
        for (Object prod : obj) {
            Parametre product = (Parametre) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    
}
