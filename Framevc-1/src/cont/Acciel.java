package cont;

import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.PathLink;

@ControllerSet(url="/ato")
public class Acciel {

    @PathLink(path = "/index")
    public String index(){
        return "Bienvenue dans l'index";
    }

    @PathLink(path = "/acceuil")
    public String acceuil(){
        return "Bienvenue dans l'acceuil";
    }

    @PathLink(path = "/index")
    public String index2(){
        return "Bienvenue dans lindex 2";
    }
}
