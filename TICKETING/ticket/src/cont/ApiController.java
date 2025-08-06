package cont;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springcopy.annote.*;

import entity.event.Reservation;
import com.google.gson.Gson;
import org.springcopy.core.ModelView;
import param.Parametre;
import utility.ConfigManager;

@ControllerSet(url = "/Api")
public class ApiController {
    
    
    Properties properties = new Properties();

    @PathLink(path = "/insert")
    @POST
    public String insertParameter(@AnnotParam(name = "param") Parametre param) {
        try {
            param.insert();
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue lors de linsertion du parametre";
        }
        return "Insertion de parametre reussie";
    }

    @PathLink(path = "/update")
    @POST
    public String update(@AnnotParam(name = "parametre") Parametre param) throws Exception {
        try {
            Map<String,Object> paramUp = new HashMap<>();
            paramUp.put("id_parametre", param.getIdParametre()); 
            param.setIdParametre(null);
            param.update(paramUp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Une erreur est survenue lors de l'insertion du parametre");
        }
        return "Insertion de parametre reussie";
    }

    @PathLink(path = "/list")
    @GET
    public String listReservation(@AnnotParam(name = "id") String id) throws Exception {
        List<Reservation> listReservation = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setIdReservation(id);
        listReservation = reservation.convertToReservation(reservation.find(null, null));
        Gson json = new Gson();
        String response = json.toJson(listReservation);
        return response;
    } 

    @PathLink(path = "/dupliquer")
    @GET
    public String detailReservation(@AnnotParam(name = "id") String id) throws Exception {
        String message = "";
        String redirect = "/vols/reservations";
        try {
            InputStream inputs = ApiController.class.getClassLoader().getResourceAsStream("config/config.conf");
                properties.load(inputs);

            String apiUrl = properties.getProperty("api.url");
            String pdfurl = properties.getProperty("pdf.duplicate");
            String urlapi = apiUrl+pdfurl;
            System.out.println("link : "+urlapi);
            URL url = new URL(urlapi);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = "";
            HashMap<String,Object> map = new HashMap<>();
            map.put("id", id);
            Gson gson = new Gson();
            json = gson.toJson(map);

            System.out.println("json : "+json);

            // envoyer le JSON
            try(OutputStream os = conn.getOutputStream()) {
                System.out.println("type : "+conn.getRequestMethod());
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lire le PDF depuis la réponse et le sauvegarder
            String fileName = "reservation_" + id + ".pdf";
           String storagefolder = properties.getProperty("serveur")+File.separator+properties.getProperty("monApp");
            // String storagefolder = properties.getProperty("url.app");
            String path = storagefolder+properties.getProperty("storage"); // à adapter selon ton serveur
            System.out.println("path :"+storagefolder+File.separator+path);
            String accesFolder = properties.getProperty("storage")+"/"+fileName;
            File folder = new File(path);
            // String pathcomplete = storagefolder+File.separator+path;
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File pdfFile = new File(folder,fileName);
            try (InputStream in = conn.getInputStream(); FileOutputStream fos = new FileOutputStream(pdfFile)) {
                byte[] buffer = new byte[8192];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
            return "redirect:/Api/download?name="+accesFolder;
        } catch (Exception e) {
            message = "Exportation failed";
            e.printStackTrace();
        }
        return "redirect:"+redirect+"?message="+message;
    }

    @PathLink(path = "/download")
    @GET
    public ModelView downloadPdf(@AnnotParam(name = "name") String name) {
        ModelView view = new ModelView("pdfDetail.jsp");
        view.addObject("name", name);
        return view;
    }

}
