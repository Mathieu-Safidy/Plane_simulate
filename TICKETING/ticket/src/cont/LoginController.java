package cont;

import org.springcopy.annote.AnnotParam;
import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.POST;
import org.springcopy.annote.PathLink;
import org.springcopy.core.Session;
import org.springcopy.exception.FieldException;

import entity.entite.Users;

@ControllerSet(url = "/security")
public class LoginController {
    @PathLink(path = "/login")
    @POST
    public String login(@AnnotParam(name = "user")Users user, Session session) throws Exception {
        if (user.testLog(session)) {
            if (((String)session.getSession("auth")).equals("Customer") || ((String)session.getSession("auth")).equals("Client")) {
                return "redirect:/vols/index";
            }else if(((String)session.getSession("auth")).equals("Admin")) {
                return "redirect:/vols/admin";
            }
        }else {
            throw new FieldException("User inconnue",500);
        }

        return "redirect:/page/Login.jsp";
    }
}
