package mg.working.Controller;

import mg.working.Service.ParametreService;
import mg.working.model.Parametre;
import mg.working.repository.ParametreRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ParamController {

    private final ParametreRepository parametreRepository;
    private final ParametreService parametreService;

    public ParamController(ParametreRepository parametreRepository, ParametreService parametreService) {
        this.parametreRepository = parametreRepository;
        this.parametreService = parametreService;
    }

    @GetMapping("/list-param")
    public String listParam(Model model) {
        List<Parametre> listparam = parametreRepository.findAll();
        model.addAttribute("parametres",listparam);
        return "ListParam";
    }

    @GetMapping("/param/update/{id}")
    public String updateParam(@PathVariable("id") String id,Model model) {
        Optional<Parametre> parametre = parametreService.findById(id);
        if (parametre.isPresent()) {
            Parametre parametre1 = parametre.get();
            model.addAttribute("parametre",parametre1);
        }
        return "update-param";
    }

    @PostMapping("/param/update")
    public String update(@ModelAttribute Parametre parametre, RedirectAttributes redirectAttributes) {
        try {
            parametreRepository.save(parametre);
            redirectAttributes.addFlashAttribute("success","Modification avec succes");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error","Use erreur est survenue lors de la modifcation du parametre "+parametre.getIdType().getLibele());
        }

        return "redirect:/list-param";
    }
}
