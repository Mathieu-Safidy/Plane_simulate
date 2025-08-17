package cont;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import entity.avion.Avion;
import org.springcopy.annote.AnnotParam;
import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.ErrorRedirect;

import entity.event.Reservation;
import entity.event.ReservationMere;
import entity.event.VDetailReservation;
import entity.event.VDetailVolsDispo;
import entity.event.Vols;
import entity.filtre.Filtre;
import entity.lieu.Ville;
import entity.place.DetailSiege;
import entity.place.TypeSiege;
import exception.ClientException;
import org.springcopy.annote.GET;
import org.springcopy.annote.MiddleWare;
import org.springcopy.annote.POST;
import org.springcopy.annote.PathLink;
import org.springcopy.core.FileMap;
import org.springcopy.core.ModelView;
import org.springcopy.core.Session;

@ControllerSet(url = "/vols")
public class VolsController {
    InputStream input = VolsController.class.getClassLoader().getResourceAsStream("config/app.conf");
    
    Properties properties = new Properties();


    @GET
    @PathLink(path = "/index")
    public ModelView findVols() throws Exception {
        ModelView view = new ModelView("index.jsp");   
        try {
            
            VDetailVolsDispo vol = new VDetailVolsDispo();
            vol.setIdType("TYS000001");
            List<VDetailVolsDispo> vols = vol.convertToVDetailVolsDispo(vol.find(null, null));
            for (VDetailVolsDispo volse : vols) {
                System.out.println("quantite : "+volse.getQuantite());
            }
            System.out.println("tableau objet : "+vol.find(null, null).length);
            for (Object volse : vol.find(null, null)) {
                System.out.println("quantite : "+((VDetailVolsDispo)volse).getQuantite());
            }

            Ville ville = new Ville();
            List<Ville> villes = new ArrayList<>();
            villes = ville.convertToVille(ville.find(null, null));
            view.addObject("villes", villes);
            view.addObject("vols", vols);
            
        } catch (Exception e) {
            throw e;
        }     
        return view;
    }

    @MiddleWare(acces = "Admin",linkLogin = "Login.jsp")
    @PathLink(path = "/admin")
    public ModelView administrer() throws Exception { 
        ModelView view = new ModelView("admin.jsp");
        return view;
    }

    @GET
    @PathLink(path = "/filtrer")
    public ModelView filtreVol(@AnnotParam(name = "filtre") Filtre filtre) throws Exception {
        ModelView view = new ModelView("index.jsp");
        List<VDetailVolsDispo> volDispo = filtre.filtrerVol();
        Ville ville = new Ville();
        List<Ville> villes = new ArrayList<>();
        villes = ville.convertToVille(ville.find(null, null));
        view.addObject("villes", villes);
        view.addObject("vols", volDispo);
        return view;
    }
    
    @GET
    @PathLink(path = "/info")
    public ModelView getInfoView(@AnnotParam(name = "idVol") String idvol) throws Exception {
        ModelView view = new ModelView("contacts.jsp");
        List<VDetailVolsDispo> vols = new ArrayList<>();
        VDetailVolsDispo vol = new VDetailVolsDispo();
        vol.setIdVols(idvol);
        vols = vol.convertToVDetailVolsDispo(vol.find(null, null));
        view.addObject("vols", vols);
        view.addObject("idvols", idvol);
        return view;
    }

    @MiddleWare(acces = "Client",linkLogin = "Login.jsp")
    @POST
    @PathLink(path = "/reserver")
    public String reserver(@AnnotParam(name = "filtre") Filtre filtre , Session session , @AnnotParam(name = "passport") FileMap file ) throws Exception {
        ReservationMere resamere = new ReservationMere();
        Reservation reservation = new Reservation();
        properties.load(input);
        
        VDetailReservation vDetailReservation = new VDetailReservation();
        try {
                reservation.setFile(file);
                resamere.setIdVols(filtre.getIdVols());
                resamere = (ReservationMere)resamere.find(null, null)[0];

                reservation.setDateReservation(filtre.getDateReservation());
                reservation.setIdReservationMere(resamere.getIdReservationMere());
                reservation.setIdUser((String)session.getSession("userId"));
                reservation.setIdType(filtre.getIdType());
                // reservation.setNombre(filtre.getPlace());

                System.out.println("nombre adult = "+filtre.getPlaceAdult()+" et enfant "+filtre.getPlaceEnfant());
                reservation.setNombre_adult(filtre.getPlaceAdult());
                reservation.setNombre_enfant(filtre.getPlaceEnfant());

                vDetailReservation.setIdReservationMere(resamere.getIdReservationMere());
                vDetailReservation.setIdType(filtre.getIdType());
                vDetailReservation = (VDetailReservation)vDetailReservation.find(null, null)[0];
                
                reservation.setDetailReservation(vDetailReservation);

                

                System.out.println("reserver : "+reservation.getIdUser() +" et fichier passport "+file );
                reservation.reserver();

        } catch (ClientException e) {
            return "redirect:/vols/info?idVol="+resamere.getIdVols()+"&message="+e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/vols/info?idVol="+resamere.getIdVols()+"&message=Une erreur s'est produit lor du processus";
        }
        return "redirect:/vols/index";
    }


    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/list")
    public ModelView listVols(@AnnotParam(name = "filtre") Filtre filtre) throws Exception {
        ModelView view = new ModelView("list-vols.jsp");
        List<VDetailVolsDispo> volDispo = new ArrayList<>();
        Vols vol = new Vols();
        VDetailVolsDispo voldis = new VDetailVolsDispo();
        System.out.println("OUT");
        volDispo = voldis.convertToVDetailVolsDispo(voldis.find(null, null));
        List<Vols> vols = vol.convertToVols(vol.find(null, null));
        if(filtre != null) {
            volDispo = filtre.filtrerVols();
        }
        
        Ville ville = new Ville();
        List<Ville> villes = new ArrayList<>();
        villes = ville.convertToVille(ville.find(null, null));
        view.addObject("villes", villes);
        view.addObject("volsdispo", volDispo);
        view.addObject("vols", vols);
        return view;
    }

    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/add/form")
    public ModelView addForm() throws Exception {
        System.out.println("Dans la fonction ");
        ModelView modelView = new ModelView("add-formualaire.jsp");
        List<Ville> listville = new ArrayList<>();
        Ville ville = new Ville();
        List<Avion> avionList = new ArrayList<>();
        Avion avion = new Avion();
        TypeSiege typeSiege = new TypeSiege();
        List<TypeSiege> typeSiegeList = new ArrayList<>();

        try {
        listville = (List<Ville>) ville.convertToVille(ville.find(null,null));
        System.out.println("ville size : "+listville.size());
        avionList = (List<Avion>) avion.convertToAvion(avion.find(null, null));
        System.out.println("avion size : "+avionList.size());
        typeSiegeList = (List<TypeSiege>) typeSiege.convertToTypeSiege(typeSiege.find(null,null));
        } catch (Exception e) {
            e.printStackTrace();
        }


        modelView.addObject("villes", listville);
        modelView.addObject("avions", avionList);
        modelView.addObject("typeSiege", typeSiegeList);
        System.out.println("Fonction fini");
        return modelView;
    }
    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/update/form")
    public ModelView changeForm(@AnnotParam(name = "id_vols") String idVols) throws Exception {
        ModelView modelView = new ModelView("add-formualaire.jsp");
        List<Ville> listville = new ArrayList<>();
        Ville ville = new Ville();
        List<Avion> avionList = new ArrayList<>();
        Avion avion = new Avion();
        Vols vols = new Vols();
        TypeSiege typeSiege = new TypeSiege();
        List<TypeSiege> typeSiegeList = new ArrayList<>();
        ReservationMere reservationMere = new ReservationMere();
        List<DetailSiege> detailSieges = new ArrayList<>();

        try {
            listville = (List<Ville>) ville.convertToVille(ville.find(null,null));
            System.out.println("ville size : "+listville.size());
            avionList = (List<Avion>) avion.convertToAvion(avion.find(null, null));
            System.out.println("avion size : "+avionList.size());
            vols.setIdVols(idVols);
//            System.out.println();
            typeSiegeList = (List<TypeSiege>) typeSiege.convertToTypeSiege(typeSiege.find(null,null));
            vols = (Vols) vols.find(null,null)[0];
            reservationMere.setIdVols(idVols);
            reservationMere = reservationMere.convertToReservationMere(reservationMere.find(null, null)).get(0);
            detailSieges = reservationMere.getDetailSiege();
        } catch (Exception e) {
            e.printStackTrace();
        }


        modelView.addObject("villes", listville);
        modelView.addObject("avions", avionList);
        modelView.addObject("typeSiege", typeSiegeList);
        modelView.addObject("vols", vols);
        modelView.addObject("detailSiege", detailSieges);
        return modelView;
    }

    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/add")
    @ErrorRedirect(link = "/add/form")
    public String addVols(@AnnotParam(name = "vols") Vols vol,@AnnotParam(name = "typeSiege0") DetailSiege detailSiege0,@AnnotParam(name = "typeSiege1") DetailSiege detailSiege1,@AnnotParam(name = "typeSiege2") DetailSiege detailSiege2) throws Exception {
        try {
            DetailSiege[] detailSieges = new DetailSiege[]{
                detailSiege0,
                detailSiege1,
                detailSiege2
            };
            vol.insertVols(detailSieges);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientException("Une erreur est survenue lors de l'insertion du vol");
        }
        return "redirect:/vols/list";
    }

    @POST
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/update")
    @ErrorRedirect(link = "/update/form")
    public String updateVols(@AnnotParam(name = "vols") Vols vol) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id_vols", vol.getIdVols());
            vol.update(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientException("Une erreur est survenue lors de la modification du vol");
        }
        return "redirect:/vols/list";
    }

    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/delete")
    public String deleteVols(@AnnotParam(name = "id_vols") String id_vols) throws Exception { 
        Vols vol = new Vols();
        try {

            ReservationMere reservationMere = new ReservationMere();
            reservationMere.setIdVols(id_vols);
            reservationMere = reservationMere.convertToReservationMere(reservationMere.find(null, null)).get(0);
            DetailSiege detailSiege = new DetailSiege();
            detailSiege.setIdReservationMere(reservationMere.getIdReservationMere());
            List<DetailSiege> details = detailSiege.convertToDetailSiege(detailSiege.find(null, null));
            for (DetailSiege detailSiege2 : details) {
                detailSiege2.delete();
            }
            System.out.println("Supression de detail siege");
            reservationMere.delete();
            vol.setIdVols(id_vols);
            vol.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientException("Une erreur est survenue lors de la suppression du vol");
        }
        return "redirect:/vols/list";
    }

    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/reservations")
    @GET
    public ModelView listRervation() throws Exception  {
        ModelView view = new ModelView("list-reservation.jsp");
        List<Reservation> reservations = new ArrayList<>();
        List<ReservationMere> reservationMeres = new ArrayList<>();
        Reservation reservation = new Reservation();
        ReservationMere reservationMere = new ReservationMere();

        reservationMeres = reservationMere.convertToReservationMere(reservationMere.find(null,null));
        reservations = reservation.convertToReservation(reservation.find(null, null));
        view.addObject("reservations", reservations);
        view.addObject("reservationsMere",reservationMeres);
        return view;
    }

    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    @PathLink(path = "/reservations/annulation")
    @POST
    public String annulerReservation(@AnnotParam(name = "filtre") Filtre filtre) throws Exception {
        try {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(filtre.getIdReservation());
            reservation = reservation.convertToReservation(reservation.find(null, null)).get(0);
            reservation.setDateReservation(filtre.getDateReservation());
            reservation.annulerReservation();
        } catch (ClientException e) {
            return "redirect:/vols/reservations?message="+e.getMessage();  
        } catch (Exception e) {
            return "redirect:/vols/reservations?message=Une erreur s'est produit lors de l'annulation de la reservation";
        }
        return "redirect:/vols/reservations?message=Annulation reussi";
    }
}
