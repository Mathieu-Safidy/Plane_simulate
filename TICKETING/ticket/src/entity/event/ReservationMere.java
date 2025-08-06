package entity.event;

import java.util.ArrayList;
import java.util.List;

import entity.entite.Users;
import entity.place.DetailSiege;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.Table;
import utility.annotation.PrimaryKey;

@Table(name = "reservation_mere")
public class ReservationMere extends Entity{

    @PrimaryKey(name = "id_reservation_mere")
    @Column(name = "id_reservation_mere")
    String idReservationMere;

    @Column(name = "id_vols")
    String idVols;

    @Column(name = "id_user")
    String idUser;

    @Ignore 
    Vols vols;

    @Ignore
    Users user;

    @Ignore
    List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ReservationMere() throws Exception{
        initiate();
    }

    public ReservationMere(String idVols) throws Exception{
        this.idVols = idVols;
        initiate();
    }

    public ReservationMere(String idReservationMere, String idVols) throws Exception{
        this.idReservationMere = idReservationMere;
        this.idVols = idVols;
        initiate();
    }

    public String getIdReservationMere() {
        return idReservationMere;
    }

    public void setIdReservationMere(String idReservationMere) {
        this.idReservationMere = idReservationMere;
    }

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setVolsBase() throws Exception {
        if(this.idVols != null) {
            Vols vol = new Vols();
            vol.setIdVols(idVols);
            vol = (Vols)vol.find(null, null)[0];
            this.setVols(vol);
        }
    }

    public void setReservationBase() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setIdReservationMere(this.getIdReservationMere());
        this.setReservations(reservation.convertToReservation(reservation.find(null,null)));
    }

    public void manyToOne() throws Exception {
        try {
            this.setReservationBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ReservationMere> convertToReservationMere(Object[] obj) throws Exception {
        List<ReservationMere> prods = new ArrayList<>();
        for (Object prod : obj) {
            ReservationMere product = (ReservationMere) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }


    public List<Reservation> getReservationsInit() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setIdReservationMere(idReservationMere);
        List<Reservation> reservations = reservation.convertToReservation(reservation.find(null,null));
        this.setReservations(reservations);
        return reservations;
    }

    public Vols getVols() {
        return vols;
    }

    public void setVols(Vols vols) {
        this.vols = vols;
    }

    public List<DetailSiege> getDetailSiege() throws Exception{
        List<DetailSiege> detailSieges = new ArrayList<>();
        DetailSiege detailSiege = new DetailSiege();
        detailSiege.setIdReservationMere(this.getIdReservationMere());
        detailSieges = detailSiege.convertToDetailSiege(detailSiege.find(null, null));
        return detailSieges;
    }

    
}
