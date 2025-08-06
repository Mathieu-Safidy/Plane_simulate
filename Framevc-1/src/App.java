import java.io.File;

import org.springcopy.annote.ControllerSet;
import org.springcopy.core.Mapping;

import cont.Acciel;

public class App {
    public static void main(String[] args) throws Exception {
        // Mapping map = new Mapping(Acciel.class.getName(), "index");
        // // boolean vr = App.isIN("http://localhost:8080/test_projet/ato/acceuil", "/acceuil" , map);
        // // System.out.println("la reponse est  :"+vr);
        // String url = "http://localhost:8080/test_projet/ato/acceuil";
        // String url1 = url.split("/")[0];
        // System.out.println(url1);
    }
    public static boolean isIN(String url, String key , Mapping map ){
        try{
            String cls_name = map.getClassName();
            Class<?> cls = Class.forName(cls_name);
            ControllerSet annot = cls.getAnnotation(ControllerSet.class);
            int inddice = url.indexOf(annot.url());
            System.out.println("Indice : "+inddice);
            if (inddice != -1) {
                String url_suit = url.substring(inddice+annot.url().length());
                System.out.println(url_suit + " ; " + key);

                if (url_suit.equals(key)){
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
