package cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springcopy.annote.AnnotParam;
import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.ErrorRedirect;
import org.springcopy.annote.GET;
import org.springcopy.annote.MiddleWare;
import org.springcopy.annote.POST;
import org.springcopy.annote.PathLink;
import org.springcopy.core.ModelView;
import param.TypeParam;
import param.Parametre;

@ControllerSet(url = "/parametre")
public class ParamController {
    
    @PathLink(path = "/update/form")
    @GET
    @MiddleWare(acces = "Admin",linkLogin = "Login.jsp")
    public ModelView formParam(@AnnotParam(name = "id") String idParam) throws Exception{
        ModelView view = new ModelView("add-param-form.jsp");
        TypeParam param = new TypeParam();
        Parametre par = new Parametre();
        par.setIdParametre(idParam);
        par = (Parametre)par.convertToParametre(par.find(null, null)).get(0);
        param = par.getType();
        // List<TypeParam> paramList = new ArrayList<>();
        // paramList = param.convertToTypeParam(param.getAllP());
        // view.addObject("parametre", paramList);
        view.addObject("parametre", par);
        view.addObject("type", param);

        return view;
    }

    @PathLink(path = "/update")
    @POST
    @MiddleWare(acces = "Admin",linkLogin = "Login.jsp")
    public String update(@AnnotParam(name = "parametre") Parametre param) throws Exception {
        try {
            Map<String,Object> paramUp = new HashMap<>();
            paramUp.put("id_parametre", param.getIdParametre()); 
            param.setIdParametre(null);
            param.update(paramUp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la mise à jour du paramétre");
        }
        return "redirect:/parametre/index";
    }

    @PathLink(path = "/add/form")
    @GET
    @MiddleWare(acces = "Admin", linkLogin = "Login.jsp")
    public String updateParam() throws Exception{   
        return "";
    }

    @PathLink(path = "/add")
    @POST
    @ErrorRedirect(link = "/add/form")
    @MiddleWare(acces = "Admin",linkLogin = "Login.jsp")
    public String addParam() throws Exception{
        
        return "redirect:";
    }

    @PathLink(path = "/index")
    @GET
    @MiddleWare(acces = "Admin", linkLogin = "Login.jsp")
    public ModelView getAll() throws Exception{
        ModelView view = new ModelView("list-parametre.jsp");
        Parametre param = new Parametre();
        List<Parametre> paramList = new ArrayList<>();
        paramList = param.convertToParametre(param.getAllP());
        view.addObject("parametre", paramList);
        return view;
    }
}
