package entity.event;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.avion.Avion;
import entity.lieu.Ville;
import entity.place.DetailSiege;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.PrimaryKey;
import utility.annotation.Table;

@Table(name = "vols")
public class Vols extends Entity {

    @PrimaryKey(name = "id_vols")
    @Column(name = "id_vols")
    String idVols;

    @Column(name = "date_vol")
    Timestamp dateVol;

    @Column(name = "id_ville_arrive")
    String idVilleArrive;

    @Column(name = "id_ville_depart")
    String idVilleDepart;

    @Column(name = "id_avion")
    String idAvion;

    @Column(name = "time_cancel")
    Double timeCancel;

    @Ignore
    Ville villeDepart;

    @Ignore
    Ville villeArrive;

    @Ignore
    Avion avion;

    public Vols() throws Exception {
        initiate();
    }

    public Vols(Timestamp dateVol, String idVilleArrive, String idVilleDepart, String idAvion, Ville villeDepart,
            Ville villeArrive) throws Exception {
        this.dateVol = dateVol;
        this.idVilleArrive = idVilleArrive;
        this.idVilleDepart = idVilleDepart;
        this.idAvion = idAvion;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        initiate();
    }

    public Vols(String idVols, Timestamp dateVol, String idVilleArrive, String idVilleDepart, String idAvion,
            Ville villeDepart, Ville villeArrive) throws Exception {
        this.idVols = idVols;
        this.dateVol = dateVol;
        this.idVilleArrive = idVilleArrive;
        this.idVilleDepart = idVilleDepart;
        this.idAvion = idAvion;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        initiate();
    }

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    public Timestamp getDateVol() {
        return dateVol;
    }

    public void setDateVol(Timestamp dateVol) {
        this.dateVol = dateVol;
    }

    public String getIdVilleArrive() {
        return idVilleArrive;
    }

    public void setIdVilleArrive(String idVilleArrive) {
        this.idVilleArrive = idVilleArrive;
    }

    public String getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(String idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArrive() {
        return villeArrive;
    }

    

    public void setVilleArrive(Ville villeArrive) {
        this.villeArrive = villeArrive;
    }

    public void setVilleArriveBase() {
        try {
            Ville arrive = new Ville();
            arrive.setIdVille(this.getIdVilleArrive());
            this.setVilleArrive((Ville)arrive.find(null, null)[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setVilleDepartBase() {
        try {
            Ville arrive = new Ville();
            arrive.setIdVille(this.getIdVilleDepart());
            this.setVilleDepart((Ville)arrive.find(null, null)[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAvionBase() {
        try {
            Avion avion = new Avion();
            avion.setIdAvion(this.getIdAvion());
            this.setAvion(avion.convertToAvion((Avion)avion.find(null, null)[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void manyToOne() {
        System.out.println(this.idVilleArrive);
        if (this.idVilleArrive != null) {
            this.setVilleDepartBase();
        }
        System.out.println(this.idVilleDepart);
        if (this.idVilleDepart != null) {
            this.setVilleArriveBase();
        }
        System.out.println(this.idAvion);
        if (this.idAvion != null) {
            this.setAvionBase();
        }
    }

    public List<Vols> convertToVols(Object[] obj) {
        List<Vols> prods = new ArrayList<>();
        for (Object prod : obj) {
            Vols product = (Vols) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    

    public Avion getAvion() throws Exception {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public void insertVols(DetailSiege[] detailSieges) {
        Connection con = null;
        ReservationMere reservationMereOut = null;
        try {
            Object[] data = this.insertTrans();
            con = ((Connection)data[0]);
            ReservationMere reservationMere = new ReservationMere();
            reservationMere.setIdVols((String)data[1]);
            String idmere = (String)reservationMere.insertWithConnectionBack(con);
            for (DetailSiege siege : detailSieges) {
                siege.setIdReservationMere(idmere);
                siege.insertWithConnection(con);
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Double getTimeCancel() {
        return timeCancel;
    }

    public void setTimeCancel(Double timeCancel) {
        this.timeCancel = timeCancel;
    }

}
