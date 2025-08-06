package entity.avion;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entity.event.Vols;
import entity.model.Model;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;

@Table(name = "avion")
public class Avion extends Entity {
    
    @Column(name = "id_avion")
    String idAvion;

    @Column(name = "date_fabrication")
    Date date_fabrication;

    @Column(name = "id_model")
    String idModel;

    @Ignore
    Model model;

    public Avion() throws Exception{
        initiate();
    }

    public Avion(String idAvion, Date date_fabrication, String idModel) throws Exception {
        this.idAvion = idAvion;
        this.date_fabrication = date_fabrication;
        this.idModel = idModel;
        initiate();
    }

    public Avion(Date date_fabrication, String idModel) throws Exception {
        this.date_fabrication = date_fabrication;
        this.idModel = idModel;
        initiate();
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public Date getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(Date date_fabrication) {
        this.date_fabrication = date_fabrication;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    

    public void setModelBase() {
        try {
            Model model = new Model();
            model.setIdModel(idModel);
            this.setModel((Model)model.find(null, null)[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void manyToOne() {
        if(this.idModel != null) {
            setModelBase();
        }
    }

    public List<Avion> convertToAvion(Object[] obj) {
        List<Avion> prods = new ArrayList<>();
        for (Object prod : obj) {
            Avion product = (Avion) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }
    public Avion convertToAvion(Object obj) throws Exception {
        Avion product = (Avion) obj;
        product.manyToOne();
        return product;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    


    
}
