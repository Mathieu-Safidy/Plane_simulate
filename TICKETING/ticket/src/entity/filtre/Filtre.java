package entity.filtre;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.event.VDetailVolsDispo;
import entity.event.Vols;

public class Filtre {

    String villeArrive;

    String villeDepart;

    // String avion;

    Date dateDepart;

    Timestamp dateReservation;

    String idReservationMere;

    String idReservation;

    String idUser;

    String idVols;

    Integer place;

    String idType;

    Integer nombre;

    // String option;

    // Date dateD;

    // Time heure;

    public String getIdVols() {
        return idVols;
    }

    public void setIdVols(String idVols) {
        this.idVols = idVols;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Integer getPlace() {
        if (this.place == null) {
            place = 0;
        }
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    // public String getAvion() {
    // return avion;
    // }

    // public void setAvion(String avion) {
    // this.avion = avion;
    // }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
        // setDateD();
        // setHeure();
    }

    // public Date getDateD() {
    // return dateD;
    // }

    // public void setDateD(Date dateD) {
    // this.dateD = dateD;
    // }

    // public void setDateD() {
    // if (this.dateDepart != null) {
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // String date = dateFormat.format(this.dateDepart);
    // this.dateD = Date.valueOf(date);
    // }
    // }

    // public Time getHeure() {
    // return heure;
    // }

    // public void setHeure(Time heure) {
    // this.heure = heure;
    // }

    // public void setHeure() {
    // if (this.dateDepart != null) {
    // SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    // String time = timeFormat.format(this.dateDepart);
    // this.heure = Time.valueOf(time);
    // }
    // }

    public List<VDetailVolsDispo> filtrerVol() throws Exception {
        List<VDetailVolsDispo> vols = new ArrayList<>();
        VDetailVolsDispo vol = new VDetailVolsDispo();
        if (this.villeArrive != null) {
            vol.setIdVilleArrive(villeArrive);
        }
        if (this.villeDepart != null) {
            vol.setIdVilleDepart(villeDepart);
        }
        if (this.getDateDepart() != null) {
            try {

                ArrayList<String> list = new ArrayList<String>();
                list.add("date_vol");
                ArrayList<Map<String, Object>> listMap = new ArrayList<>();
                Map<String, Object> value = new HashMap<>();
                Map<String, Object> value2 = new HashMap<>();

                Date dateDep = this.getDateDepart();
                Time tempHeuremin = Time.valueOf("00:00:00");
                Time tempHeuremax = Time.valueOf("23:59:59");

                LocalDate localDate = dateDep.toLocalDate();
                
                LocalTime localTimemin = tempHeuremin.toLocalTime();
                LocalTime localTimemax = tempHeuremax.toLocalTime();

                LocalDateTime localDateTimeMin = LocalDateTime.of(localDate, localTimemin);
                LocalDateTime localDateTimeMax = LocalDateTime.of(localDate, localTimemax);

                Timestamp timestampmin = Timestamp.valueOf(localDateTimeMin);
                Timestamp timestampmax = Timestamp.valueOf(localDateTimeMax);

                value.put("min", timestampmin);
                value.put("max", timestampmax);

                if(this.getPlace() != null) {
                    list.add("quantite");
                    value2.put("min", this.getPlace());
                }
                
                listMap.add(value);
                listMap.add(value2);
                vols = vol.convertToVDetailVolsDispo(vol.find(list, listMap));

            } catch (Exception e) {
                throw e;
            }
        }

        return vols;
    }

    public List<Vols> filtrerVols() throws Exception {
        List<Vols> vols = new ArrayList<>();
        Vols vol = new Vols();
        if (this.villeArrive != null) {
            vol.setIdVilleArrive(villeArrive);
        }
        if (this.villeDepart != null) {
            vol.setIdVilleDepart(villeDepart);
        }
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Map<String, Object>> listMap = new ArrayList<>();

        if (this.getDateDepart() != null) {
            try {

                list.add("date_vol");
                Map<String, Object> value = new HashMap<>();
                Map<String, Object> value2 = new HashMap<>();

                Date dateDep = this.getDateDepart();
                Time tempHeuremin = Time.valueOf("00:00:00");
                Time tempHeuremax = Time.valueOf("23:59:59");

                LocalDate localDate = dateDep.toLocalDate();
                
                LocalTime localTimemin = tempHeuremin.toLocalTime();
                LocalTime localTimemax = tempHeuremax.toLocalTime();

                LocalDateTime localDateTimeMin = LocalDateTime.of(localDate, localTimemin);
                LocalDateTime localDateTimeMax = LocalDateTime.of(localDate, localTimemax);

                Timestamp timestampmin = Timestamp.valueOf(localDateTimeMin);
                Timestamp timestampmax = Timestamp.valueOf(localDateTimeMax);

                value.put("min", timestampmin);
                value.put("max", timestampmax);
                
                if(this.getPlace() != null) {
                    list.add("quantite");
                    value2.put("min", this.getPlace());
                }
                
                listMap.add(value);
                listMap.add(value2);
                
            } catch (Exception e) {
                throw e;
            }
        }
        vols = vol.convertToVols(vol.find(list, listMap));

        return vols;
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

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    // public String getOption() {
    // return option;
    // }

    // public void setOption(String option) {
    // this.option = option;
    // }

}
