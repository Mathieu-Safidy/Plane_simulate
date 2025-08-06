package entity.event;

import java.sql.Timestamp;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springcopy.core.FileMap;

import entity.entite.Users;
import entity.place.PlaceReserve;
import entity.place.TypeSiege;
import exception.ClientException;
import param.Parametre;
import utility.Entity;
import utility.annotation.Column;
import utility.annotation.Ignore;
import utility.annotation.PrimaryKey;
import utility.annotation.Table;

@Table(name = "reservation")
public class Reservation extends Entity {

    @PrimaryKey(name = "id_reservation")
    @Column(name = "id_reservation")
    String idReservation;

    @Column(name = "date_reservation")
    Timestamp dateReservation;

    @Column(name = "id_reservation_mere")
    String idReservationMere;

    @Column(name = "id_users")
    String idUser;

    @Column(name = "id_type")
    String idType;

    @Ignore
    ReservationMere resaMere;

    @Ignore
    Users use ; 

    @Ignore
    Integer nombre;

    @Ignore
    VDetailReservation detailReservation ;

    @Ignore
    TypeSiege type;

    @Ignore
    FileMap file;

    public TypeSiege getType() {
        return type;
    }

    public void setType(TypeSiege type) {
        this.type = type;
    }

    public VDetailReservation getDetailReservation() {
        return detailReservation;
    }

    public void setDetailReservation(VDetailReservation detailReservation) {
        this.detailReservation = detailReservation;
        if(this.nombre == null) {
            this.nombre = this.detailReservation.getNombre();
        }
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Reservation() throws Exception {
        initiate();
    }

    public Reservation(Timestamp dateReservation, String idReservationMere, String idUser) throws Exception {
        this.dateReservation = dateReservation;
        this.idReservationMere = idReservationMere;
        this.idUser = idUser;
        initiate();
    }

    public Reservation(String idReservation, Timestamp dateReservation, String idReservationMere, String idUser)
            throws Exception {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.idReservationMere = idReservationMere;
        this.idUser = idUser;
        initiate();
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getIdReservationMere() {
        return idReservationMere;
    }

    public void setIdReservationMere(String idReservationMere) {
        this.idReservationMere = idReservationMere;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setFile(FileMap file) {
        this.file = file;
    }
    
    public void setUserBase() throws Exception {
        if(this.idUser != null)  {
            this.use = new Users();
            this.use.setIdUsers(this.idUser);
            this.use = (Users)(this.use.find(null, null)[0]);
        }
    }

    public void setTypeBase() throws Exception {
        if(this.idType != null)  {
            this.type = new TypeSiege();
            this.type.setIdType(this.idType);
            this.type = (TypeSiege)(this.type.find(null, null)[0]);
        }
    } 

    public void setReservationMereBase() throws Exception { 
        if(this.idReservationMere != null)  {
            this.resaMere = new ReservationMere();
            this.resaMere.setIdReservationMere(this.idReservationMere);
            this.setResaMere((ReservationMere)this.resaMere.find(null, null)[0]);
            this.resaMere.setVolsBase();
        }
    }


    public void manyToOne() {
        try {
            setUserBase();
            setReservationMereBase();
            setTypeBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> convertToReservation(Object[] obj) {
        List<Reservation> prods = new ArrayList<>();
        for (Object prod : obj) {
            Reservation product = (Reservation) prod;
            product.manyToOne();
            prods.add(product);
        }
        return prods;
    }

    public void reserver () throws ClientException {
        Connection con = null;
        try {
            Timestamp dateActuel = this.getDateReservation();
            this.setReservationMereBase();
            long duree = Reservation.getDureeAvant(dateActuel, this.getResaMere().getVols().getDateVol());
            Parametre param = new Parametre();
            param.setIdParametre("PRM000002");
            param = (Parametre) param.find(null, null)[0];
            System.out.println("la duree "+duree+" valeur = "+Math.round(param.getValeur()));
            if (duree < Math.round(param.getValeur())) {
                throw new ClientException("La date limite de reservation a ete depasse ! ");
            }
            Object[] data = this.insertTrans();
            con = ((Connection)data[0]);
            // int nombre = this.detailReservation.getNombre();
            for (int i = 0; i < this.getNombre(); i++) {
                double prix = 0.0;
                PlaceReserve places = new PlaceReserve();
                prix = this.detailReservation.getPrix();
                if (this.detailReservation.getIdPromotion() != null) {
                    places.setIdPromotion(this.detailReservation.getIdPromotion());
                    Promotion prom = new Promotion();
                    prom.setIdPromotion(this.detailReservation.getIdPromotion());
                    prom = (Promotion) prom.find(null, null)[0];
                    System.out.println("Timestamp resa "+this.getDateReservation()+" ; "+prom.getDatePromotion()+ " ; "+ prom.getDateFin());
                   
                    if(this.getDateReservation().after(prom.getDatePromotion()) && this.getDateReservation().before(prom.getDateFin())) {
                        prix = (prix)*((100-prom.getValeur())/100);
                    }
                    places.setPrix(prix);
                }
                places.setIdReservation((String)data[1]);
                places.setIdType(this.detailReservation.getIdType());
                places.setPrix(prix);
                places.insertWithConnection(con);
            }
            con.commit();

        }catch (ClientException ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception e) {
                    ex.printStackTrace();
                }
            }
            throw ex;
        }catch (Exception e) { 
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ReservationMere getResaMere() {
        return resaMere;
    }

    public void setResaMere(ReservationMere resaMere) {
        this.resaMere = resaMere;
    }

    public Users getUse() {
        return use;
    }

    public void setUse(Users use) {
        this.use = use;
    }

    public static long getDureeAvant(Timestamp dateActuel,Timestamp dateDepart){
        LocalDateTime date1 = dateActuel.toLocalDateTime();
        LocalDateTime date2 = dateDepart.toLocalDateTime();
        long minutes = ChronoUnit.MINUTES.between(date1, date2);
        return minutes;
    }

    public void annulerReservation() throws Exception {
        Connection con = null;
        try {
            List<PlaceReserve> list = new ArrayList<PlaceReserve>();
            PlaceReserve place = new PlaceReserve();
            place.setIdReservation(idReservation);
            list = place.convertToPlaceReserve(place.find(null, null));
            Timestamp dateActuel = this.getDateReservation();
            this.setReservationMereBase();
            long duree = Reservation.getDureeAvant(dateActuel, this.getResaMere().getVols().getDateVol());
            Parametre param = new Parametre();
            param.setIdParametre("PRM000001");
            param = (Parametre) param.find(null, null)[0];
            System.out.println("la duree "+duree+" valeur = "+Math.round(param.getValeur()));
            if (duree < Math.round(param.getValeur())) {
                throw new ClientException("La date limite d'annulation de reservation a ete depasse ! ");
            }
            con = place.connect();
            
            for (PlaceReserve placeReserve : list) {
                placeReserve.delete(con);
            }
            Reservation reservation = new Reservation();
            reservation.setIdReservation(idReservation);
            reservation.delete(con);
            con.commit();
        } catch (ClientException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace(); 
            throw e;
        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } 
        finally {
            try {
                if (con!= null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
