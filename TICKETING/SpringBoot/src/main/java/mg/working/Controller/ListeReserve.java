package mg.working.Controller;

import mg.working.Service.ReservationMereService;
import mg.working.Service.ReservationService;
import mg.working.Service.UserService;
import mg.working.model.Reservation;
import mg.working.model.ReservationMere;
import mg.working.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class ListeReserve {

    private final UserService userService;
    private final ReservationService reservationService;
    private final ReservationMereService reservationMereService;

    public ListeReserve(UserService userService, ReservationService reservationService, ReservationMereService reservationMereService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationMereService = reservationMereService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "Home";
    }

    @GetMapping("/list/{id}")
    public String listreservation(@PathVariable("id") String id,Model model) {
        Optional<User> optuser = userService.findByid(id);
        if (optuser.isPresent()) {
            List<ReservationMere> reservationMereList  = reservationMereService.findByIdUser(id);
//            List<Reservation> listreservation = reservationService.listReservations(id);
            model.addAttribute("listreservationmere", reservationMereList);
//            model.addAttribute("listreservation", listreservation);
        }

        return "Liste";
    }

}
