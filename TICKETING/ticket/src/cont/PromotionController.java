package cont;

import org.springcopy.annote.AnnotParam;
import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.GET;
import org.springcopy.annote.MiddleWare;
import org.springcopy.annote.POST;
import org.springcopy.annote.PathLink;
import org.springcopy.core.ModelView;

import entity.event.Promotion;
import exception.ClientException;

@ControllerSet(url = "/promotion")
public class PromotionController {
    

    @PathLink(path = "/add/form")
    @GET
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    public ModelView promoForm() {
        ModelView view = new ModelView("promo-form.jsp");
        return view;
    }

    @PathLink(path = "/add")
    @POST
    @MiddleWare(acces = "Admin" , linkLogin = "Login.jsp")
    public String addPromo(@AnnotParam(name = "promotion") Promotion promotion) throws Exception{
        try {
            promotion.insert();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientException("Une erreur est survenue sur linsertion de la promotion");
        }
        return "redirect:/vols/admin";
    }
}
