package cont;

import org.springcopy.annote.ControllerSet;
import org.springcopy.annote.PathLink;

@ControllerSet(url = "/")
public class IndexController {
    @PathLink(path = "")
    public String index() {
        return "redirect:/vols/index";
    }
}
