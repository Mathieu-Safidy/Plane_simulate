package test;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.event.Reservation;
import entity.event.VDetailVolsDispo;
import entity.event.Vols;
import entity.filtre.Filtre;

public class App {
    public static void main(String[] args) {
        try {
            Filtre filtre = new Filtre();
            filtre.setIdReservation("RESERVATIOn");
            System.out.println("id"+filtre.getIdReservation());
            // Reservation resa = new Reservation();
            // resa.setIdReservation("RES000001");
            // resa = (Reservation)resa.find(null, null)[0];
            // Timestamp date = Timestamp.valueOf("2024-03-12 15:45:00");
            // Timestamp date2 = Timestamp.valueOf("2024-03-13 15:45:00");
            // System.out.println("minutes entre : "+Reservation.getDureeAvant(date,date2));
            // VDetailVolsDispo vol = new VDetailVolsDispo();
            // vol.setIdVols("VOL000001");
            // vol = vol.convertToVDetailVolsDispo(vol.find(null, null)).get(0);
            // System.out.println(vol.getVilleArrive().getNom());
            // Vols vol = new Vols();
            // vol.setIdVols("VOL000001");
            // vol = vol.convertToVols(vol.find(null, null)).get(0);
            // System.out.println("heure de vol : "+vol.getDateVol());
            // vol.setIdAvion("AVN000001");
            // Map<String,Object> map = new HashMap<String,Object>();
            // map.put("id_vols", "VOL000001");
            // vol.update(map);
            // Filtre filtre = new Filtre();
            // List<Vols> vols = filtre.filtrerVols();
            // for (Vols vols2 : vols) {
            //     System.out.println("id vols = "+vols2.getIdVols());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
